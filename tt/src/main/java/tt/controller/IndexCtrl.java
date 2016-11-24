package tt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tt.model.User;
import tt.service.TTServiceImpl;



@Controller
public class IndexCtrl {
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	
	@RequestMapping(value = {"/index","/"} , method = RequestMethod.GET)
	public ModelAndView  index(HttpSession session, @RequestParam(value = "pg",   required=false) Long id_partgroup) 
	{
		ModelAndView model = new ModelAndView("index");
		User user = new User();
		user.setName("name "+user.getId());
		user.setPassword("pass "+user.getId());
		
		//ttService.addUser(user);
		
		//System.out.println(""+ttService.getUserList());
		
		return model;
	}
	
	@RequestMapping(value = {"/login"} , method = RequestMethod.GET)
	public ModelAndView  login(HttpSession session, @RequestParam(value = "error",required = false) String error,
			@RequestParam(value = "logout",	required = false) String logout) 
	{
	    
		ModelAndView model = new ModelAndView("login");
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}

		if (logout != null) {
			SecurityContextHolder.clearContext();
			session.invalidate();
			//model.setViewName("index");
		}
		
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


	@RequestMapping(value = {"/contact-us.html"} , method = RequestMethod.GET)
	public ModelAndView  contact_us(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("contact-us");
		
		return model;
	}

	@RequestMapping(value = {"/product-details.html"} , method = RequestMethod.GET)
	public ModelAndView  product_details(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("product-details");
		
		return model;
	}

	@RequestMapping(value = {"/cart.html"} , method = RequestMethod.GET)
	public ModelAndView  cart(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("cart");
		
		return model;
	}

	@RequestMapping(value = {"/checkout.html"} , method = RequestMethod.GET)
	public ModelAndView  checkout(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("checkout");
		
		return model;
	}

	@RequestMapping(value = {"/login.html"} , method = RequestMethod.GET)
	public ModelAndView  login(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("login");
		
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

}
