package tt.util.autoLoad;


import tt.util.*;

public class FileHandler implements Runnable {
	
	private String pathToShare;
	private Long code;
	
	FileUpload fileUpload = new FileUpload();
	
	public FileHandler(Long code,String pathToShare) 
	{
		this.pathToShare = pathToShare;
		this.code = code;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			fileUpload.downloadPhoto(code.longValue(), pathToShare);
		}
		catch(Exception e)
		{
			System.out.println("FileHandler - "+e.getMessage());
			e.printStackTrace();
		}
	}

}
