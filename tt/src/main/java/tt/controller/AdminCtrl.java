package tt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import tt.bean.AppBean;
import tt.bean.SessionBean;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.Store;
import tt.modelattribute.IMAmodel;
import tt.modelattribute.MA_loadNomencl;
import tt.modelattribute.MA_loadProvider;
import tt.service.TTServiceImpl;
import tt.util.FileUpload;



@Controller
@RequestMapping(value = {"/admin"} , method = RequestMethod.GET)
public class AdminCtrl {
	
	
	@Autowired
	private AppBean appBean;

	@Autowired
	private FileUpload fileUpload;
	
	@Autowired
	private SessionBean sessBean;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work
	

	
	//@Loggable
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView  manage(HttpSession session, 
				@RequestParam(value = "act",   defaultValue = "0") String act,
				@RequestParam(value = "error",   defaultValue = "") String error) 
	{
		ModelAndView model = new ModelAndView("admin/main");
		
		//System.out.println(ttService.getStoreList());
		
		switch (act)
		{
			case "1":
				model = new ModelAndView("admin/addProvider");
				model.addObject("dirProviders",ttService.getProviderList());
				//model.addObject("error",sessBean.getErrorList());
			break;

			case "2":
				model = new ModelAndView("admin/addNomencl");
				model.addObject("dirNomencls", ttService.getNomenclatureList());
			break;
			
		}
		
		model.addObject("error",sessBean.getErrorList());
		model.addObject("sessionBean",sessBean);
		
		
		return model;
	}
	
	
	
	@RequestMapping(value = "addNomencl" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   addNomencl(HttpSession session, @Valid @ModelAttribute("addNomenclForm") DirNomenclature dirNomenclature,
			BindingResult result,
			@ModelAttribute  MultipartFile file,
			@RequestParam(value = "id_dir_nomenclature",   required=false) Long id_dir_nomenclature) 
	{
		
		ModelAndView model = new ModelAndView("redirect:/admin?act=2");

		if(result.hasErrors())
		{
			sessBean.addError("Правильно введите данные!");
			return model;
		}
		if(id_dir_nomenclature != null && id_dir_nomenclature.longValue()>0) 
			dirNomenclature.setId(id_dir_nomenclature);
		
		//ttService.addProvider(dirNomenclature);
		
	    return model;
	}

	@RequestMapping(value = "addProvider" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processProvider(HttpSession session, @Valid @ModelAttribute("addProviderForm") DirProvider dirProvider,
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
	
	

	//@SuppressWarnings("static-access")
	@RequestMapping(value = "addFileNomencl" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileProvider( @ModelAttribute  MultipartFile file,
										@Valid MA_loadNomencl mA_loadNomencl ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			sessBean.addError("Правильно введите данные!");
			return model;
		}
		
		if(mA_loadNomencl.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadNomencl);
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				sessBean.getErrorList().add("Параметры не записаны! ");
			}
			
		}
			
		sessBean.setmA_loadNomencl(mA_loadNomencl);

		try {

					TreeSet<DirNomenclature> sP = new TreeSet<DirNomenclature>();
					sP.addAll((List<DirNomenclature>) fileUpload.process(new DirNomenclature(),file, mA_loadNomencl));
		
					for(DirNomenclature dN: sP) 
					{
						try {
							ttService.addNomenclature(dN);
							
						}
						catch(org.springframework.dao.DataIntegrityViolationException dve) {
							//dve.printStackTrace(); 
							sessBean.getErrorList().add(dN.getName()+" уже существует! ");
						}
						
					}

			}
			catch (java.lang.NumberFormatException nfe) {
						nfe.printStackTrace();
						sessBean.addError("Ошибка формата данных !");
			}
			catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						sessBean.addError("Ошибка загрузки файла!");
			}


		return model;
	}
	
	
	


	@RequestMapping(value = "addFileProvider" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileProvidere( @ModelAttribute  MultipartFile file,
										@Valid MA_loadProvider mA_loadProvider ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act)
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			sessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		
		//System.out.println("sessBean - " +sessBean);
		
		sessBean.setmA_loadProvider(mA_loadProvider);
				
		try {

					TreeSet<DirProvider> sP = new TreeSet<DirProvider>();
					sP.addAll((List<DirProvider>) fileUpload.process(new DirProvider(),file, mA_loadProvider));
	
					for(DirProvider dp: sP) 
					{
						try {
							ttService.addProvider(dp);
						}
						catch(org.springframework.dao.DataIntegrityViolationException dve) {
							//dve.printStackTrace(); 
							sessBean.getErrorList().add(dp.getName()+" уже существует! ");
						}
						
					}
	
		}
		catch (java.lang.NumberFormatException nfe) {
					nfe.printStackTrace();
					sessBean.addError("Ошибка формата данных !");
		}
		catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					sessBean.addError("Ошибка загрузки файла!");
		}
		
		
		return model;
	}


	private Store objectToBytes(IMAmodel IMAmodel) throws IOException{
		// TODO Auto-generated method stub
		Store store = new Store();

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
		objOut.writeObject(IMAmodel);
		objOut.close();
		byteOut.close();
		byte[] bytes = byteOut.toByteArray();
		
		store.setSerialVersionUID(IMAmodel.getSerialversionuid());
		store.setBytearray(bytes);

		return store;
	}


}
