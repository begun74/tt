package tt.util.autoLoad;


import java.util.List;

import tt.util.*;

public class FileHandler implements Runnable {
	
	private String pathToShare;
	private Long code;
	private List<String> listPaths;
	
	FileUpload fileUpload = new FileUpload();
	
	public FileHandler(Long code,String pathToShare) 
	{
		this.pathToShare = pathToShare;
		this.code = code;
	}

	public FileHandler(Long code,List<String> listPaths) 
	{
		this.listPaths = listPaths;
		this.code = code;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//fileUpload.downloadPhoto(code.longValue(), pathToShare);
			fileUpload.downloadPhoto(code.longValue(), listPaths);
		}
		catch(Exception e)
		{
			System.out.println("FileHandler - "+e.getMessage());
			e.printStackTrace();
		}
	}

}
