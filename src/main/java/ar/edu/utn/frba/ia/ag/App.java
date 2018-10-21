package ar.edu.utn.frba.ia.ag;

import ar.edu.utn.frba.ia.ag.cruzamiento.BinomialAzar;
import ar.edu.utn.frba.ia.ag.mutacion.MutacionSimple;
import ar.edu.utn.frba.ia.ag.paro.AptitudMinimaPromedio;
import ar.edu.utn.frba.ia.ag.seleccion.Ranking;
import ar.edu.utn.frba.ia.ag.utils.FileUtils;

/**
 *
 */
public class App 
{
	static final int POBLACION_INICIAL = 200;//Defecto 200
	static final double CORTE_APT_PROM = 0.88;//Defecto 0.87
	static final int CORTE_ITERACION = 5000;
	static final int SELECCION_RANKING = 20;//Defecto 20
	static final double MUTACION = 0.3;
	
    public static void main( String[] args )
    {
        FileUtils.inicializarCorrida();
        FileUtils.agregarTexto( "----- Configuración del Algoritmo Genérico  -----" );
        FileUtils.agregarTexto( "Población Inicial: "+ POBLACION_INICIAL);
        FileUtils.agregarTexto( "Mutación: "+MUTACION*100+"%");
        FileUtils.agregarTexto( "Selección \'RANKING\': "+ SELECCION_RANKING+" Individuos");
        FileUtils.agregarTexto( "Corte \'Aptitud Mínima Promedio\' ");
        FileUtils.agregarTexto( "Aptitud Promedio de equipo mayor a: "+ CORTE_APT_PROM*100+"%");
        
        Configuracion configuracion = new Configuracion(
	        		new AptitudMinimaPromedio(CORTE_APT_PROM, CORTE_ITERACION),
	        		POBLACION_INICIAL,
	                new Ranking(SELECCION_RANKING, Jugador.class),
	            	new BinomialAzar(true),
	            	new MutacionSimple(MUTACION)
            	);
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(configuracion, Jugador.class);

        FileUtils.agregarTexto( "" );
        FileUtils.agregarTexto( "----- Se inicia el Algoritmo Genérico  -----" );
        //System.out.println( "----- Se inicia el Algoritmo Genérico  -----" );
        algoritmoGenetico.ejecutar();

        FileUtils.agregarTexto( "" );
        FileUtils.agregarTexto( "----- Configuración del Algoritmo Genérico  -----" );
        FileUtils.agregarTexto( "Población Inicial: "+ POBLACION_INICIAL);
        FileUtils.agregarTexto( "Mutación: "+MUTACION*100+"%");
        FileUtils.agregarTexto( "Selección \'RANKING\': "+ SELECCION_RANKING+" Individuos");
        FileUtils.agregarTexto( "Corte \'Aptitud Mínima Promedio\' ");
        FileUtils.agregarTexto( "Aptitud Promedio de equipo mayor a: "+ CORTE_APT_PROM*100+"%");
    }
}
