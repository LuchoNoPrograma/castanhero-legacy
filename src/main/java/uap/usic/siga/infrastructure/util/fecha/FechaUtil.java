package uap.usic.siga.infrastructure.util.fecha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FechaUtil {

    private static final String FORMATO_FECHA_DEFAULT = "yyyy-MM-dd";
    private static final String FORMATO_FECHA_HORA = "yyyy-MM-dd HH:mm:ss";

    public static Date convertirStringAFecha(String fechaStr) {
        return convertirStringAFecha(fechaStr, FORMATO_FECHA_DEFAULT);
    }

    public static Date convertirStringAFecha(String fechaStr, String formato) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            return sdf.parse(fechaStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatearFecha(Date fecha) {
        return formatearFecha(fecha, FORMATO_FECHA_DEFAULT);
    }

    public static String formatearFecha(Date fecha, String formato) {
        if (fecha == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }

    public static boolean esAnteriorA(Date fecha1, Date fecha2) {
        if (fecha1 == null || fecha2 == null) {
            return false;
        }
        return fecha1.before(fecha2);
    }

    public static boolean esPosteriorA(Date fecha1, Date fecha2) {
        if (fecha1 == null || fecha2 == null) {
            return false;
        }
        return fecha1.after(fecha2);
    }

    public static LocalDate convertirDateALocalDate(Date fecha) {
        if (fecha == null) {
            return null;
        }
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertirLocalDateADate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static boolean esFechaValida(String fechaStr) {
        return convertirStringAFecha(fechaStr) != null;
    }

    public static Date obtenerFechaActual() {
        return new Date();
    }

    public static int calcularDiferenciaEnDias(Date fechaInicio, Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return 0;
        }
        long diferenciaMilisegundos = fechaFin.getTime() - fechaInicio.getTime();
        return (int) (diferenciaMilisegundos / (1000 * 60 * 60 * 24));
    }
}
