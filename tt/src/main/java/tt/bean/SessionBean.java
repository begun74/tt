package tt.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tt.modelattribute.MA_loadProvider;


@Service
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4017739667981879557L;
	
	@Autowired
	private MA_loadProvider mA_loadProvider;
	
	public SessionBean() {
		
	}
	
	



	public MA_loadProvider getmA_loadProvider() {
		return mA_loadProvider;
	}


	public void setmA_loadProvider(MA_loadProvider mA_loadProvider) {
		this.mA_loadProvider = mA_loadProvider;
	}


	@PostConstruct
	void init(){
		//System.out.println("SessionBean @PostConstruct ");
	}
	
	@PreDestroy
	void destr() {
		//System.out.println("SessionBean @PreDestroy ");
	}





	@Override
	public String toString() {
		return "SessionBean [mA_loadProvider=" + mA_loadProvider + "]";
	}


	

}
