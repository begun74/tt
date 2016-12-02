package tt.util;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletContextAwareProcessor;


@Component
public class Constants {

	@Autowired
    ServletContext context; 
	
	@Resource
    private Environment env;
	
    public static File tempDirectory = null;
    public static String IMAGES_SERVER = null;

    
    public Constants() {
    	
    }
    
    
	
	@PostConstruct
	void init(){
		
		IMAGES_SERVER = env.getRequiredProperty("IMAGES_SERVER");
		tempDirectory = (File) context.getAttribute("javax.servlet.context.tempdir");
		System.out.println("Constants tempDirectory " +tempDirectory);
		System.out.println("IMAGES_SERVER - " +IMAGES_SERVER);
	}
	
	@PreDestroy
	void destr() {
		//System.out.println("BacketBean @PreDestroy ");
	}

}
