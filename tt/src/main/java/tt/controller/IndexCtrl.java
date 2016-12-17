package tt.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tt.bean.AppBean;
import tt.bean.SessionBean;
import tt.model.DirNomenclature;
import tt.model.Tail;
import tt.model.User;
import tt.modelattribute.MA_search;
import tt.service.TTServiceImpl;
import tt.util.*;



@Controller
@Scope("session")
public class IndexCtrl {
	
	
	@Autowired
	private AppBean appBean;
	
	@Autowired
	SessionBean sessBean;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	
	@Autowired
	MA_search mA_search;
	
	@RequestMapping(value = {"/index","/"} , method = RequestMethod.GET)
	public ModelAndView  index(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("index");
		User user = new User();
		user.setName("name "+user.getId());
		user.setPassword("pass "+user.getId());
		
		
		model.addObject("mA_search",mA_search);
		
		//model.addObject("tails",ttService.getTailsList());
		model.addObject("tails",ttService.tailSetNomenclature(mA_search.getPn(), mA_search.getGndr()));
		model.addObject("version",appBean.getVersion());
		model.addObject("providers", ttService.getProviderList());
		model.addObject("categories", ttService.getNomenclGroupList());
		model.addObject("genders", ttService.getGenderList());
		
		model.addObject("UPLOAD_FILE_PATH", Constants.UPLOAD_FILE_PATH);
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
		else {
			
			//SecurityContextHolder.
		}

		if (logout != null) {
			SecurityContextHolder.clearContext();
			session.invalidate();
			model = new ModelAndView("redirect:/admin");
			
		}
		
		return model;
	}
	
	
	@RequestMapping(value = {"/search"} , method = RequestMethod.GET)
	public String  searchGet(HttpSession session, @ModelAttribute("mA_search") MA_search mA_search, Model model) 
	{
		 //mIndex = session.getAttribute("mIndex") == null?new MIndex():(MIndex)session.getAttribute("mIndex");
		//session.setAttribute("mA_search", mA_search);
		this.mA_search = mA_search;
		
		model.addAttribute("version",appBean.getVersion());
		
		model.addAttribute("mA_search", mA_search);
		
		model.addAttribute("providers", ttService.getProviderList());
		model.addAttribute("categories", ttService.getNomenclGroupList());
		model.addAttribute("genders", ttService.getGenderList());

		model.addAttribute("tails",ttService.tailSetNomenclature(mA_search.getPn(), mA_search.getGndr()));
		
		return "index";
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

	@RequestMapping(value = {"/product-details{id}"} , method = RequestMethod.GET)
	public ModelAndView  product_details(HttpSession session, @RequestParam(value = "id",   required=false) Long id) 
	{
		ModelAndView model = new ModelAndView("product-details");
		
		//System.out.println(sessBean.getOrders());
		model.addObject("sessBean", sessBean);
		
		if(id != null)
		{
			DirNomenclature dn = (DirNomenclature)ttService.getObject(DirNomenclature.class, id); 
			
			model.addObject("nomenclature", dn);
			model.addObject("tails", ttService.getTailsList(dn.getId()));
			model.addObject("provider", dn.getTails().iterator().next().getDirProvider());
			model.addObject("price", dn.getTails().iterator().next().getFirstPrice());
		}
		
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
