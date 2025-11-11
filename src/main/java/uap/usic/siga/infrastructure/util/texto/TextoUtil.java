package uap.usic.siga.infrastructure.util.texto;

import java.text.Normalizer;

public class TextoUtil {

    public static String capitalizar(String texto) {
        if (esVacio(texto)) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    public static String capitalizarCadaPalabra(String texto) {
        if (esVacio(texto)) {
            return texto;
        }
        String[] palabras = texto.split("\\s+");
        StringBuilder resultado = new StringBuilder();
        for (String palabra : palabras) {
            if (resultado.length() > 0) {
                resultado.append(" ");
            }
            resultado.append(capitalizar(palabra));
        }
        return resultado.toString();
    }

    public static String removerAcentos(String texto) {
        if (texto == null) {
            return null;
        }
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return textoNormalizado.replaceAll("\\p{M}", "");
    }

    public static boolean esVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean noEsVacio(String texto) {
        return !esVacio(texto);
    }

    public static String recortar(String texto, int longitudMaxima) {
        if (texto == null || texto.length() <= longitudMaxima) {
            return texto;
        }
        return texto.substring(0, longitudMaxima) + "...";
    }

    public static String limpiarEspacios(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.trim().replaceAll("\\s+", " ");
    }

    public static String convertirAMayusculas(String texto) {
        return texto != null ? texto.toUpperCase() : null;
    }

    public static String convertirAMinusculas(String texto) {
        return texto != null ? texto.toLowerCase() : null;
    }

    public static boolean contiene(String texto, String subcadena) {
        if (texto == null || subcadena == null) {
            return false;
        }
        return texto.contains(subcadena);
    }

    public static boolean contieneIgnorandoMayusculas(String texto, String subcadena) {
        if (texto == null || subcadena == null) {
            return false;
        }
        return texto.toLowerCase().contains(subcadena.toLowerCase());
    }

    public static String removerCaracteresEspeciales(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.replaceAll("[^a-zA-Z0-9\\s]", "");
    }
}
