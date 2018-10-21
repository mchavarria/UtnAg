package ar.edu.utn.frba.ia.ag.mutacion;

import java.util.List;

import ar.edu.utn.frba.ia.ag.Estado;
import ar.edu.utn.frba.ia.ag.Individuo;
import ar.edu.utn.frba.ia.ag.utils.FileUtils;

public abstract class Mutacion {
	
	public void mutar(List<Individuo> individuos, Estado estado) {
		
		if (Math.random() <= this.getProbabilidadDeMutacion(individuos, estado)) {
			
			Individuo individuoAleatorio = individuos.get((int)(Math.random() * individuos.size()));
			
//			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("MUTA -> individuo previo: " + individuoAleatorio.toString());
			FileUtils.agregarTexto("Mutación:");
			FileUtils.agregarTexto("A:" + individuoAleatorio.toString());
			individuoAleatorio.mutar();
			
			estado.sumarMutacion();
			FileUtils.agregarTexto("D:" + individuoAleatorio.toString());
			
//			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("MUTA -> nuevo individuo: " + individuoAleatorio.toString());
			
		}
	}
	
	protected abstract double getProbabilidadDeMutacion(List<Individuo> individuos, Estado estado);
	
}
