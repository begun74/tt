package tt.modelattribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;



@Component
@Scope("session")
@SessionAttributes("MA_search")
public class MA_search implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4012114965391013096L;
	
	List<Long> pn = new ArrayList<Long>();
	
	List<Long> gndr = new ArrayList<Long>();
	
	List<Long> cat = new ArrayList<Long>();//Category
	
	List<Long> type = new ArrayList<Long>();//Type бельевой, верхний, чулочно-носочный
	
	private int p_p = 9;
	
	private int sortby = 2;
	
	
	private boolean asc = true;
	
	
	
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



	public List<Long> getType() {
		return type;
	}



	public void setType(List<Long> type) {
		this.type = type;
	}



	public int getP_p() {
		return p_p;
	}

	public void setP_p(int p_p) {
		this.p_p = p_p;
	}



	public int getSortby() {
		return sortby;
	}

	public void setSortby(int sortby) {
		this.sortby = sortby;
	}



	public boolean isAsc() {
		return asc;
	}



	public void setAsc(boolean asc) {
		this.asc = asc;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "MA_search [pn=" + pn + ", gndr=" + gndr + ", cat=" + cat + ", type=" + type + ", p_p=" + p_p
				+ ", sortby=" + sortby + ", asc=" + asc + "]";
	}



	
	
}
