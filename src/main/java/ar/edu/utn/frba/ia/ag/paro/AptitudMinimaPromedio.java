package ar.edu.utn.frba.ia.ag.paro;

import java.util.List;

import ar.edu.utn.frba.ia.ag.Individuo;

public class AptitudMinimaPromedio extends CriterioDeParo {
	
	//La aptitud máxima de un jugador puede ser 50 según las tablas de ponderaciones
	//Una buena aptitud del equipo sería un 70% de la función aptitud.
	private double aptitudMinimaPromedio = Double.MAX_VALUE;
	
	public AptitudMinimaPromedio(double aptitudMinimaPromedio) {
		this.aptitudMinimaPromedio = aptitudMinimaPromedio;
	}
	
	@Override
	public Boolean parar(List<Individuo> individuos) {
		
		double totalAptitud = 0;
		
		for (Individuo individuo : individuos) {
			totalAptitud += individuo.aptitud();
		}
		
		return (totalAptitud / individuos.size() >= aptitudMinimaPromedio);
	}
	
}
