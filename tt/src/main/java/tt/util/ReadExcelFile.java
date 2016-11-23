package tt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tt.bean.SessionBean;
import tt.model.DirNomenclature;
import tt.model.DirProvider;
import tt.model.IModel;
import tt.model.Tail;
import tt.modelattribute.MA_loadNomencl;
import tt.modelattribute.MA_loadProvider;
import tt.modelattribute.MA_loadTail;

@Service
public class ReadExcelFile {
	
		
	
    private static Workbook getWorkbook(File tmpFile) throws IOException {
        
    	Workbook workbook = null;
        FileInputStream fis = new FileInputStream(tmpFile);
        
        if (tmpFile.toString().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(fis);
        } else if (tmpFile.toString().endsWith("xls")) {
            workbook = new HSSFWorkbook(fis);
        }
        return workbook;
    }
	

	
	public  static List<?> processFile(File tmpFile, DirProvider dirProvider, MA_loadProvider mA_loadProvider) throws IOException {
		
		List<DirProvider>  lProvs = new ArrayList<DirProvider>();
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter();
		
		
		int row_ = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        	
        		if(row_ >= mA_loadProvider.getRow()) {
        			try {
		        		dirProvider = new DirProvider();
		        	
		        		dirProvider.setName(df.formatCellValue(tmp.getCell(mA_loadProvider.getCol_name()-1)));
		        		dirProvider.setCode(Integer.parseInt(df.formatCellValue(tmp.getCell(mA_loadProvider.getCol_code()-1)) ) );
		        	
			        	lProvs.add(dirProvider);
        			}
		    		catch (java.lang.NumberFormatException nfe) {
						//nfe.printStackTrace();
						throw new java.lang.NumberFormatException("("+(row_+1) +") Ошибка формата данных !");
						
		    		}

        		}
        	System.out.println("row - "+row_);
        	
        	++row_;
        }
        
		return lProvs;
		
	}

	
	public  static List<?> processFile(File tmpFile, DirNomenclature dirNomenclature, MA_loadNomencl mA_loadNomencl) throws Exception{
		
		List<DirNomenclature>  lNomencls = new ArrayList<DirNomenclature>();
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter();
		
		
		int row_ = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        	
        		if(row_ >= mA_loadNomencl.getRow()-1) {
        			dirNomenclature = new DirNomenclature();
	        	
        			dirNomenclature.setName(df.formatCellValue(tmp.getCell(mA_loadNomencl.getCol_name()-1)));
        			dirNomenclature.setCode(Long.parseLong(df.formatCellValue(tmp.getCell(mA_loadNomencl.getCol_code()-1)) ) );
        			dirNomenclature.setArticle(df.formatCellValue(tmp.getCell(mA_loadNomencl.getCol_article()-1)));
        			
		        	lNomencls.add(dirNomenclature);
        		}
        	
        	
        	++row_;
        }
        
		return lNomencls;
		
	}



	public static Collection<?> processFile(File tmpFile, Tail tail, MA_loadTail mA_loadTail)  throws Exception {
		// TODO Auto-generated method stub
		List<Tail>  lTails = new ArrayList<Tail>();
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
       // DataFormatter df = new DataFormatter();
        
        Timestamp timestamp = new Timestamp(new Date().getTime());
		
		
		int row_ = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        	
        		if(row_ >= mA_loadTail.getRow()-1) {
        			tail = new Tail();
	        	
        			tail.setAmountTail(mA_loadTail.getCol_amountTail());
        			tail.setFirstPrice(mA_loadTail.getCol_firstPrice());
        			tail.setCreate_date(timestamp);
        			lTails.add(tail);
        		}
        	
        	
        	++row_;
        }
        
		return lTails;
	}

}
