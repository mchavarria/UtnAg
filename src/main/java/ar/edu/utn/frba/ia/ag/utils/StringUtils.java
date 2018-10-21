package ar.edu.utn.frba.ia.ag.utils;

public class StringUtils {
	
    public static String largoColFijo(String valor, Integer largo) {
    	//return String.format("%1$"+largo+"s", valor.toString()).replace(' ', '_');
    	return String.format("%-" + largo + "." + largo + "s", valor).replace(' ', '|');
    }

    public static String largoFijo(String valor, Integer largo) {
    	//return String.format("%1$"+largo+"s", valor.toString()).replace(' ', '_');
    	return String.format("%-" + largo + "." + largo + "s", valor).replace(' ', '0');
    }

    public static String largoFijo(Double valor, Integer largo) {
    	//return String.format("%1$"+largo+"s", valor.toString()).replace(' ', '_');
    	return String.format("%-" + largo + "." + largo + "s", valor).replace(' ', '0');
    }

    public static String largoFijo(Double valor) {

    	return StringUtils.largoFijo(valor.toString(), 17);
    }

    public static String largoFijo(String valor) {

    	return StringUtils.largoFijo(valor, 17);
    }
    
    public static String columnasJugador() {
    	
    	String val = new StringBuilder()
    		    .append(StringUtils.largoColFijo("Aptitud", 8))
    		    .append(StringUtils.largoColFijo(";C_PROM", 17))
    		    .append(StringUtils.largoColFijo(";E_PROM", 17))
    		    .append(StringUtils.largoColFijo(";AVG", 17))
    		    .append(StringUtils.largoColFijo(";SLG", 17))
    		    .append(StringUtils.largoColFijo(";OBP", 17))
    		    .append(StringUtils.largoColFijo(";OPS", 17))
    		    .append(StringUtils.largoColFijo(";PA", 17))
    		    .append(StringUtils.largoColFijo(";FUERZA", 17))
    		    .append(StringUtils.largoColFijo(";GOLPE", 17))
    		    .append(StringUtils.largoColFijo(";MENTALIDAD", 17))
    		    .append(StringUtils.largoColFijo(";RAPIDEZ", 17))
    		    .append(StringUtils.largoColFijo(";VELOCIDAD", 17))
    		    .toString();
    	
    	return val;
    }

    public static String columnasValoresProm() {
    	
    	String val = new StringBuilder()
    		    .append(StringUtils.largoColFijo("PASADA", 6))
    		    .append(StringUtils.largoColFijo(";APT_PROM", 8))
    		    .append(StringUtils.largoColFijo(";CARACT_PROM", 17))
    		    .append(StringUtils.largoColFijo(";ESTAD_PROM", 17))
    		    .toString();
    	
    	return val;
    }
}
