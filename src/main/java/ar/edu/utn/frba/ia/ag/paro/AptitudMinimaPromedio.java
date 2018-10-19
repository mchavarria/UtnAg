package ar.edu.utn.frba.ia.ag.paro;

import java.util.List;

import ar.edu.utn.frba.ia.ag.Individuo;

public class AptitudMinimaPromedio extends CriterioDeParo {
	
	private double minValEstadistica = Double.MAX_VALUE;
	private Integer minValCaracteristica;
	
	public AptitudMinimaPromedio(double valEstadistica, Integer valCaracteristica) {
		this.minValEstadistica = valEstadistica;
		this.minValCaracteristica = valCaracteristica;
	}
	
	@Override
	public Boolean parar(List<Individuo> individuos) {
		
		double totalEstadisticas = 0;
		double totalCaracteristicas = 0;
		double promEstadisticas = 0;
		double promCaracteristicas = 0;
		
		for (Individuo individuo : individuos) {
			
			totalEstadisticas += individuo.obtenerPromedioEstadisticas();
			totalCaracteristicas += individuo.obtenerPromedioCaracteristicas();
		}
		
		promEstadisticas = totalEstadisticas / individuos.size();
		promCaracteristicas = totalCaracteristicas / individuos.size();
		
		return (promEstadisticas >= minValEstadistica && promCaracteristicas >= minValCaracteristica);
	}
	
}
