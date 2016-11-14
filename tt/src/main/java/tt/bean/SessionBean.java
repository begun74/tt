package tt.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4017739667981879557L;
	
	
	public SessionBean() {
		
	}
	
	@PostConstruct
	void init(){
		System.out.println("SessionBean @PostConstruct ");
	}
	
	@PreDestroy
	void destr() {
		System.out.println("SessionBean @PreDestroy ");
	}

	@Override
	public String toString() {
		return "SessionBean [toString()=" + super.toString() + "]";
	}
	
	

}
