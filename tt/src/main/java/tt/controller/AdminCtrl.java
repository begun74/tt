package tt.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
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
import tt.bean.AdminSessionBean;
import tt.model.DirNomenclGroup;
import tt.model.DirNomenclGroupRoot;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.Tail;
import tt.modelattribute.IMAmodel;
import tt.modelattribute.MA_loadNomencl;
import tt.modelattribute.MA_loadNomenclGroup;
import tt.modelattribute.MA_loadNomenclGroupRoot;
import tt.modelattribute.MA_loadProvider;
import tt.modelattribute.MA_loadTail;
import tt.modelattribute.MA_loadTempTail;
import tt.service.TTServiceImpl;
import tt.util.FileUpload;
import tt.util.autoLoad.Handler;
import tt.util.autoLoad.MainAutoLoad;



@Controller
@Scope("session")
@RequestMapping(value = {"/admin"} , method = RequestMethod.GET)
public class AdminCtrl {
	
	
	@Autowired
	private AppBean appBean;

	@Autowired
	private FileUpload fileUpload;
	
	@Autowired
	private AdminSessionBean adminSessBean;
	
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
			break;

			case "2":
				model = new ModelAndView("admin/addNomencl");
				model.addObject("dirNomencls", ttService.getNomenclatureList());
			break;

			case "3":
				model = new ModelAndView("admin/addTails");
				model.addObject("tempTails", adminSessBean.getTempListTails());
				model.addObject("tails", ttService.getTailsList());
			break;

			case "4":
				model = new ModelAndView("admin/addNomenclGroup");
				model.addObject("dirNomenclGroups", ttService.getNomenclGroupList());
			break;

			case "5":
				model = new ModelAndView("admin/autoLoad");
				model.addObject("autoLoadIMAmodels", appBean.getAutoLoad_IMAmodels());
			break;

			case "6":
				model = new ModelAndView("admin/addNomenclGroupRoot");
				model.addObject("dirNomenclGroupRoots", ttService.getNomenclGroupRootList());
			break;
		}
		
		model.addObject("error",adminSessBean.getErrorList());
		model.addObject("sessionBean",adminSessBean);
		model.addObject("providers", ttService.getProviderList());
		
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
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		if(id_dir_nomenclature != null && id_dir_nomenclature.longValue()>0) 
			dirNomenclature.setId(id_dir_nomenclature);
		
		//ttService.addProvider(dirNomenclature);
		
	    return model;
	}


	@RequestMapping(value = "addPhotoNomencl" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   addPhotoNomencl(HttpSession session, 
			@ModelAttribute  MultipartFile photoFile,
			@RequestParam(value = "codeNomencl", required=true) Long codeNomencl) 
	{
		
		ModelAndView model = new ModelAndView("redirect:/admin?act=2");

		//fileUpload.downloadPhoto(System.currentTimeMillis(),"\\\\192.168.0.9\\интернет магазин\\ТОП\\Юбка\\А");
		
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


	@RequestMapping(value = "addTails" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   addTails( @Valid @ModelAttribute("addTailForm") Tail tail,
									BindingResult result,
									@ModelAttribute  MultipartFile file,
									@RequestParam(value = "id_tail",   required=false) Long id_tail) 
	{
		
		ModelAndView model = new ModelAndView("redirect:/admin?act=3");

		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		if(id_tail != null && id_tail.longValue()>0) 
			tail.setId(id_tail);
		
		//ttService.addProvider(dirNomenclature);
		
	    return model;
	}
	
	
	@RequestMapping(value = "delObject")
	public String  delObject(HttpSession session,@RequestParam(value = "id",   defaultValue = "-1") Long id ,@RequestParam(value = "act",   defaultValue = "-1") int act
			,@RequestParam(value = "clazz",  required=true, defaultValue = "") String clazz) 
	{

		try {
			
			ttService.delObject(ttService.getObject(Class.forName("tt.model."+clazz), id));
			
		} 
		catch( org.springframework.dao.DataIntegrityViolationException dve) {
			adminSessBean.addError("Нельзя удалить!");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin?act="+act;
	}
	
	

	@RequestMapping(value = "addFileNomencl" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileProvider( @ModelAttribute  MultipartFile file,
										@Valid MA_loadNomencl mA_loadNomencl ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		if(mA_loadNomencl.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadNomencl);
				adminSessBean.setmA_loadNomencl(mA_loadNomencl);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}
		


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
							adminSessBean.getErrorList().add(dN.getName()+" уже существует! ");
						}
					}


			}
			catch (java.lang.NumberFormatException nfe) {
				nfe.printStackTrace();
				adminSessBean.addError(nfe.getMessage());
				
			}
			catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						adminSessBean.addError("Ошибка загрузки файла!");
			}


		return model;
	}
	
	
	
	@RequestMapping(value = "addFileNomenclGroup" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileNomenclGroup( @ModelAttribute  MultipartFile file,
										@Valid MA_loadNomenclGroup mA_loadNomenclGroup ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		if(mA_loadNomenclGroup.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadNomenclGroup);
				adminSessBean.setmA_loadNomenclGroup(mA_loadNomenclGroup);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}
		


		try {

					TreeSet<DirNomenclGroup> lDNG = new TreeSet<DirNomenclGroup>();
					lDNG.addAll((List<DirNomenclGroup>) fileUpload.process(new DirNomenclGroup(),file, mA_loadNomenclGroup));
		
					for(DirNomenclGroup dNG: lDNG) 
					{
						try {
							ttService.addNomenclGroup(dNG);
							
						}
						catch(org.springframework.dao.DataIntegrityViolationException dve) {
							//dve.printStackTrace(); 
							adminSessBean.getErrorList().add(dNG.getName()+" уже существует! ");
						}
					}


			}
			catch (java.lang.NumberFormatException nfe) {
				nfe.printStackTrace();
				adminSessBean.addError(nfe.getMessage());
				
			}
			catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						adminSessBean.addError("Ошибка загрузки файла!");
			}


		return model;
	}

	
	@RequestMapping(value = "addFileNomenclGroupRoot" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileNomenclGroupRoot( @ModelAttribute  MultipartFile file,
										@Valid MA_loadNomenclGroupRoot mA_loadNomenclGroupRoot ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		if(mA_loadNomenclGroupRoot.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadNomenclGroupRoot);
				adminSessBean.setmA_loadNomenclGroupRoot(mA_loadNomenclGroupRoot);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}
		


		try {

					TreeSet<DirNomenclGroupRoot> lDNGR = new TreeSet<DirNomenclGroupRoot>();
					lDNGR.addAll((List<DirNomenclGroupRoot>) fileUpload.process(new DirNomenclGroupRoot(),file, mA_loadNomenclGroupRoot));
		
					for(DirNomenclGroupRoot dNGR: lDNGR) 
					{
						try {
							ttService.addNomenclGroupRoot(dNGR);
							
						}
						catch(org.springframework.dao.DataIntegrityViolationException dve) {
							//dve.printStackTrace(); 
							adminSessBean.getErrorList().add(dNGR.getName()+" уже существует! ");
						}
					}


			}
			catch (java.lang.NumberFormatException nfe) {
				nfe.printStackTrace();
				adminSessBean.addError(nfe.getMessage());
				
			}
			catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						adminSessBean.addError("Ошибка загрузки файла!");
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
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		
		if(mA_loadProvider.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadProvider);
				adminSessBean.setmA_loadProvider(mA_loadProvider);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}
		
		adminSessBean.setmA_loadProvider(mA_loadProvider);
				
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
							adminSessBean.getErrorList().add(dp.getName()+" уже существует! ");
						}
						
					}
					
					
	
		}
		catch (java.lang.NumberFormatException nfe) {
			nfe.printStackTrace();
			adminSessBean.addError(nfe.getMessage());
			
		}
		catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					adminSessBean.addError("Ошибка загрузки файла!");
		}
		
		
		return model;
	}


	
	@RequestMapping(value = "addFileTail" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileTail(@ModelAttribute MA_loadTempTail mA_loadTempTail, @RequestParam(value = "act",   defaultValue = "-1", required=true) int act)
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
				
		try {

					synchronized(this) {

						Iterator<Tail> iter_lT = adminSessBean.getTempListTails().iterator();
						
						while(iter_lT.hasNext()) 
						{
								Tail tail = iter_lT.next();
								if( mA_loadTempTail.getTailIndex().contains(tail.getIndex()) ) //Проверка отмечена ли запись на загрузку
								{
									ttService.addTail(tail);
									iter_lT.remove();
								}
						}
						
					}
					//System.out.println("sessBean.getTempListTails() - " +sessBean.getTempListTails());
	
		}
		catch (javax.validation.ConstraintViolationException cve)
		{
			adminSessBean.addError(cve.getLocalizedMessage());
		}
		catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					adminSessBean.addError("Ошибка загрузки остатков!");
		}
		
		
		return model;
	}
	

	@RequestMapping(value = "addTempFileTail" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processTempFileTail( @ModelAttribute  MultipartFile file,
										@Valid MA_loadTail mA_loadTail ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act)
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		
		if(mA_loadTail.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadTail);
				adminSessBean.setmA_loadTail(mA_loadTail);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}
		
		adminSessBean.setmA_loadTail(mA_loadTail);
				
		try {

					List<Tail> lT = (List<Tail>) fileUpload.process(new Tail(),file, mA_loadTail);
	
					for(Tail t: lT) 
					{
							adminSessBean.getTempListTails().add(t);
					}
					
					
	
		}
		catch (java.lang.NumberFormatException nfe) {
			nfe.printStackTrace();
			adminSessBean.addError(nfe.getMessage());
		}
		catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					adminSessBean.addError("Ошибка загрузки файла!");
		}
		
		
		return model;
	}


	@RequestMapping(value = "autoLoad" , method = RequestMethod.POST )
	public ModelAndView   processAutoLoad(@RequestParam(value = "act",   defaultValue = "-1", required=true) int act,
											@RequestParam(value = "status",   required=false) int status)
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		List<IMAmodel> listMAmodel = appBean.getAutoLoad_IMAmodels();
		List<Handler> listHandler = new ArrayList(listMAmodel.size());

		for(IMAmodel mam: listMAmodel)
			listHandler.add(new Handler(mam));
		
		
		switch (status) {
			
			case 0:
				MainAutoLoad.stopAutoLoad();
				break;
			
			case 1:
				MainAutoLoad.startAutoLoad(listHandler);
				break;
		}
		
		return model;
	}
}
