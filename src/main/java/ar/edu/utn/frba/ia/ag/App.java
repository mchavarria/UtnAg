package ar.edu.utn.frba.ia.ag;

import ar.edu.utn.frba.ia.ag.cruzamiento.BinomialAzar;
import ar.edu.utn.frba.ia.ag.mutacion.MutacionSimple;
import ar.edu.utn.frba.ia.ag.paro.AptitudMinimaPromedio;
import ar.edu.utn.frba.ia.ag.seleccion.Ranking;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Configuracion configuracion = new Configuracion(new AptitudMinimaPromedio(8), 1000,
                new Ranking(2), new BinomialAzar(), new MutacionSimple(0.5));
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(configuracion, Jugador.class);

        Individuo resultado = algoritmoGenetico.ejecutar();
    }
}
