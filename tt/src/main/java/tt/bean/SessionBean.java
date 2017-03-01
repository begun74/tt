package tt.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import tt.model.OrderItems;

@Service("sessBean")
@Scope( proxyMode=ScopedProxyMode.TARGET_CLASS,value=WebApplicationContext.SCOPE_SESSION)


public class SessionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3864475801596719257L;

	private LinkedHashMap<String,Object> errorMap = new LinkedHashMap<String,Object>();
	
	private AtomicInteger npp = new AtomicInteger(0);
	
	private List<OrderItems> orderItems = new ArrayList<OrderItems>();

	private User authUser;
	
	{
		//errorMap.put("ok", "ok ok");
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public int getNpp() {
		return npp.incrementAndGet();
	}

	public HashMap<String, Object> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(LinkedHashMap<String, Object> errorMap) {
		this.errorMap = errorMap;
	}

	public User getAuthUser() {
		return authUser;
	}

	public void setAuthUser(User authUser) {
		this.authUser = authUser;
	}



	
}
