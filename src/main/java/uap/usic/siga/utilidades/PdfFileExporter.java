package uap.usic.siga.utilidades;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

public class PdfFileExporter {

	// public void exportPdfFile(String templateFileName, Map<String, Object> data, String pdfFileName) {
    public void exportPdfFile(String templateFileName,  String htmlContent, String pdfFileName) {
	       // String htmlContent = generateHtml(templateFileName, data);
	        try {
	            FileOutputStream fileOutputStream = new FileOutputStream(pdfFileName);
	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(htmlContent);
	            renderer.layout();
	            renderer.createPDF(fileOutputStream, false);
	            renderer.finishPDF();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
}
