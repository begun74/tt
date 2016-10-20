package tt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tt.service.TTServiceImpl;



@Controller
public class IndexCtrl {
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	
	@RequestMapping(value = {"/index","/"} , method = RequestMethod.GET)
	public ModelAndView  index(HttpSession session, @RequestParam(value = "pg",   required=false) Long id_partgroup) 
	{
		ModelAndView model = new ModelAndView("index");
		System.out.println(""+ttService.getUserList());
		
		return model;
	}
	
	@RequestMapping(value = {"/messageUrl"} , method = RequestMethod.GET)
	public ModelAndView  messageUrl(HttpSession session) 
	{
		ModelAndView model = new ModelAndView("index");
		System.out.println(""+ttService.getUserList());
		
		return model;
	}

}
