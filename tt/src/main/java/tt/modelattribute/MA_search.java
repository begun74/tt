package tt.modelattribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class MA_search implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4012114965391013096L;
	
	List<Long> pn = new ArrayList<Long>();
	

	
	public List<Long> getPn() {
		return pn;
	}



	public void setPn(List<Long> pn) {
		this.pn = pn;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "MA_search [pn=" + pn + "]";
	}



	
	
	
	
}
