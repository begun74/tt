package tt.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tt.model.DirNomenclGroup;
import tt.model.DirNomenclGroupRoot;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.IModel;
import tt.model.Tail;
import tt.modelattribute.IMAmodel;
import tt.modelattribute.MA_loadNomencl;
import tt.modelattribute.MA_loadNomenclGroup;
import tt.modelattribute.MA_loadNomenclGroupRoot;
import tt.modelattribute.MA_loadProvider;
import tt.modelattribute.MA_loadTail;
import tt.service.TTServiceImpl;


@Service
@Transactional()
public class FileUpload {
	
	@Autowired
	Constants constants; 
	
	@Autowired
	private TTServiceImpl ttService;  //Service which will do all data retrieval/manipulation work

	private static final String[][] ALLOWED_FILE_TYPES_PICS = {{"image/jpeg","jpeg"}, {"image/jpg","jpg"}, {"image/gif","gif"}};
	private static final String[][] ALLOWED_FILE_TYPES_XLS = {{"application/vnd.ms-excel","xls"},{"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","xlsx"}};
    //private static final Long MAX_FILE_SIZE = 1048576L; //1MB
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

	
	public Collection<?>  process(IModel model , MultipartFile file, IMAmodel IMAmodel) throws Exception {
		if (!file.isEmpty()) {
			String contentType = file.getContentType().toString().toLowerCase();
			String extention ;
			
			if ((extention = isValidContentType(ALLOWED_FILE_TYPES_XLS,contentType)) != null) {
            	File tmpFile = new File(TEMP_FILE_PATH + File.separator+"tmp."+extention );

				try {
					file.transferTo(tmpFile);


					if(model instanceof DirProvider)
					return ReadExcelFile.processFile(tmpFile,(DirProvider) model, (MA_loadProvider) IMAmodel) ;
					
					else if(model instanceof DirNomenclature)
					{
						List<DirNomenclGroup> lNG = ttService.getNomenclGroupList();
						HashMap<Long,DirNomenclGroup> hmNomenclGroup = new HashMap<Long,DirNomenclGroup>();
						for(DirNomenclGroup dng: lNG) 
							hmNomenclGroup.put(dng.getCode(), dng);

						return ReadExcelFile.processFile(tmpFile,(DirNomenclature) model, (MA_loadNomencl) IMAmodel, hmNomenclGroup) ;
					}

					else if(model instanceof DirNomenclGroup)
					{
						List<DirNomenclGroupRoot> lNGR = ttService.getNomenclGroupRootList();
						HashMap<Long,DirNomenclGroupRoot> hmNomenclGroupRoot = new HashMap<Long,DirNomenclGroupRoot>();
						
						for(DirNomenclGroupRoot dngr: lNGR) 
							hmNomenclGroupRoot.put(dngr.getCode(), dngr);
					
						return ReadExcelFile.processFile(tmpFile,(DirNomenclGroup) model, (MA_loadNomenclGroup) IMAmodel, hmNomenclGroupRoot) ;
					}

					else if(model instanceof DirNomenclGroupRoot)
						return ReadExcelFile.processFile(tmpFile,(DirNomenclGroupRoot) model, (MA_loadNomenclGroupRoot) IMAmodel) ;
					
					else if(model instanceof Tail)
					{
						List<DirProvider> lP = ttService.getProviderList();
						HashMap<Integer,DirProvider> hmProv = new HashMap<Integer,DirProvider>();
						for(DirProvider dp: lP) 
							hmProv.put(dp.getCode(), dp);

						List<DirNomenclature> lN = ttService.getNomenclatureList();
						HashMap<Long,DirNomenclature> hmNomencl = new HashMap<Long,DirNomenclature>();
						for(DirNomenclature dn: lN) 
							hmNomencl.put(dn.getCode(), dn);

						return ReadExcelFile.processFile(tmpFile,(Tail) model, (MA_loadTail) IMAmodel, hmProv, hmNomencl) ;
					}
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
