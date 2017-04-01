package tt.modelattribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component
@Scope("session")
public class MA_search implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4012114965391013096L;
	
	List<Long> pn = new ArrayList<Long>();
	
	List<Long> gndr = new ArrayList<Long>();
	
	List<Long> cat = new ArrayList<Long>();//Category
	

	
	public List<Long> getPn() {
		return pn;
	}



	public void setPn(List<Long> pn) {
		this.pn = pn;
	}




	public List<Long> getGndr() {
		return gndr;
	}



	public void setGndr(List<Long> gndr) {
		this.gndr = gndr;
	}


	
	public List<Long> getCat() {
		return cat;
	}



	public void setCat(List<Long> cat) {
		this.cat = cat;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "MA_search [pn=" + pn + ", gndr=" + gndr + ", cat=" + cat + "]";
	}



	

	
}
