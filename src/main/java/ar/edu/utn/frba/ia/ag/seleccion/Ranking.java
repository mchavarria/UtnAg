package ar.edu.utn.frba.ia.ag.seleccion;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import ar.edu.utn.frba.ia.ag.Estado;
import ar.edu.utn.frba.ia.ag.Individuo;

public class Ranking extends Seleccion {

	Class<? extends Individuo> indClass;
	Integer cantSeleccion;
	
	public Ranking(Integer cantSeleccion, Class<? extends Individuo> individuoClass) {
		this.indClass = individuoClass;
		this.cantSeleccion = cantSeleccion;
	}
	
	@Override
	protected List<Individuo> seleccion(List<Individuo> individuos, Estado estado) {
		
		Integer cantPoblacion = individuos.size();
		// Ordeno según la función de aptitud
		Collections.sort(individuos);
		
		// Tengo en cuenta la cantidad que quiero seleccionar y elimino los restantes
		while (individuos.size() > this.cantSeleccion) {
			individuos.remove(individuos.size() - 1);
		}

		while (cantPoblacion > individuos.size() ) {
			try {
				individuos.add(this.indClass.newInstance().generarRandom());
            } catch (Exception e) {
                Logger.getLogger(
                        Logger.GLOBAL_LOGGER_NAME).severe(
                        "No se puede crear una instancia de "
                                + this.indClass.getName()
                                + ". Probablemente no tenga un constructor vacio."
                                + " // CAUSA: " + e);
            }
		}
		
		return individuos;
		//return this.generarCopias(individuos, estado);
	}
	
}
