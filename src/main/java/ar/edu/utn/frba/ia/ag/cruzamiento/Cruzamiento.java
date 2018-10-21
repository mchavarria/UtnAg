package ar.edu.utn.frba.ia.ag.cruzamiento;

import ar.edu.utn.frba.ia.ag.Individuo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Cruzamiento {
	
	protected static final String X = "X";
	protected static final String Y = "Y";
	protected Boolean cruceAleatorio = false;

	public Cruzamiento(Boolean cruceAleatorio) {
		this.cruceAleatorio = cruceAleatorio;
	}

	protected abstract void cruzar(Individuo padreA, Individuo padreB);
	
	public void cruzarIndividuos(List<Individuo> individuos) {
		List<Individuo> individuosACruzar = new ArrayList<>(individuos);

		if (this.cruceAleatorio)
			Collections.shuffle(individuosACruzar);

		for (int i = 0; i < individuosACruzar.size() ; i++) {
			cruzar(individuos.get(i), individuosACruzar.get(individuosACruzar.size() - i - 1));
		}
	}
	
}
