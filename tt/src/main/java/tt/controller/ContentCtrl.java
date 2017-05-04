package tt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tt.bean.AdminSessionBean;
import tt.bean.AppBean;
import tt.service.TTServiceImpl;
import tt.util.FileUpload;

@Controller
@Scope("session")
@RequestMapping(value = {"/admin/content"} , method = RequestMethod.GET)
public class ContentCtrl {

	@Autowired
	private AppBean appBean;

	@Autowired
	private FileUpload fileUpload;
	
	@Autowired
	private AdminSessionBean adminSessBean;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView  manage(HttpSession session, 
				@RequestParam(value = "act",   defaultValue = "0") String act,
				@RequestParam(value = "error",   defaultValue = "") String error) 
	{
		ModelAndView model = new ModelAndView("admin/content/adverts");
		
		//System.out.println(ttService.getStoreList());
		
		switch (act)
		{
			case "1":
				model = new ModelAndView("admin/addProvider");
			break;
		}
		
		model.addObject("error",adminSessBean.getErrorList().toString().length() > 512 ?adminSessBean.getErrorList().toString().substring(0, 512)+" ...":adminSessBean.getErrorList());
		model.addObject("sessionBean",adminSessBean);
		
		return model;
	}
	
}
