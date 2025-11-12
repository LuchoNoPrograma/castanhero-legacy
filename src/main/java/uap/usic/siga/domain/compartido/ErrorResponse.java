package uap.usic.siga.domain.compartido;

/**
 * Clase DTO para respuestas de error en la API.
 */
public class ErrorResponse {

    private int code;
    private String developerMessage;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String developerMessage) {
        this.code = code;
        this.developerMessage = developerMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
