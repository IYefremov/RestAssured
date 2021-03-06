package com.cyberiansoft.test.ios10_client.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class PDFReader {
	
	public static String getPDFText(File pdfdocument) {		
		String pdftext = "";
		try{
		    PDDocument document = null; 
		    document = PDDocument.load(pdfdocument);
		    document.getClass();
		    if( !document.isEncrypted() ){
		        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		        stripper.setSortByPosition( true );
		        PDFTextStripper Tstripper = new PDFTextStripper();
		        pdftext = Tstripper.getText(document);
		        System.out.println("Text:"+pdftext);
		    }
		    }catch(Exception e){
		        e.printStackTrace();
		    }
		return pdftext;
	}

    public static String getPDFText(String pdfDocument) {
        try (InputStream inputStream = new URL(pdfDocument).openStream()) {
            PDDocument document = PDDocument.load(inputStream);
            return new PDFTextStripper().getText(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
