package tt.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import tt.model.OrderItems;

@Component
public class CreatePDF {

	//private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	
	static String pathToFont = null;
	

	/**
	 * @param args
	 */
	public static Document createPDF(String file, String pathToFont_,List<OrderItems> orderItems) 
	{
		pathToFont = pathToFont_;
		//System.out.println("listModels - "+mapModels);
		Document document = null;
		

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			//addTitlePage(document);

			createTable(document,  orderItems);

			document.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return document;

	}

	private static void addMetaData(Document document) {
		document.addTitle("Test title");
		document.addSubject("Test PDF report");
		document.addAuthor("Test");
		document.addCreator("Test");
	}

	private static void addTitlePage(Document document)	throws DocumentException, IOException {

		Font TIME_ROMAN = new Font(BaseFont.createFont(pathToFont+"/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		Font TIME_ROMAN_BOLD = new Font(BaseFont.createFont(pathToFont+"/timesbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Коммерческое предложение от компании ООО '12341234'  от " + simpleDateFormat.format(new Date()), TIME_ROMAN));

		creteEmptyLine(preface, 1);
		//preface.add(new Paragraph("Адрес: РБ, г. Минск, ул. Перамоги, д.1  +375 (29)123-12-12  ", TIME_ROMAN_BOLD));

		document.add(preface);

	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void createTable(Document document, List<OrderItems> orderItems) throws DocumentException, IOException {

		Font TIME_ROMAN = new Font(BaseFont.createFont(pathToFont+"/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		Font TIME_ROMAN_BOLD = new Font(BaseFont.createFont(pathToFont+"/timesbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		preface.add(new Paragraph("Заказ №" + orderItems.get(0).getOrder().getId()+"  от "+ simpleDateFormat.format(orderItems.get(0).getOrder().getCreation_date()), TIME_ROMAN));
		preface.add(new Paragraph("Имя : " + orderItems.get(0).getOrder().getPerson_name(), TIME_ROMAN));
		preface.add(new Paragraph("Телефон : " + orderItems.get(0).getOrder().getPhone(), TIME_ROMAN));
		preface.add(new Paragraph("E-mail : " + orderItems.get(0).getOrder().getEmail(), TIME_ROMAN));
		
		

		document.add(preface);
		
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(5);

		PdfPCell c1 = new PdfPCell(new Phrase("Наименование",TIME_ROMAN));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Производитель",TIME_ROMAN));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);
		

		c1 = new PdfPCell(new Phrase("Размеры",TIME_ROMAN));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Кол-во",TIME_ROMAN));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Цена",TIME_ROMAN));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);


		
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		for(OrderItems oi:  orderItems)
		{
			table.addCell(new PdfPCell(new Phrase(oi.getTail().getDirNomenclature().getName(),TIME_ROMAN)));
			table.addCell(new PdfPCell(new Phrase(oi.getTail().getDirNomenclature().getDirProvider().getName(),TIME_ROMAN)));
			table.addCell(new Phrase(""+oi.getTail().getSize(),TIME_ROMAN));
			table.addCell(new Phrase(" "+oi.getAmount(),TIME_ROMAN));
			table.addCell(new Phrase(" ",TIME_ROMAN));
		}
		
		
		
		
		document.add(table);
	}
	
	public static ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {
		 
		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
 
			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();
 
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}

}
