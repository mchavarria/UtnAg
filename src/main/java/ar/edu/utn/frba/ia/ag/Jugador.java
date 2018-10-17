package ar.edu.utn.frba.ia.ag;

public class Jugador extends Individuo {
	
	/**
	 * Función Aptitud = (Función G + Función H) - Funcion I
	 * 
	 * Función G = Suma de estadísticas ponderadas y relacionadas con las características
	 * Función H = Suma de características ponderadas
	 * Función I = Ajuste por posible valuación del jugador
	 */

	// Total de características
	static final int TOTAL_CARACTERISTICAS = 5;
	
	// Constantes de valoración de características
	// Excelente = 8, 9, 10.
	// Promedio = 4, 5, 6, 7.
	// Mala = 1, 2, 3.
	static final int CARACTERISTICA_EXCELENTE = 8;
	static final int CARACTERISTICA_MALA = 3;

	// Constante de valoración de estadísticas
	static final int ESTADISTICA_BUENA = 50;
	
	// Estadísticas
	
	// AVG: Porcentaje de bateo.
	private Double avg;
	// SLG: Porcentaje de pegada y corrida a base
	private Double slg;
	// OBP: Porcentaje de permanencia en base
	private Double obp;
	// OPS: Porcentaje de base más pegada
	private Double ops;
	// PA: Paciencia en base.
	private Double pa;

	// Características

	private Double fuerza;
	private Double golpe;
	private Double mentalidad;
	private Double rapidez;
	private Double velocidad;

	// Getters y Setters de estadísticas

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double val) {
		this.avg = val;
	}

	public Double getSlg() {
		return slg;
	}

	public void setSlg(Double val) {
		this.slg = val;
	}
	
	public Double getObp() {
		return obp;
	}

	public void setObp(Double val) {
		this.obp = val;
	}

	public Double getOps() {
		return ops;
	}

	public void setOps(Double val) {
		this.ops = val;
	}

	public Double getPa() {
		return pa;
	}

	public void setPa(Double val) {
		this.pa = val;
	}

	// Getters y Setters de características
	
	public Double getFuerza() {
		return fuerza;
	}
	
	public void setFuerza(Double val) {
		this.fuerza = val;
	}

	public Double getGolpe() {
		return golpe;
	}
	
	public void setGolpe(Double val) {
		this.golpe = val;
	}
	
	public Double getMentalidad() {
		return mentalidad;
	}
	
	public void setMentalidad(Double val) {
		this.mentalidad = val;
	}

	public Double getRapidez() {
		return rapidez;
	}
	
	public void setRapidez(Double val) {
		this.rapidez = val;
	}
	
	public Double getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(Double val) {
		this.velocidad = val;
	}
	
	/*
	 * Función aptitud
	 */
	public double aptitud() {
		return this.aptitudG() + this.aptitudH() - this.aptitudI();
	}
	
	@Override
	public Individuo generarRandom() {
		
		Jugador jug = new Jugador();
		
		// La puntuación de las estadísticas es de 1 a 100 (%)
		jug.setObp(Math.random() * 100);
		jug.setSlg(Math.random() * 100);
		jug.setOps(Math.random() * 100);
		jug.setAvg(Math.random() * 100);
		jug.setPa(Math.random() * 100);
		// La puntuación de las características es de 1 a 10
		jug.setVelocidad(Math.random() * 10);
		jug.setRapidez(Math.random() * 10);
		jug.setFuerza(Math.random() * 10);
		jug.setGolpe(Math.random() * 10);
		jug.setMentalidad(Math.random() * 10);
		
		return jug;
	}
	
	@Override
	public String toString() {
		return "Jugador: OBP: " + obp + " | SLG: " + slg + " | OPS: " + ops + "| AVG: " + avg + "| PA: " + pa 
				+ " | Velocidad: " + velocidad + " | Rapidez: " + rapidez + "| Fuerza: " + fuerza + "| Golpe: " + golpe + "| Mentalidad: " + mentalidad
				+ " | Aptitud: " + this.aptitud();
	}

	/*
	 * Es la suma de las estadísticas ponderadas en combinación con las características.
	 */
	private Double aptitudG() {
		return this.ponderarEstFuerza() + this.ponderarEstGolpe() + this.ponderarEstMentalidad() + this.ponderarEstRapidez() + this.ponderarEstVelocidad();
	}

	/*
	 * Es la suma de las características ponderadas.
	 */
	private Double aptitudH() {
		return this.ponderarFuerza() + this.ponderarGolpe() + this.ponderarMentalidad() + this.ponderarRapidez() + this.ponderarVelocidad();
	}

	/*
	 * Permite ajustar la función aptitud al considerar la posible de un buen jugador.
	 * Establece la relación con el promedio de las características
	 */
	private Double aptitudI() {
		Double total =  fuerza + golpe + mentalidad + rapidez + velocidad;
		
		return total / TOTAL_CARACTERISTICAS;
	}
	
	// Métodos con ponderación de las estadísticas relacionado con las características.
	// https://docs.google.com/spreadsheets/d/1XYCWNH7kNLKO5ETPfUz1FH3u0igFXCGuWIF5YiuYHqU/edit?usp=sharing
	
	private Double ponderarEstVelocidad() {
		if (this.estadisticaEsBuena(obp)
			&& this.estadisticaEsBuena(pa)
		) {
			return (double) 6;
		} else if (this.estadisticaEsBuena(obp)
			&& !this.estadisticaEsBuena(pa)
		) {
			return (double) 5;
		} 

		return (double) 0;
	}
	
	private Double ponderarEstRapidez() {
		if (this.estadisticaEsBuena(slg)) {
			return (double) 4;
		}
		
		return (double) 2;
	}
	
	private Double ponderarEstFuerza() {
		return (double) 6;
	}
	
	private Double ponderarEstGolpe() {
		if (this.estadisticaEsBuena(avg)
			&& this.estadisticaEsBuena(slg)
		) {
			return (double) 7;
		} else if (this.estadisticaEsBuena(avg)) {
			return (double) 4;
		} else if (avg > slg) {
			return (double) 2;
		}

		return (double) 0;
	}
	
	private Double ponderarEstMentalidad() {
		if (this.estadisticaEsBuena(pa)) {
			return (double) 4;
		}
		
		return (double) 2;
	}
	
	private Double ponderarOps() {
		return (double) 10;
	}
	
	/*
	 * Una estadística es considerada buena por arriba del 50%
	 */
	private Boolean estadisticaEsBuena(Double val) {
		if (val > ESTADISTICA_BUENA) {
			return true;
		}
		
		return false;
	}
	
	// Métodos con ponderación de las características
	
	private Double ponderarVelocidad() {
		if (velocidad <= CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (velocidad >= CARACTERISTICA_EXCELENTE) {
			return 7.5;
		} else {
			return (double) 5;
		}
	}
	
	private Double ponderarRapidez() {
		if (rapidez <= CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (rapidez >= CARACTERISTICA_EXCELENTE) {
			return 4.5;
		} else {
			return (double) 3;
		}
	}
	
	private Double ponderarFuerza() {
		if (fuerza <= CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (fuerza >= CARACTERISTICA_EXCELENTE) {
			return 7.5;
		} else {
			return (double) 5;
		}
	}
	
	private Double ponderarGolpe() {
		if (golpe <= CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (golpe >= CARACTERISTICA_EXCELENTE) {
			return 7.5;
		} else {
			return (double) 5;
		}
	}
	
	private Double ponderarMentalidad() {
		if (mentalidad <= CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (mentalidad >= CARACTERISTICA_EXCELENTE) {
			return (double) 3;
		} else {
			return (double) 2;
		}
	}
}
