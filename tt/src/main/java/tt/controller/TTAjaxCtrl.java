package tt.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tt.bean.AdminSessionBean;

@Controller
public class TTAjaxCtrl {
	
	@Autowired
	AdminSessionBean adminSessBean;

	@ResponseBody
	@RequestMapping(value = "/clearErrors", method = RequestMethod.GET)
	public HttpStatus  clearErrors() 
	{
		
		//System.out.println("clearErrors " +sessBean.getErrorList());
		adminSessBean.clearError();
		//System.out.println("clearErrors " +sessBean.getErrorList());
		
		return  HttpStatus.OK;
	}

}
