package tt.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tt.bean.AdminSessionBean;
import tt.bean.SessionBean;
import tt.model.DirNomenclature;
import tt.model.Order;
import tt.model.OrderItems;
import tt.model.Tail;
import tt.service.TTServiceImpl;


@Controller
public class TTAjaxCtrl {
	
	@Autowired
	AdminSessionBean adminSessBean;
	
	
	@Autowired
	SessionBean sessBean;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	

	@ResponseBody
	@RequestMapping(value = "/clearErrors", method = RequestMethod.GET)
	public HttpStatus  clearErrors(HttpSession session) 
	{
		if(session.isNew()) return HttpStatus.FORBIDDEN;
		//System.out.println("clearErrors " +sessBean.getErrorList());
		adminSessBean.clearError();
		//System.out.println("clearErrors " +sessBean.getErrorList());
		
		return  HttpStatus.OK;
	}
	
	
	
	
	@RequestMapping(value = "/productDetail{id}", method = RequestMethod.GET)
	public @ResponseBody List<Tail> productDetail( @RequestParam ("id") long id) 
	{
		
		
		List<Tail> tails = ttService.getTailsList(id);
		
		for(Tail tail: tails)				//!! Нужно чтобы не было зацикливания т.к. в DirNomenclature есть Tail, а в Tail есть DirNomenclature и так по кругу
			tail.setDirNomenclature(null);  //   вываливается в Exception
		
		
		return  tails;
	}
	
	
	@RequestMapping(value = "/toOrder{id}", method = RequestMethod.GET)
	public ResponseEntity<Integer> toOrder(HttpSession session, @RequestParam ("id") long id, @RequestParam ("size") String size, @RequestParam ("amount") int amount) 
	{
		//System.out.println("session.isNew() - " +session.isNew());
		if(session.isNew()) return new ResponseEntity<Integer>(0,HttpStatus.FORBIDDEN);
		
		OrderItems orderItem = new OrderItems();
		orderItem.setAmount(amount);
		orderItem.setSize(size);
		orderItem.setCreate_date(new Timestamp(new Date().getTime()));
		orderItem.setDirNomenclature((DirNomenclature)ttService.getObject(DirNomenclature.class, id));
		orderItem.setNpp(sessBean.getNpp());
		
		sessBean.getOrderItems().add(orderItem);
		session.setAttribute("sessBean", sessBean);
		
		//System.out.println("toOrder  - "+id+"  "+size+"   "+amount);
		
		return  new ResponseEntity<Integer>(sessBean.getOrderItems().size(),HttpStatus.OK);
	}

	
	@RequestMapping(value = "/getOrderItems{id}", method = RequestMethod.GET)
	public ResponseEntity<List<OrderItems>> getOrderItems( @RequestParam ("id") long id) 
	{
		
		
		List<OrderItems> orderItems = ttService.getOrderItems(id);
		
		
		//for(OrderItems orderItem: orderItems) {				//!! Нужно чтобы не было зацикливания т.к. в DirNomenclature есть Tail, а в Tail есть DirNomenclature и так по кругу
		//	orderItem.setDirNomenclature(null);  			//   вываливается в Exception
		//	orderItem.setOrder(null);						//	
		//}
		System.out.println("orderItems.size() - " +orderItems.size());
		
		for(OrderItems orderItem: orderItems) {
			System.out.println(orderItem.getId()+":  ");
			System.out.println(orderItem.getOrder()+"  ");
			System.out.println(orderItem.getDirNomenclature());
		}
		
		
		return  new ResponseEntity<List<OrderItems>>(new ArrayList<OrderItems>(),HttpStatus.OK);
	}	
	

}
