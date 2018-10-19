package ar.edu.utn.frba.ia.ag;

import ar.edu.utn.frba.ia.ag.cruzamiento.BinomialAzar;
import ar.edu.utn.frba.ia.ag.mutacion.MutacionSimple;
import ar.edu.utn.frba.ia.ag.paro.AptitudMinimaPromedio;
import ar.edu.utn.frba.ia.ag.seleccion.Ranking;

/**
 *
 */
public class App 
{
	static final int POBLACION_INICIAL = 30;
	static final int CORTE_ESTADISTICA = 80;
	static final int CORTE_ITERACION = 5000;
	static final int CORTE_CARACTERISTICA = 4;
	static final int SELECCION_RANKING = 12;
	static final double MUTACION = 0.2;
	
    public static void main( String[] args )
    {
        System.out.println( "----- Configuración del Algoritmo Genérico  -----" );
        System.out.println( "Población Inicial: "+ POBLACION_INICIAL);
        System.out.println( "Mutación: "+MUTACION*100+"%");
        System.out.println( "Selección \'RANKING\': "+ SELECCION_RANKING+" Individuos");
        System.out.println( "Corte \'Aptitud Mínima Promedio\' ");
        System.out.println( "Estadística Promedio de equipo mayor a: "+ CORTE_ESTADISTICA+"%");
        System.out.println( "Característica Promedio de equipo mayor a: "+ CORTE_CARACTERISTICA);
        
        Configuracion configuracion = new Configuracion(
	        		new AptitudMinimaPromedio(CORTE_ESTADISTICA, CORTE_CARACTERISTICA, CORTE_ITERACION),
	        		POBLACION_INICIAL,
	                new Ranking(SELECCION_RANKING, Jugador.class),
	            	new BinomialAzar(),
	            	new MutacionSimple(MUTACION)
            	);
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(configuracion, Jugador.class);

        System.out.println( "" );
        System.out.println( "----- Se inicia el Algoritmo Genérico  -----" );
        algoritmoGenetico.ejecutar();

    }
}
