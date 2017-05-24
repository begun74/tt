package tt.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import tt.model.JSON_OrderItems;
import tt.model.Order;
import tt.model.OrderItems;
import tt.model.Tail;
import tt.service.TTServiceImpl;
import tt.util.FileUpload;


@Controller
@Scope("session")
public class TTAjaxCtrl  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1356148162750126473L;


	@Autowired
	AdminSessionBean adminSessBean;
	
	
	@Autowired
	SessionBean sessBean;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	
	@Autowired
	private FileUpload fileUpload;

	@Autowired(required = true)
	private HttpServletRequest  request;	

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
	
	@ResponseBody
	@RequestMapping(value = "/delPhotoFile", method = RequestMethod.GET)
	public ResponseEntity<String>  delPhotoFile(HttpSession session, 
			@RequestParam(value = "code",required = true) Long code, 
				@RequestParam(value = "file_number",required = true) int file_number) 
	{
		ResponseEntity<String> OK_Resp = new ResponseEntity<String>("OK",HttpStatus.OK);
		ResponseEntity<String> ERR_Resp = new ResponseEntity<String>("ERR",HttpStatus.FORBIDDEN);
		
		if(session.isNew()) return new ResponseEntity<String>("FORBIDDEN",HttpStatus.FORBIDDEN);
		
		if(fileUpload.deletePhoto(code,file_number)) 
		{
			System.out.println("Delete photo file:  code " +code +",  file_number  " +file_number);
			return OK_Resp;
		}
		else
		{
			System.out.println("!! ERROR: Delete photo file:  code " +code +",  file_number  " +file_number);
			return ERR_Resp;
		}
		
	}

	@ResponseBody
	@RequestMapping(value = "/clearSessErrors", method = RequestMethod.GET)
	public HttpStatus  clearSessErrors(HttpSession session, @RequestParam(value = "error_name",required = false) String error_name) 
	{
		if(session.isNew()) return HttpStatus.FORBIDDEN;
		//System.out.println("clearErrors " +sessBean.getErrorList());
		if(error_name != null)
			session.removeAttribute(error_name);
		//System.out.println("clearErrors " +sessBean.getErrorList());
		
		return  HttpStatus.OK;
	}
	
	
	
	@RequestMapping(value = "/productDetail{id}", method = RequestMethod.GET)
	public @ResponseBody List<Tail> productDetail( @RequestParam ("id") long id) 
	{
		
		//Enumeration en = request.getHeaderNames();
		
		//while(en.hasMoreElements()) {
		//	System.out.println(en.nextElement()+" :  "+request.getHeader(en.nextElement().toString()));
		//}
	    List<Tail> tails = ttService.getTailsList(id, request.getRemoteAddr());
		
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
		
		DirNomenclature dn = (DirNomenclature)ttService.getObject(DirNomenclature.class, id);
		//System.out.println("dn.getTails() - " +dn.getTails());
		
		Tail firstTail = (Tail)dn.getTails().iterator().next();
		//System.out.println("firstTail - " + firstTail);
		
		//boolean isShowPrices = isShowPrices((org.springframework.security.core.userdetails.User)session.getAttribute("authUser"));
		
		//if(isShowPrices)
		dn.setOpt_price(firstTail.getOpt_price());
		//else 
		dn.setRozn_price(firstTail.getRozn_price());

		dn.setTempPrice(firstTail.getFirstPrice());
		
		orderItem.getTail().setDirNomenclature(dn);
		orderItem.setNpp(sessBean.getNpp());
		
		sessBean.getOrderItems().add(orderItem);
		session.setAttribute("sessBean", sessBean);
		
		//System.out.println("toOrder  - "+id+"  "+size+"   "+amount);
		
		return  new ResponseEntity<Integer>(sessBean.getOrderItems().size(),HttpStatus.OK);
	}

	@RequestMapping(value = "/toOrder2{id}", method = RequestMethod.GET)
	public ResponseEntity<Integer> toOrder2(HttpSession session, @RequestParam ("id_tails") long id_tails, @RequestParam ("amount") int amount) 
	{
		//System.out.println("session.isNew() - " +session.isNew());
		if(session.isNew()) return new ResponseEntity<Integer>(0,HttpStatus.FORBIDDEN);
		
		Tail tail = (Tail)ttService.getObject(Tail.class, id_tails);
		
		OrderItems orderItem = new OrderItems();
		orderItem.setAmount(amount);
		orderItem.setTail(tail);
		//orderItem.setSize(size);
		orderItem.setCreate_date(new Timestamp(new Date().getTime()));

		
		
		//DirNomenclature dn = (DirNomenclature)ttService.getObject(DirNomenclature.class, id);
		//System.out.println("dn.getTails() - " +dn.getTails());
		
		//Tail firstTail = (Tail)dn.getTails().iterator().next();
		//System.out.println("firstTail - " + firstTail);
		
		//boolean isShowPrices = isShowPrices((org.springframework.security.core.userdetails.User)session.getAttribute("authUser"));
		
		//if(isShowPrices)
		//dn.setOpt_price(firstTail.getOpt_price());
		//else 
		//dn.setRozn_price(firstTail.getRozn_price());

		//dn.setTempPrice(firstTail.getFirstPrice());
		
		//orderItem.setDirNomenclature(tail.getDirNomenclature());
		orderItem.setNpp(sessBean.getNpp());
		
		sessBean.getOrderItems().add(orderItem);
		session.setAttribute("sessBean", sessBean);
		
		//System.out.println("toOrder  - "+id+"  "+size+"   "+amount);
		
		return  new ResponseEntity<Integer>(sessBean.getOrderItems().size(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getOrderItems{id}", method = RequestMethod.GET)
	public ResponseEntity<List<JSON_OrderItems>> getOrderItems( @RequestParam ("id") long id) 
	{
		
		
		List<OrderItems> orderItems = ttService.getOrderItems(id);
		
		List<JSON_OrderItems> json_oitems = new ArrayList<JSON_OrderItems>();
		
		//System.out.println("orderItems.size() - " +orderItems.size());
		
		for(OrderItems orderItem: orderItems) {
			//System.out.println(orderItem.getId()+":  "+orderItem.getSize()+"  "+ orderItem.getAmount());
			//System.out.println(orderItem.getOrder()+"  ");
			//System.out.println(orderItem.getDirNomenclature());
			JSON_OrderItems json_oitem = new JSON_OrderItems();
			json_oitem.setId(orderItem.getId());
			json_oitem.setAmount(orderItem.getAmount());
			json_oitem.setArticle(orderItem.getTail().getDirNomenclature().getArticle());
			json_oitem.setCode(orderItem.getTail().getDirNomenclature().getCode());
			json_oitem.setCreate_date(orderItem.getCreate_date());
			json_oitem.setModel(orderItem.getTail().getDirNomenclature().getModel());
			json_oitem.setName(orderItem.getTail().getDirNomenclature().getName());
			json_oitem.setSize(orderItem.getSize());
			json_oitem.setDestruction_date(orderItem.getDestruction_date());
			
			json_oitems.add(json_oitem);
		}
		//System.out.println(" json_oitems - " + json_oitems);
		
		return  new ResponseEntity<List<JSON_OrderItems>>(json_oitems,HttpStatus.OK);
	}	
	
	
	@RequestMapping(value = "/closeOrder{id}", method = RequestMethod.GET)
	public ResponseEntity<Long>  closeOrder( @RequestParam ("id") long id) 
	{
		
		
		List<OrderItems> lOrderItems = ttService.getOrderItems(id);
		
		for(OrderItems oi : lOrderItems)
			if(oi.getDestruction_date() == null)  
				oi.setDestruction_date(new Timestamp(new Date().getTime()));
		
		ttService.saveOrderItems(lOrderItems);
		
		return  new ResponseEntity<Long>(id,HttpStatus.OK);

	}
	
	@RequestMapping(value = "/setReadyOrderItem{id}", method = RequestMethod.GET)
	public ResponseEntity<Timestamp>  setReadyOrderItem( @RequestParam ("id") long id, @RequestParam ("status") boolean status) 
	{
		OrderItems oi = (OrderItems) ttService.getObject(OrderItems.class, id);
		
		if(status)
			oi.setDestruction_date(new Timestamp(new Date().getTime()));
		else
			oi.setDestruction_date(null);
		
		List<OrderItems> lOrderItems = new ArrayList<OrderItems>(1); 
		lOrderItems.add(oi);
		
		ttService.saveOrderItems(lOrderItems);
		
		return  new ResponseEntity<Timestamp>(oi.getDestruction_date() , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/countGender{id}", method = RequestMethod.GET)
	public ResponseEntity<Integer>  countGender(HttpSession session, @RequestParam ("id") long id) 
	{
		
		if(session.isNew()) return new ResponseEntity<Integer>(0,HttpStatus.FORBIDDEN);		
		
		int count = ttService.countGender(id).intValue();
		
		return  new ResponseEntity<Integer>(count,HttpStatus.OK);

	}


	@RequestMapping(value = "/countCategory{id}", method = RequestMethod.GET)
	public ResponseEntity<Integer>  countCategory(HttpSession session, @RequestParam ("id") long id) 
	{
		
		if(session.isNew()) return new ResponseEntity<Integer>(0,HttpStatus.FORBIDDEN);		
		
		int count = ttService.countCategory(id).intValue();
		
		return  new ResponseEntity<Integer>(count,HttpStatus.OK);

	}

	@RequestMapping(value = "/countProvider{id}", method = RequestMethod.GET)
	public ResponseEntity<Integer>  countProvider(HttpSession session, @RequestParam ("id") long id) 
	{
		
		if(session.isNew()) return new ResponseEntity<Integer>(0,HttpStatus.FORBIDDEN);		
		
		int count = ttService.countProvider(id).intValue();
		
		return  new ResponseEntity<Integer>(count,HttpStatus.OK);

	}

	@RequestMapping(value = "/countType{id}", method = RequestMethod.GET)
	public ResponseEntity<Integer>  countType(HttpSession session, @RequestParam ("id") long id) 
	{
		
		if(session.isNew()) return new ResponseEntity<Integer>(0,HttpStatus.FORBIDDEN);		
		
		int count = ttService.countType(id).intValue();
		
		return  new ResponseEntity<Integer>(count,HttpStatus.OK);

	}

	
	protected boolean isShowPrices(org.springframework.security.core.userdetails.User authUser) {
		
		try {
			if(!authUser.getAuthorities().isEmpty())
				return true;
			else 
				return false;
		}
		catch(Exception exc) {
			return false;
		}
	}

}
