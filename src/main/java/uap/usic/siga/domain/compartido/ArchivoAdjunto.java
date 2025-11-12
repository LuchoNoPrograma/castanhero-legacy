package uap.usic.siga.domain.compartido;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase DTO para manejo de archivos adjuntos.
 * Rectorado - USIC
 * Fecha: 2019-08-25
 */
public class ArchivoAdjunto {

    private MultipartFile file;
    private String description;
    private String fileName;

    public ArchivoAdjunto() {
    }

    public ArchivoAdjunto(MultipartFile file, String description, String fileName) {
        this.file = file;
        this.description = description;
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
