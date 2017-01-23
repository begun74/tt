package tt.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tt.bean.AppBean;
import tt.model.Order;
import tt.model.OrderItems;
import tt.model.Tail;
import tt.service.TTServiceImpl;
import tt.util.CreatePDF;
import tt.util.FileUpload;

@Controller
@Scope("session")
@RequestMapping(value = {"/eshop"} , method = RequestMethod.GET)
public class EshopCtrl {
	
	@Autowired
	private AppBean appBean;

	@Autowired
	private FileUpload fileUpload;
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work

	@Autowired
	CreatePDF createPDF;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView  manage(HttpSession session, 
				@RequestParam(value = "act",   defaultValue = "0") String act,
				@RequestParam(value = "error",   defaultValue = "") String error) 
	{
		ModelAndView model = new ModelAndView("redirect:/eshop?act=1");

		switch (act)
		{
			case "1":
				model = new ModelAndView("eshop/newOrders");
				model.addObject("orders",ttService.getOrdersList());
			break;
		}
		
		return model;
	}
	
	@RequestMapping(value = "printOrder", method = RequestMethod.GET)
	public void  printOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session, 
				@RequestParam(value = "orderId",   defaultValue = "0") long orderId) 
	{
		
		final ServletContext servletContext = request.getSession().getServletContext();
	    final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    final String temperotyFilePath = tempDirectory.getAbsolutePath();
	    
		final String pathToFont =  servletContext.getRealPath("/resources/forRussText/");
		
		String fileName = "Order_"+orderId+".pdf";
	    response.setContentType("application/pdf;charset=UTF-8");
	    response.setHeader("Content-disposition", "attachment; filename="+ fileName);
	    
	    File f = new File(fileName);
	    
	    
	    try {
	    	f.createNewFile();
	    	CreatePDF.createPDF(temperotyFilePath+"\\"+fileName, pathToFont, ttService.getOrderItems(orderId));
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos = CreatePDF.convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	}

}
