package tt.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import tt.model.Order;

@Service("sessBean")
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)


public class SessionBean {
	
	private AtomicInteger npp = new AtomicInteger(0);
	
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getNpp() {
		return npp.incrementAndGet();
	}
	
	

}
