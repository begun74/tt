package tt.util;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletContextAwareProcessor;


@Component
public class Constants {

	@Autowired
    ServletContext context; 
	
    public static File tempDirectory = null;

    
    public Constants() {
    	
    }
    
	public File getTempDirectory() {
		return tempDirectory;
	}
    
	
	@PostConstruct
	void init(){
		tempDirectory = (File) context.getAttribute("javax.servlet.context.tempdir");
		System.out.println("Constants tempDirectory " +tempDirectory);
	}
	
	@PreDestroy
	void destr() {
		//System.out.println("BacketBean @PreDestroy ");
	}

}
