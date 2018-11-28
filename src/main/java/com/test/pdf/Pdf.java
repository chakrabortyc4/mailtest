package com.test.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class Pdf {
	
	public static final String DEST = "D:\\pdf\\paragraph_spacebefore.pdf";
	
	 public static void main(String[] args) throws IOException, DocumentException {
		 
		        File file = new File(DEST);
		        file.getParentFile().mkdirs();
		        Rectangle pagesize = PageSize.A4;
		        new Pdf().createPdf(DEST, pagesize);		        
	   }
	 
	 public void createPdf(String filename, Rectangle pagesize) throws IOException, DocumentException {
	        Document document = new Document(pagesize);
	        PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream(filename));
	        document.open();
	        
	        //add border
	        Rectangle rect= new Rectangle(577,825,18,15);
	        rect.enableBorderSide(1);
	        rect.enableBorderSide(2);
	        rect.enableBorderSide(4);
	        rect.enableBorderSide(8);
	        rect.setBorderColor(BaseColor.BLACK);
	        rect.setBorderWidth(1);
	        
	        
	        //add headers
	        Paragraph header = new Paragraph("VIBGIOR CIRCUIT RESULT 2018", fontFactory(16, "bold"));
	       	header.setAlignment(Element.ALIGN_CENTER);
	        Paragraph patronage = new Paragraph("FIAP 2018/19, FIP 2018-100, PSA 2018-29, GPU", fontFactory(8, "bold"));
	        patronage.setAlignment(Element.ALIGN_RIGHT);
	        patronage.setSpacingBefore(2f);
	        patronage.setSpacingAfter(6f);
	       
	        PdfContentByte contentByte = new PdfContentByte(writer);
	       
	        
	        //add data to document
	        document.add(rect);
	        document.add(header);
	        document.add(patronage);	       
	        document.add(new LineSeparator());
	        document.add(authorDetails("", 000000, ""));
	        document.add(clubDetails());
	        document.add(new DottedLineSeparator());
	        document.add(pdfTable());
	           
	        document.close();
	    }
	 
	 
	 
	 public Paragraph authorDetails(String name, Integer id, String date) {		 		 
		 Paragraph authorName = new Paragraph("Author ID: "+id+"\n Author Name : "+name.toUpperCase()+"\n Result Date: "+date, fontFactory(10, "normal"));		 
		 authorName.setSpacingBefore(2f);
		 authorName.setSpacingAfter(2f);
		 return authorName;
	 }
	 
	 public Paragraph clubDetails() {		 		 
		 Paragraph club = new Paragraph("belghoria photo lovers".toUpperCase(), fontFactory(10, "bold"));
		 club.setAlignment(Element.ALIGN_CENTER);
		 club.setSpacingAfter(6f);
		 return club;
	 }
	 
	 public Font fontFactory( int size, String fontStyle) {
		 Font font =null;
		 if(fontStyle.equals("bold"))
		    font  = FontFactory.getFont(FontFactory.HELVETICA, size, Font.BOLD);
		 else if(fontStyle.equals("normal")) 
			 font  = FontFactory.getFont(FontFactory.HELVETICA, size, Font.NORMAL);
		 
		 return font;
		 
	 }
	 
	 public PdfPTable  pdfTable() throws DocumentException {
		 
		 PdfPTable table = new PdfPTable(4);
		 table.setSpacingBefore(20f);
		 table.setTotalWidth(200);
		 
	       
	        
	        PdfPCell cell;
	        Paragraph section =new Paragraph("COLOUR",fontFactory(10,"bold"));
	        section.add(" (Accepted score 15/30)");
	        cell = new PdfPCell(section);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	        table.getDefaultCell().setPadding(2);
	        table.getDefaultCell().setPaddingLeft(24);
	        cell.setColspan(4);
	        cell.setPaddingLeft(170);
	        cell.setFixedHeight(25);
	        table.addCell(cell);
	        
	        
	        table.addCell("Titel");
	        table.addCell("Score");
	        table.addCell("Accept");
	        table.addCell("Regect");
	        
	        table.addCell("ABC");
	        table.addCell("16");
	        table.addCell("*");
	        table.addCell("");
	        
	        table.addCell("PQR");
	        table.addCell("12");
	        table.addCell("");
	        table.addCell("*");
	        
	        table.addCell("UTV");
	        table.addCell("22");
	        table.addCell("*");
	        table.addCell("");
	        
	        table.addCell("VPT");
	        table.addCell("14");
	        table.addCell("");
	        table.addCell("*");
	        
         return table;
	 }
	 
	 
}

//http://bethecoder.com/applications/tutorials/pdf/itext/line-separators.html
