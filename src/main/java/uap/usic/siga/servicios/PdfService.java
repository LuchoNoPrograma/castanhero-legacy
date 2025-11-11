package uap.usic.siga.servicios;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

public interface PdfService {

	 ByteArrayInputStream convertHtmlToPdf(String htmlContent);
	 void guardarArchivo(String htmlContent) ;
	 public void exportPdfFile(String templateFileName,  String htmlContent, String pdfFileName);
	 
	 
	
}
