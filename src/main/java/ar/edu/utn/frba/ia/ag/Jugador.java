package ar.edu.utn.frba.ia.ag;

import java.io.PrintWriter;
import java.io.StringWriter;

import ar.edu.utn.frba.ia.ag.utils.StringUtils;

public class Jugador extends Individuo {
	
	/**
	 * Función Aptitud = (Función G + Función H) - Funcion I
	 * 
	 * Función G = Suma de estadísticas ponderadas y relacionadas con las características
	 * Función H = Suma de características ponderadas
	 * Función I = Ajuste por posible valuación del jugador
	 */
	
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
		// La puntuación de las características es de 1 a 100
		jug.setVelocidad(Math.random() * 10);
		jug.setRapidez(Math.random() * 10);
		jug.setFuerza(Math.random() * 10);
		jug.setGolpe(Math.random() * 10);
		jug.setMentalidad(Math.random() * 10);
		
		return jug;
	}
	
	@Override
	public String toString() {
		//StringWriter stringWriter = new StringWriter();
		//PrintWriter writer = new PrintWriter(stringWriter, true);
		//writer.println("Jugador con Aptitud: " + this.aptitud() + " y Estadísticas:");
		//writer.println("OBP: " + obp + " | SLG: " + slg + " | OPS: " + ops + "| AVG: " + avg + "| PA: " + pa);
		//writer.println("Velocidad: " + velocidad + " | Rapidez: " + rapidez + "| Fuerza: " + fuerza + "| Golpe: " + golpe + "| Mentalidad: " + mentalidad);
		//return stringWriter.toString();

		String val = StringUtils.largoFijo(this.aptitud(), 8);
		val += StringUtils.largoFijo(";"+this.obtenerPromedioCaracteristicas());
		val += StringUtils.largoFijo(";"+this.obtenerPromedioEstadisticas());
		val += StringUtils.largoFijo(";"+avg);
		val += StringUtils.largoFijo(";"+slg);
		val += StringUtils.largoFijo(";"+obp);
		val += StringUtils.largoFijo(";"+ops);
		val += StringUtils.largoFijo(";"+pa);
		val += StringUtils.largoFijo(";"+fuerza);
		val += StringUtils.largoFijo(";"+golpe);
		val += StringUtils.largoFijo(";"+mentalidad);
		val += StringUtils.largoFijo(";"+rapidez);
		val += StringUtils.largoFijo(";"+velocidad);
		
		return val;
		//return this.aptitud()+";"+this.obtenerPromedioCaracteristicas()+";"+this.obtenerPromedioEstadisticas()+";"+avg+";"+slg+";"+obp+";"+ops+";"+pa+";"+fuerza+";"+golpe+";"+mentalidad+";"+rapidez+";"+velocidad;
		//return "Aptitud:"+this.aptitud()+"; AVG:"+avg+"; SLG:"+slg+"; OBP:"+obp+"; OPS:"+ops+"; PA:"+pa+"; FUERZA:"+fuerza+"; GOLPE:"+golpe+"; MENTALIDAD:"+mentalidad+"; RAPIDEZ:"+rapidez+"; VELOCIDAD:"+velocidad;
	}

	/*
	 * Es la suma de las estadísticas ponderadas en combinación con las características.
	 */
	private Double aptitudG() {
		return this.ponderarEstFuerza() + this.ponderarEstGolpe() + this.ponderarEstMentalidad() + this.ponderarEstRapidez() + this.ponderarEstVelocidad() + this.ponderarOps();
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
		Double prom =  (fuerza + golpe + mentalidad + rapidez + velocidad) / JugadorConfig.TOTAL_CARACTERISTICAS;
		Double aptGyH = this.aptitudG() + this.aptitudH();
		Double val;
		if (prom == 8 || prom == 7) {
			val = (double) 0;
		} else if (prom <= 3) {
			val =  aptGyH * 0.9;
		} else if (prom > 8) {
			val = aptGyH * 0.8;
		} else {
			//4, 5, 6
			val = aptGyH * 0.2;
		}
		return val;
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
		if (val > JugadorConfig.ESTADISTICA_BUENA) {
			return true;
		}
		
		return false;
	}
	
	// Métodos con ponderación de las características
	
	private Double ponderarVelocidad() {
		if (velocidad <= JugadorConfig.CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (velocidad >= JugadorConfig.CARACTERISTICA_EXCELENTE) {
			return 7.5;
		} else {
			return (double) 5;
		}
	}
	
	private Double ponderarRapidez() {
		if (rapidez <= JugadorConfig.CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (rapidez >= JugadorConfig.CARACTERISTICA_EXCELENTE) {
			return 4.5;
		} else {
			return (double) 3;
		}
	}
	
	private Double ponderarFuerza() {
		if (fuerza <= JugadorConfig.CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (fuerza >= JugadorConfig.CARACTERISTICA_EXCELENTE) {
			return 7.5;
		} else {
			return (double) 5;
		}
	}
	
	private Double ponderarGolpe() {
		if (golpe <= JugadorConfig.CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (golpe >= JugadorConfig.CARACTERISTICA_EXCELENTE) {
			return 7.5;
		} else {
			return (double) 5;
		}
	}
	
	private Double ponderarMentalidad() {
		if (mentalidad <= JugadorConfig.CARACTERISTICA_MALA) {
			return (double) 0;
		} else if (mentalidad >= JugadorConfig.CARACTERISTICA_EXCELENTE) {
			return (double) 3;
		} else {
			return (double) 2;
		}
	}

	public Double obtenerPromedioCaracteristicas() {
		Double sum = fuerza + golpe + mentalidad + rapidez + velocidad;
		
		return sum / JugadorConfig.TOTAL_CARACTERISTICAS;
	}
	
	public Double obtenerPromedioEstadisticas() {
		Double sum = avg + slg + obp + ops + pa;
		
		return sum / JugadorConfig.TOTAL_CARACTERISTICAS;
	}
}
