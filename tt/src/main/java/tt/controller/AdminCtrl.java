package tt.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tt.annotation.Loggable;
import tt.model.DirProvider;
import tt.service.TTServiceImpl;
import tt.util.FileUpload;



@Controller
@RequestMapping(value = {"/admin"} , method = RequestMethod.GET)
public class AdminCtrl {
	
	@Autowired
	FileUpload fileUpload;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	
	//@Loggable
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView  manage(HttpSession session, 
				@RequestParam(value = "act",   defaultValue = "0") String act,
				@RequestParam(value = "error",   defaultValue = "") String error) 
	{
		ModelAndView model = new ModelAndView("admin/main");
		
		switch (act)
		{
			case "1":
				model = new ModelAndView("admin/addProvider");
				model.addObject("dirProviders",ttService.getProviderList());
			break;

			
		}
		
		model.addObject("error", error);
		
		return model;
	}
	
	
	
	@RequestMapping(value = "addProvider" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processBrand(HttpSession session, @Valid @ModelAttribute("addProviderForm") DirProvider dirProvider,
			BindingResult result,
			@ModelAttribute  MultipartFile file,
			@RequestParam(value = "id_dir_provider",   required=false) Long id_dir_provider) 
	{
		
		ModelAndView model = new ModelAndView("redirect:/admin?act=1");

		if(result.hasErrors())
		{
			model.addObject("error", result.getFieldError().getDefaultMessage());
			return model;
		}
		if(id_dir_provider != null && id_dir_provider.longValue()>0) 
			dirProvider.setId(id_dir_provider);
		
		ttService.addProvider(dirProvider);
		
	    return model;
	}

	@RequestMapping(value = "delObject")
	public String  delObject(HttpSession session,@RequestParam(value = "id",   defaultValue = "-1") long id ,@RequestParam(value = "act",   defaultValue = "-1") int act
			,@RequestParam(value = "clazz",  required=true, defaultValue = "") String clazz) 
	{

		try {
			ttService.delObject(ttService.getObject(Class.forName("tt.model."+clazz), id));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin?act="+act;
	}
	
	
	@RequestMapping(value = "addFile" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processPhoto( @ModelAttribute  MultipartFile file, @RequestParam(value = "act",   defaultValue = "-1") int act,
										@RequestParam(value = "row",   defaultValue = "1") int row , @RequestParam(value = "cols",   defaultValue = "1") String cols) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);

		fileUpload.process(file);
		System.out.println(row+"  "+cols);
	    return model;
	}
	
	//@Loggable
	public void doAdmin() {
		
		//return new ModelAndView("admin/admin2") ;
	}

}
