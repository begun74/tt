package tt.util.autoLoad;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tt.util.*;


@Service
@Scope("request")
public class FileHandler implements Runnable {
	
	private String pathToShare;
	private Long code;
	private List<String> listPaths;
	private Thread thread;
	
	//@Autowired
	private FileUpload fileUpload = new FileUpload();
	
	
	public FileHandler(){}
	
	public FileHandler(Long code,String pathToShare) 
	{
		thread = new Thread(this, "FileHandler(Long code,String pathToShare)");
		thread.setPriority(1);
		this.pathToShare = pathToShare;
		this.code = code;
	}

	public FileHandler(Long code,List<String> listPaths) 
	{
		thread = new Thread(this, "FileHandler(Long code,List<String> listPaths)");
		thread.setPriority(1);
		thread.setName("FileHandler "+code);
		this.listPaths = listPaths;
		this.code = code;
	}

	public void setParameters(Long code,String pathToShare) 
	{
		this.pathToShare = pathToShare;
		this.code = code;
	}

	public void setParameters(Long code,List<String> listPaths)
	{
		this.listPaths = listPaths;
		this.code = code;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//Thread.currentThread().setName("FileHandler "+code);
			//Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(thread.getName());
			//System.out.println("Thread.currentThread() - "+Thread.currentThread().getPriority() +"");
			//fileUpload.downloadPhoto(code.longValue(), pathToShare);
			fileUpload.downloadPhoto(code.longValue(), listPaths);
		}
		catch(Exception e)
		{
			System.out.println("FileHandler - "+e.getMessage());
			e.printStackTrace();
		}
	}

	
	@PostConstruct
	void init(){
    	
		System.out.println("FileHandler init " + fileUpload);
	}
	
	@PreDestroy
	void destr() {
		System.out.println("FileHandler destr " );
	}   
}
