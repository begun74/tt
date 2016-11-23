package tt.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tt.modelattribute.MA_loadNomencl;
import tt.modelattribute.MA_loadProvider;
import tt.modelattribute.MA_loadTail;
import tt.service.TTServiceImpl;


@Service
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4017739667981879557L;
	

	@Autowired
	private AppBean appBean;
	
	@Autowired
	private MA_loadProvider mA_loadProvider;
	
	@Autowired
	private MA_loadNomencl mA_loadNomencl;
	
	@Autowired
	private MA_loadTail mA_loadTail;
	
	private List<String> errorList = new ArrayList<String>();
	private List<String> successList = new ArrayList<String>();
	



	public MA_loadNomencl getmA_loadNomencl() {
		return mA_loadNomencl;
	}

	public void setmA_loadNomencl(MA_loadNomencl mA_loadNomencl) {
		this.mA_loadNomencl = mA_loadNomencl;
	}



	public MA_loadProvider getmA_loadProvider() {
		return mA_loadProvider;
	}

	public void setmA_loadProvider(MA_loadProvider mA_loadProvider) {
		this.mA_loadProvider = mA_loadProvider;
	}

	
	
	public MA_loadTail getmA_loadTail() {
		return mA_loadTail;
	}

	public void setmA_loadTail(MA_loadTail mA_loadTail) {
		this.mA_loadTail = mA_loadTail;
	}

	
	
	public List<String> getSuccessList() {
		return successList;
	}

	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}




	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	
	public void addError(String error) {
		getErrorList().clear();
		getErrorList().add(error);
	}


	public void clearError() {
		getErrorList().clear();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	@PostConstruct
	void init(){
		//System.out.println("SessionBean @PostConstruct ");
		
		if(appBean.findBySerialVerUID(mA_loadNomencl.getSerialversionuid()) == null)
			setmA_loadNomencl(new MA_loadNomencl());
		else
			this.setmA_loadNomencl((MA_loadNomencl) appBean.findBySerialVerUID(mA_loadNomencl.getSerialversionuid()) );

		if(appBean.findBySerialVerUID(mA_loadProvider.getSerialversionuid()) == null)
			setmA_loadProvider(new MA_loadProvider());
		else
			this.setmA_loadProvider((MA_loadProvider) appBean.findBySerialVerUID(mA_loadProvider.getSerialversionuid()) );

		if(appBean.findBySerialVerUID(mA_loadTail.getSerialversionuid()) == null)
			setmA_loadTail(new MA_loadTail());
		else
			this.setmA_loadTail((MA_loadTail) appBean.findBySerialVerUID(mA_loadTail.getSerialversionuid()) );

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
