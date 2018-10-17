package ar.edu.utn.frba.ia.ag.cruzamiento;

import ar.edu.utn.frba.ia.ag.Individuo;

import java.util.ArrayList;
import java.util.List;

public abstract class Cruzamiento {
	
	protected static final String X = "X";
	protected static final String Y = "Y";
	
	protected abstract void cruzar(Individuo padreA, Individuo padreB);
	
	public List<Individuo> cruzarIndividuos(List<Individuo> individuos) {
		
		List<Individuo> nuevos = new ArrayList<Individuo>();
		
		for (int i = 0; i < individuos.size() ; i++) {
			cruzar(individuos.get(i), individuos.get(individuos.size() - i - 1));
		}
		
		return nuevos;
	}
	
}
