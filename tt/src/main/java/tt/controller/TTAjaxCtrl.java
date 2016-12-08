package tt.controller;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import tt.model.DirNomenclature;
import tt.model.Tail;
import tt.service.TTServiceImpl;


@Controller
public class TTAjaxCtrl {
	
	@Autowired
	AdminSessionBean adminSessBean;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	

	@ResponseBody
	@RequestMapping(value = "/clearErrors", method = RequestMethod.GET)
	public HttpStatus  clearErrors() 
	{
		
		//System.out.println("clearErrors " +sessBean.getErrorList());
		adminSessBean.clearError();
		//System.out.println("clearErrors " +sessBean.getErrorList());
		
		return  HttpStatus.OK;
	}
	
	
	
	
	@RequestMapping(value = "/productDetail{id}", method = RequestMethod.GET)
	public @ResponseBody Set<Tail> productDetail(@RequestParam ("id") long id) 
	{
		
		DirNomenclature dirNomenclature = (DirNomenclature)ttService.getObject(DirNomenclature.class, id);
		
		Set<Tail> tails = dirNomenclature.getTails();
		
		for(Tail tail: tails)
			tail.setDirNomenclature(null); //!! Нужно чтобы не было зацикливания т.к. в DirNomenclature есть Tail, а в Tail есть DirNomenclature и так по кругу
		
		
		return  tails;
	}
	

}
