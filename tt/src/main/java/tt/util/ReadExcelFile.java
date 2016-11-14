package tt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import tt.model.DirProvider;
import tt.model.IModel;

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
	

	
	public  static List<?> processFile(File tmpFile, DirProvider dirProvider, int row, int[] cols) throws IOException{
		
		List<DirProvider>  lProvs = new ArrayList<DirProvider>();
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter();
		
		
		int row_ = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        	try {
        		if(row_ >= row) {
	        		dirProvider = new DirProvider();
	        	
	        		dirProvider.setName(df.formatCellValue(tmp.getCell(cols[0])));
	        		dirProvider.setCode(Integer.parseInt(df.formatCellValue(tmp.getCell(cols[1])) ) );
	        	
		        	lProvs.add(dirProvider);
        		}
        	}
        	catch(NumberFormatException nexc) {
        		nexc.printStackTrace();
        	}
        	
        	++row_;
        }
        
		return lProvs;
		
	}

}
