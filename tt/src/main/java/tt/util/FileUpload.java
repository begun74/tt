package tt.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tt.model.DirProvider;


@Component
@Transactional()
public class FileUpload {
	
	@Autowired
	Constants constants; 

	private static final String[][] ALLOWED_FILE_TYPES_PICS = {{"image/jpeg","jpeg"}, {"image/jpg","jpg"}, {"image/gif","gif"}};
	private static final String[][] ALLOWED_FILE_TYPES_XLS = {{"application/vnd.ms-excel","xls"},{"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","xlsx"}};
    private static final Long MAX_FILE_SIZE = 1048576L; //1MB
    //private static final String UPLOAD_FILE_PATH = "D:/GIT_/wood/src/main/webapp/resources/pics/";
    private static final String UPLOAD_FILE_PATH = "UPLOAD_FILE_PATH";
    private File TEMP_FILE_PATH = null;

    
    
    
    @PostConstruct
	void init(){
    	TEMP_FILE_PATH = constants.tempDirectory;
		System.out.println("TEMP_FILE_PATH - " +TEMP_FILE_PATH);
	}
	
	@PreDestroy
	void destr() {
		//System.out.println("BacketBean @PreDestroy ");
	}    
	public List<DirProvider>  process(MultipartFile file) {
		if (!file.isEmpty()) {
			String contentType = file.getContentType().toString().toLowerCase();
			
			if (isValidContentType_XLS(contentType)) {
            	String newFile = null;
            	//System.out.println("process - "+TEMP_FILE_PATH);

				try {
					file.transferTo(new File(TEMP_FILE_PATH + File.separator+"file.tmp"));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            	/*
                if (belowMaxFileSize(file.getSize())) {
                    new File(newFile).mkdirs();
                    try {
						file.transferTo(new File(newFile));
						//return ReadExcelUtil.readParticleboard(new File(newFile));
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
                }
                */
            	
			}
		}
		
		return null;

	}

    
    private boolean isValidContentType_PICS(String contentType) {
    	//System.out.println("contentType - "+contentType);
    	List<String[]> lExt= Arrays.asList(ALLOWED_FILE_TYPES_PICS);
    	
    	for(String[] ext: lExt) 
    		if(ext[0].equals(contentType))
    			return true; 
        
        return false;
    }

    
    
    private boolean isValidContentType_XLS(String contentType) {
    	
    	List<String[]> lExt= Arrays.asList(ALLOWED_FILE_TYPES_XLS);
    	
    	for(String[] ext: lExt) 
    		if(ext[0].equals(contentType))
    			return true; 
        
        return false;
    }

	
}
