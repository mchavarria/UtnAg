package ar.edu.utn.frba.ia.ag;

public final class JugadorConfig {
	
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
}
