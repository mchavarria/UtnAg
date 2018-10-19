package ar.edu.utn.frba.ia.ag;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

public class UTgeNesUtils {
    public static Method armarSetter(Individuo individuo, Field field) {

        try {

            return individuo.getClass().getMethod("set" + armarAtributoPascalCase(field), field.getType());

        } catch (Exception e) {
            Logger.getLogger(
                    Logger.GLOBAL_LOGGER_NAME).severe(
                    "No se puede ejecurar el Setter del individuo {0}"
                            + individuo.toString()
                            + " // CAUSA: "
                            + e);
        }

        return null;
    }

    public static Method armarGetter(Individuo individuo, Field field) {

        try {

            return individuo.getClass().getMethod("get" + armarAtributoPascalCase(field));

        } catch (Exception e) {
            Logger.getLogger(
                    Logger.GLOBAL_LOGGER_NAME).severe(
                    "No se puede ejecurar el Getter del individuo {0}"
                            + individuo.toString()
                            + " // CAUSA: "
                            + e);
        }
        return null;
    }

    private static String armarAtributoPascalCase(Field field) {
        return field.getName().toUpperCase().charAt(0) + field.getName().substring(1);
    }

    public static Object alguno(Object[] elementos) {

        return elementos[(int)(Math.random() * elementos.length)];
    }

    public static String armarRandomString(int length) {

        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer buffer = new StringBuffer("");

        for (int i = 0; i < length; i++) {
            double index = Math.random() * alfabeto.length();
            buffer.append(alfabeto.charAt((int) index));
        }

        return buffer.toString();

    }
    
    public static Double caracteristicaEquipo(List<Individuo> individuos) {
    	double caracteristica = 0;
		
		for (Individuo individuo : individuos) {
			
			caracteristica += individuo.obtenerPromedioCaracteristicas();
		}
		
		return (caracteristica / individuos.size());
    }
    
    public static Double estadisticaEquipo(List<Individuo> individuos) {
    	double estadisticas = 0;
		
		for (Individuo individuo : individuos) {
			
			estadisticas += individuo.obtenerPromedioEstadisticas();
		}
		
		return (estadisticas / individuos.size());
    }
    
    public static String estadisticasEquipo(List<Individuo> individuos) {
    	double aptitud = 0;
		double caracteristicas = 0;
    	double estadisticas = 0;
    	double aptitudProm;
		double caracteristicasProm;
    	double estadisticasProm;
		
		for (Individuo individuo : individuos) {
			aptitud += individuo.aptitud();
			caracteristicas += individuo.obtenerPromedioCaracteristicas();
			estadisticas += individuo.obtenerPromedioEstadisticas();
		}
		
		aptitudProm = aptitud / individuos.size();
		caracteristicasProm = caracteristicas / individuos.size();
		estadisticasProm = estadisticas / individuos.size();
		
		return "Aptitud: "+aptitudProm+" Características: "+caracteristicasProm+" Estadísticas: "+estadisticasProm;
    }
}
