package tt.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
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
import tt.model.IModel;
import tt.modelattribute.MA_loadProvider;


@Service
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

	
	public Collection<?>  process(IModel model , MultipartFile file, MA_loadProvider mA_loadProvider) throws IllegalStateException, IOException {
		if (!file.isEmpty()) {
			String contentType = file.getContentType().toString().toLowerCase();
			String extention ;
			
			if ((extention = isValidContentType(ALLOWED_FILE_TYPES_XLS,contentType)) != null) {
            	File tmpFile = new File(TEMP_FILE_PATH + File.separator+"tmp."+extention );

				try {
					file.transferTo(tmpFile);
					
					if(model instanceof DirProvider)
					return ReadExcelFile.processFile(tmpFile,(DirProvider) model, mA_loadProvider) ;
					
					//else if(model instanceof DirProvider)
					//return ReadExcelFile.processFile((DirProvider) model) ;
					
				} 
				finally {
					tmpFile.delete();
				}
			}
		}
		
		return null;

	}

    

	private String isValidContentType(String[][] ALLOWED_FILE_TYPES,String contentType) {
    	//System.out.println("contentType - "+contentType);
    	List<String[]> lExt= Arrays.asList(ALLOWED_FILE_TYPES);
    	
    	for(String[] ext: lExt) 
    		if(ext[0].equals(contentType))
    			return ext[1]; 
        
        return null;
    }

	
}
