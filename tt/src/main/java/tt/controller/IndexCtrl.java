package tt.controller;


import java.io.File;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import tt.bean.AppBean;
import tt.bean.SessionBean;
import tt.config.CustomAuthenticationSuccessHandler;
import tt.model.ContactUsMessages;
import tt.model.DirNomenclature;
import tt.model.Order;
import tt.model.OrderItems;
import tt.model.Tail;
import tt.model.User;
import tt.modelattribute.MA_search;
import tt.service.TTServiceImpl;
import tt.util.*;




@Controller
@Scope("session")
public class IndexCtrl implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -904284592001974037L;
	
	private  static  final  Log  LOG  =  LogFactory.getLog(IndexCtrl.class);

	@Autowired
	private AppBean appBean;
	
	@Autowired
	SessionBean sessBean;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	
	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	MA_search mA_search;
	
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@ExceptionHandler(Exception.class) 
    public ModelAndView handleExceptions(HttpServletRequest req, Exception anExc) {
		ModelAndView model = new ModelAndView("redirect:/index");
		
		
        anExc.printStackTrace(); // do something better than this ;)
        
        return model;
    }
	
	
	@RequestMapping(value = {"/search","/index","/"} , method = RequestMethod.GET)
	public String  searchGet(HttpSession session, @ModelAttribute("product_filter") MA_search mA_search, Model model, 
								@RequestParam(value = "p", defaultValue = "1") int p) 
	{
		this.mA_search = mA_search;
		
		model.addAttribute("version",appBean.getVersion());
		
		model.addAttribute("mA_search", this.mA_search);
		
		//model.addAttribute("providers", ttService.getProviderList());
		model.addAttribute("providers", ttService.getProviderListInTails());
		
		//model.addAttribute("categories", ttService.getNomenclGroupList());
		model.addAttribute("categories", ttService.getNomenclGroupListInTails());

		model.addAttribute("genders", ttService.getGenderList());
		model.addAttribute("types", ttService.getNomenclGroupRootListInTails());

		
		Object[] resultNomInTails = ttService.getNomenclInTails(this.mA_search, p , mA_search.getP_p());
		
		model.addAttribute("allItems",resultNomInTails[0]);
		model.addAttribute("tails",resultNomInTails[1]);
		model.addAttribute("maxDateInTails",resultNomInTails[2]);
		
		
		model.addAttribute("isShowPrices", isShowPrices((org.springframework.security.core.userdetails.User)session.getAttribute("authUser")));
		
		model.addAttribute("sessBean", sessBean);
		
		System.gc();
		
		return "index";
	}
	
	@RequestMapping(value = {"/find"} , method = RequestMethod.GET)
	public ModelAndView  find(HttpSession session, @RequestParam(value = "text",required = true) String text, @RequestParam(value = "p", defaultValue = "1") int p, 
			@RequestParam(value = "perPage", defaultValue = "9") int perPage) 
	{
		ModelAndView model = new ModelAndView("find");
		model.addObject("mA_search", mA_search);
		model.addObject("tails", ttService.findByText(text) );
		model.addObject("findText",text);
		model.addObject("isShowPrices", isShowPrices((org.springframework.security.core.userdetails.User)session.getAttribute("authUser")));
		
		return model;
	}

/*	
	@RequestMapping(value = {"/index","/"} , method = RequestMethod.GET)
	public ModelAndView  index(HttpSession session, @RequestParam(value = "p", defaultValue = "1") int p, 
								@RequestParam(value = "perPage", defaultValue = "9") int perPage) 
	{
		ModelAndView model = new ModelAndView("index");
		User user = new User();
		user.setName("name "+user.getId());
		user.setPassword("pass "+user.getId());
		
		
		model.addObject("mA_search",mA_search);
		
		//model.addObject("tails",ttService.getTailsList());
		//model.addObject("tails",ttService.tailSetNomenclature(mA_search.getPn(), mA_search.getGndr(), mA_search.getCat(), p , perPage));
		//model.addObject("tails",ttService.tailNomenclatureSet(mA_search.getPn(), mA_search.getGndr(), mA_search.getCat(), p , perPage));
		
		model.addObject("tails",ttService.getNomenclInTails(mA_search, p , perPage));
		
		model.addObject("version",appBean.getVersion());
		//model.addObject("providers", ttService.getProviderList());
		model.addObject("providers", ttService.getProviderListInTails());
		
		//model.addObject("categories", ttService.getNomenclGroupList());
		model.addObject("categories", ttService.getNomenclGroupListInTails());
		
		model.addObject("genders", ttService.getGenderList());
		model.addObject("types", ttService.getNomenclGroupRootListInTails());
		
		model.addObject("isShowPrices", isShowPrices((org.springframework.security.core.userdetails.User)session.getAttribute("authUser")));
		
		
		model.addObject("UPLOAD_FILE_PATH", Constants.UPLOAD_FILE_PATH);
		return model;
	}
	*/
	
	@RequestMapping(value = {"/login"} , method = RequestMethod.GET)
	public ModelAndView  login(HttpSession session, HttpServletRequest request, @RequestParam(value = "error",required = false) String error,
			@RequestParam(value = "logout",	required = false) String logout) 
	{
	    

		ModelAndView model = new ModelAndView("login");

		org.springframework.security.core.userdetails.User authUser = ( (org.springframework.security.core.userdetails.User) session.getAttribute("authUser"));
		
		if(authUser != null && !authUser.getAuthorities().isEmpty()) 
			System.out.println(authUser.getUsername()+" - "+authUser.getAuthorities());
		
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
			return model;
		}
		
		else if (logout != null) {
			SecurityContextHolder.clearContext();
			session.invalidate();
			model = new ModelAndView("redirect:/admin");
			return model;
		}
		
		else
		{
			
			
		}
		
		return model;
	}
	
	@RequestMapping(value = {"/action"} , method = RequestMethod.GET)
	public ModelAndView  action(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("common/action");
		//System.out.println(""+ttService.getUserList());
		
		return model;
	}
	
	@RequestMapping(value = {"/our_shops"} , method = RequestMethod.GET)
	public ModelAndView  our_shops(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("our_shops");
		//System.out.println(""+ttService.getUserList());
		
		return model;
	}

	@RequestMapping(value = {"/vacancies"} , method = RequestMethod.GET)
	public ModelAndView  vacancies(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("vacancies");
		//System.out.println(""+ttService.getUserList());
		
		return model;
	}

	@RequestMapping(value = {"/about_company"} , method = RequestMethod.GET)
	public ModelAndView  about_company(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("about_company");
		//System.out.println(""+ttService.getUserList());
		
		return model;
	}

	
	@RequestMapping(value = {"/showMessage"} , method = RequestMethod.GET)
	public ModelAndView  messageUrl(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("index");
		//System.out.println(""+ttService.getUserList());
		
		return model;
	}

	
	@RequestMapping(value = {"/404.html"} , method = RequestMethod.GET)
	public ModelAndView  not_found(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("404");
		
		return model;
	}


	@RequestMapping(value = {"/contact-us"} , method = RequestMethod.GET)
	public ModelAndView  contact_us_GET(HttpSession session, @RequestParam(value = "error",required = false) String error) 
	{
		ModelAndView model = new ModelAndView("contact-us");
		
		return model;
	}

	@RequestMapping(value = {"/contactus"} , method = RequestMethod.POST)
	public ModelAndView  contact_us_POST(HttpSession session, @Valid @ModelAttribute("contactUsMessages") ContactUsMessages contactUsMessages, BindingResult result) 
	{
		ModelAndView model = new ModelAndView("redirect:/contact-us");
		
		System.out.println("sessBean.getErrorMap() - "+ sessBean.getErrorMap());
		
		Timestamp ts = new Timestamp(new Date().getTime());
		
		if(result.hasErrors())
		{
		
			sessBean.getErrorMap().put("contactus_error", ts);
			
			System.out.println("sessBean.getErrorMap() - "+ sessBean.getErrorMap());
			return model;
		}
		
		contactUsMessages.setCreation_date(ts);
		
		ttService.addContactUsMessages(contactUsMessages);
		//sessBean.getErrorMap().put("contactus_ok", ts);
		
		//System.out.println("sessBean.getErrorMap() - "+ sessBean.getErrorMap());
		return model;
	}
	
	
	@RequestMapping(value = {"/product-details{id}"} , method = RequestMethod.GET)
	public ModelAndView  product_details(HttpSession session, @RequestParam(value = "id",   required=false) Long id) 
	{
		ModelAndView model = new ModelAndView("product-details");
		
		//System.out.println(sessBean.getOrders());
		model.addObject("sessBean", sessBean);
		
		try
		{
			DirNomenclature dn = (DirNomenclature)ttService.getObject(DirNomenclature.class, id); 
			
			//List<DirNomenclature> popNomencl = ttService.getPopularDirNomenclature();
			List<DirNomenclature> popNomencl = ttService.getNomenclOfProvider(id);
			
			model.addObject("nomenclature", dn);
			model.addObject("popNomencl", popNomencl);
			//!-- model.addObject("tails", ttService.getTailsList(dn.getId()));
			model.addObject("provider",dn.getDirProvider());
			//model.addObject("provider", dn.getTails().iterator().next().getDirProvider());
			
			Iterator<Tail> iter = dn.getTails().iterator();
			
			while(iter.hasNext())
			{
				synchronized (iter) {
					if(iter.next().getDestruction_date() != null)
						iter.remove();
				}
			}
			
			Tail tail = dn.getTails().iterator().next();
			
			model.addObject("firstprice", tail.getFirstPrice());

			if(isShowPrices((org.springframework.security.core.userdetails.User)session.getAttribute("authUser")))
			{
				//model.addObject("price", tail.getFirstPrice());
				model.addObject("price", tail.getOpt_price());
			}
			else
				model.addObject("price", tail.getRozn_price());
			
		}
		catch(java.util.NoSuchElementException nsee) {
			System.out.println("ERROR: IndexCtrl.product_detail("+id+")");
		}
		catch(Exception exc) {
			System.out.println("ERROR: IndexCtrl.product_detail("+id+")");
			exc.printStackTrace();
			return new ModelAndView("redirect:/error404");
		}
		
		return model;
	}
	

	@RequestMapping(value = {"/product-details2{id}"} , method = RequestMethod.GET)
	public ModelAndView  product_details2(HttpSession session, @RequestParam(value = "id",   required=false) Long id) 
	{
		ModelAndView model = new ModelAndView("product-details2");
		
		//System.out.println(sessBean.getOrders());
		model.addObject("sessBean", sessBean);
		
		try
		{
			DirNomenclature dn = (DirNomenclature)ttService.getObject(DirNomenclature.class, id); 
			
			List<DirNomenclature> popNomencl = ttService.getPopularDirNomenclature();
			
			model.addObject("nomenclature", dn);
			model.addObject("popNomencl", popNomencl);
			//!-- model.addObject("tails", ttService.getTailsList(dn.getId()));
			model.addObject("provider",dn.getDirProvider());
			//model.addObject("provider", dn.getTails().iterator().next().getDirProvider());
			
			Iterator<Tail> iter = dn.getTails().iterator();
			
			while(iter.hasNext())
			{
				synchronized (iter) {
					if(iter.next().getDestruction_date() != null)
						iter.remove();
				}
			}
			
			Tail tail = dn.getTails().iterator().next();
			
			model.addObject("firstprice", tail.getFirstPrice());

			if(isShowPrices((org.springframework.security.core.userdetails.User)session.getAttribute("authUser")))
			{
				//System.out.println("dn.getTails() - " +dn.getTails());
				
				
				//System.out.println("dn.getTails() - " +dn.getTails());
				
				
				//model.addObject("price", tail.getFirstPrice());
				model.addObject("price", tail.getOpt_price());
				
			}
			else
				model.addObject("price", tail.getRozn_price());
			
			//model.addObject("isShowPrices", isShowPrices((org.springframework.security.core.userdetails.User)session.getAttribute("authUser")));

		}
		catch(Exception exc) {
			System.out.println("ERROR: IndexCtrl.product_detail("+id+")");
			exc.printStackTrace();
			return new ModelAndView("redirect:/error404");
		}
		
		return model;
	}
	
	
	@RequestMapping(value = "/delOrder{npp}", method = RequestMethod.GET)
	public ModelAndView delOrder(HttpSession session, @RequestParam ("npp") int npp) 
	{
		ModelAndView model = new ModelAndView("redirect:/cart");
		//sessBean.getOrders()(order);
		
		Iterator<OrderItems> iter = sessBean.getOrderItems().listIterator();
		
		while(iter.hasNext())
		{
			OrderItems order = iter.next();
			if(order.getNpp() == npp)
				iter.remove();
		}
		
		session.setAttribute("sessBean", sessBean);
		
		
		
		System.out.println("Delete Order  - "+npp);
		
		return model;
	}


	@RequestMapping(value = {"/cart"} , method = RequestMethod.GET)
	public ModelAndView  cart(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("cart2");
		
	
		model.addObject("orderItems", sessBean.getOrderItems());
		
		return model;
	}

	
	@RequestMapping(value = {"/createOrder"} , method = RequestMethod.POST)
	public ModelAndView  createOrder(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("orderForm") @Valid  Order order,
			BindingResult result) 
	{
		final ServletContext servletContext = request.getSession().getServletContext();
	    final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    final String temperotyFilePath = tempDirectory.getAbsolutePath();
	    
		final String pathToFont =  servletContext.getRealPath("/resources/forRussText/");

		ModelAndView model = new ModelAndView("redirect:/cart");
		
		if(result.hasErrors())
		{
			session.setAttribute("error", "Введите корректные данные");
			return model;
		}
		
		//Order order = new Order();
		order.setCreation_date(new Timestamp(new Date().getTime()));
				
		for(OrderItems oI: sessBean.getOrderItems())
		{
			oI.setOrder(order);
		}
		
		
		order.setOrderItems(sessBean.getOrderItems());
		//System.out.println("Order - "+order);
		
		ttService.addOrder(order);
		
		sendMailService.sendOrder(order);
		
		sessBean.getOrderItems().clear();
		model.addObject("orderItems", sessBean.getOrderItems());
		model.addObject("last_order",order);
		
		return model;
	}
	
	
	@RequestMapping(value = {"/cooperation"} , method = RequestMethod.GET)
	public ModelAndView  cooperation(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("cooperation");
		
		return model;
	}

	
	@RequestMapping(value = {"/checkout.html"} , method = RequestMethod.GET)
	public ModelAndView  checkout(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("checkout");
		
		return model;
	}


	@RequestMapping(value = {"/shop.html"} , method = RequestMethod.GET)
	public ModelAndView  shop(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("shop");
		
		return model;
	}
	
	
	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	public ModelAndView  test(HttpSession session, 
				@RequestParam(value = "act",   defaultValue = "0") String act,
				@RequestParam(value = "error",   defaultValue = "") String error) 
	{
		ModelAndView model = new ModelAndView("admin/test2");
		
		return model;
	}


	@RequestMapping(value = {"/error404"} , method = RequestMethod.GET)
	public ModelAndView  err404(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("404");
		
		return model;
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
