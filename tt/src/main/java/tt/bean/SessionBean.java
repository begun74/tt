package tt.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import tt.model.OrderItems;

@Service("sessBean")
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)


public class SessionBean {
	
	private AtomicInteger npp = new AtomicInteger(0);
	
	private List<OrderItems> orderItems = new ArrayList<OrderItems>();

	

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}



	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}



	public int getNpp() {
		return npp.incrementAndGet();
	}
	
	

}
