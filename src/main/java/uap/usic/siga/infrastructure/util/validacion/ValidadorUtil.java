package uap.usic.siga.infrastructure.util.validacion;

import java.util.regex.Pattern;

public class ValidadorUtil {

    private static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String REGEX_TELEFONO = "^[0-9]{7,10}$";
    private static final String REGEX_CI = "^[0-9]{5,12}$";

    public static boolean esEmailValido(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        return pattern.matcher(email).matches();
    }

    public static boolean esTelefonoValido(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_TELEFONO);
        return pattern.matcher(telefono).matches();
    }

    public static boolean esCarnetValido(String carnet) {
        if (carnet == null || carnet.trim().isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_CI);
        return pattern.matcher(carnet).matches();
    }

    public static boolean esNumeroValido(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esEnteroValido(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esRangoValido(int valor, int minimo, int maximo) {
        return valor >= minimo && valor <= maximo;
    }

    public static boolean esLongitudValida(String texto, int minimo, int maximo) {
        if (texto == null) {
            return false;
        }
        int longitud = texto.length();
        return longitud >= minimo && longitud <= maximo;
    }

    public static boolean esUrlValida(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        String regex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }
}
