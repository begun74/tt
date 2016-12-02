package tt.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tt.model.DirGender;
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
    private static File TEMP_FILE_PATH = null;

    @Resource
    private Environment env;
    
    
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
						
						List<DirGender> lDGen = ttService.getGenderList();
						HashMap<String,DirGender> hmDGen = new HashMap<String,DirGender>();
						for(DirGender dG: lDGen) 
							hmDGen.put(dG.getName(), dG);


						return ReadExcelFile.processFile(tmpFile,(DirNomenclature) model, (MA_loadNomencl) IMAmodel, hmNomenclGroup, hmDGen) ;
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

    
	
	public void downloadPhoto (long code, String pathToShare) 
	{
		
		
		try {
			
			File[] files = new File(pathToShare).listFiles();  
			
			File rootFolder = new File(constants.UPLOAD_FILE_PATH+File.separator+code);
			if(!rootFolder.exists() && !rootFolder.mkdirs()) throw new Exception("Can not create rootFolder! ");
			
			File largeFolder = new File(constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"L");
			if(!largeFolder.exists() && !largeFolder.mkdirs()) throw new Exception("Can not create largeFolder! ");
			
			File mediumFolder = new File(constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"M");
			if(!mediumFolder.exists() && !mediumFolder.mkdirs()) throw new Exception("Can not create mediumFolder! ");
			
			File smallFolder = new File(constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"S");
			if(!smallFolder.exists() && !smallFolder.mkdirs()) throw new Exception("Can not create smallFolder! ");
			

			for(int i=0; i < files.length; ++i)
			{
					long time = System.currentTimeMillis();
					
					Path path = Paths.get(files[i].toURI());
					byte[] data = Files.readAllBytes(path);
					
					File tempFile = new File(TEMP_FILE_PATH+File.separator+code+".tmp");
					
					path = Paths.get(tempFile.toURI());
					Files.write(path, data);
					
					BufferedImage img = ImageIO.read(path.toFile());
					ImageIO.write(img, "jpg", new File(largeFolder+File.separator+code+"_L_"+i+".jpg"));
					ImageIO.write(scaleImage(img,389,582), "jpg", new File(mediumFolder+File.separator+code+"_M_"+i+".jpg"));
					ImageIO.write(scaleImage(img,189,282), "jpg", new File(smallFolder+File.separator+code+"_S_"+i+".jpg"));
					
					tempFile.delete();
					
					System.out.println(files[i]+" time - " +(System.currentTimeMillis() - time)/1000+ " sec.");
			}
		}
		catch(java.io.FileNotFoundException fnf)
		{
			fnf.getMessage();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public BufferedImage scaleImage(BufferedImage img, int targetWidth, int targetHeight) {

	    int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	    BufferedImage ret = img;
	    BufferedImage scratchImage = null;
	    Graphics2D g2 = null;

	    int w = img.getWidth();
	    int h = img.getHeight();

	    int prevW = w;
	    int prevH = h;

	    do {
	        if (w > targetWidth) {
	            w /= 2;
	            w = (w < targetWidth) ? targetWidth : w;
	        }

	        if (h > targetHeight) {
	            h /= 2;
	            h = (h < targetHeight) ? targetHeight : h;
	        }

	        if (scratchImage == null) {
	            scratchImage = new BufferedImage(w, h, type);
	            g2 = scratchImage.createGraphics();
	        }

	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

	        prevW = w;
	        prevH = h;
	        ret = scratchImage;
	    } while (w != targetWidth || h != targetHeight);

	    if (g2 != null) {
	        g2.dispose();
	    }

	    if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
	        scratchImage = new BufferedImage(targetWidth, targetHeight, type);
	        g2 = scratchImage.createGraphics();
	        g2.drawImage(ret, 0, 0, null);
	        g2.dispose();
	        ret = scratchImage;
	    }

	    return ret;

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
