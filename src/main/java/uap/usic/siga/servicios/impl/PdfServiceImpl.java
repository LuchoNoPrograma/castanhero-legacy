package uap.usic.siga.servicios.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import uap.usic.siga.servicios.PdfService;

@Service
public class PdfServiceImpl implements PdfService {

	@Override
	public ByteArrayInputStream convertHtmlToPdf(String htmlContent) {
		  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		  ITextRenderer renderer = new ITextRenderer();
	        renderer.setDocumentFromString(htmlContent);
	        renderer.layout();
	        renderer.createPDF(outputStream, false);
	        renderer.finishPDF();
	        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	        return inputStream;
	}

	@Override
	public void guardarArchivo(String htmlContent) {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public void exportPdfFile(String templateFileName, String htmlContent, String pdfFileName) {
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
