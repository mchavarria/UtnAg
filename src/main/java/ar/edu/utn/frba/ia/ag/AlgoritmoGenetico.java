package ar.edu.utn.frba.ia.ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlgoritmoGenetico {
    private Configuracion configuracion;
    private List<Individuo> individuos;
    private Class<? extends Individuo> individuoClass;
    private Estado estado = new Estado();

    public AlgoritmoGenetico(Configuracion configuracion, Class<? extends Individuo> individuoClass) {

        this.configuracion = configuracion;
        this.individuoClass = individuoClass;
    }

    protected void agregarIndividuo(Individuo individuo) {

        if (this.individuos == null) {
            this.individuos = new ArrayList<Individuo>();
        }

        this.individuos.add(individuo);
    }

    protected void generarPoblacionInicial(Class<? extends Individuo> individuoClass) {

        for (int i = 0; i < this.configuracion.getPoblacionInicial(); i++) {
            try {
                this.agregarIndividuo(individuoClass.newInstance().generarRandom());
            } catch (Exception e) {
                Logger.getLogger(
                        Logger.GLOBAL_LOGGER_NAME).severe(
                        "No se puede crear una instancia de "
                                + individuoClass.getName()
                                + ". Probablemente no tenga un constructor vacio."
                                + " // CAUSA: " + e);
            }
        }
    }

    public void ejecutar() {

        this.generarPoblacionInicial(individuoClass);

        System.out.println( "Población inicial" );
        for (Individuo individuo : this.individuos) {
        	System.out.println( individuo.toString() );	
        }
        
        System.out.println( "Promedios Población: " + UTgeNesUtils.estadisticasEquipo(this.individuos) );	
        
        Integer pasada = 1;
        while (!this.configuracion.getCriterioDeParo().parar(this.individuos)) {

            this.estado.generarEstadisticas(this.individuos);

            this.seleccion();
            this.cruzamiento();

            System.out.println( "" );
            System.out.println( "Pasada #"+pasada+" - Población:" );
            for (Individuo individuo : this.individuos) {
            	System.out.println( individuo.toString() );	
            }
            System.out.println( "Promedios Población: " + UTgeNesUtils.estadisticasEquipo(this.individuos) );

            this.mutacion();
            pasada++;
        }

        this.estado.generarEstadisticas(this.individuos);
        
        Collections.sort(this.individuos);

        this.loggearEstado();
    }

    private void seleccion() {
        this.individuos = this.configuracion.getMetodoDeSeleccion().seleccionar(this.individuos, this.estado);
    }

    private void cruzamiento() {
        this.configuracion.getCruzamiento().cruzarIndividuos(this.individuos);
    }

    private void mutacion() {
        this.configuracion.getMutacion().mutar(this.individuos, this.estado);
    }

    private void loggearEstado() {

        System.out.println( "" );
        System.out.println( "----- Algoritmo Genérico Finalizado -----" );
        System.out.println( "" );
        System.out.println("Estadistica Promedio de Equipo Ultima Iteracion: "+UTgeNesUtils.estadisticaEquipo(this.individuos));
        System.out.println("Mejor Estadistica Promedio de Equipo Histórica: "+this.estado.getMejorEstadisticaPromedio());
        System.out.println("Peor Estadistica Promedio de Equipo  Histórica: "+this.estado.getPeorEstadisticaPromedio());
        System.out.println("Caracteristica Promedio de Equipo Ultima Iteracion: "+UTgeNesUtils.caracteristicaEquipo(this.individuos));
        System.out.println("Mejor Caracteristica Promedio de Equipo Histórica: "+this.estado.getMejorCaracteristicaPromedio());
        System.out.println("Peor Caracteristica Promedio de Equipo  Histórica: "+this.estado.getPeorCaracteristicaPromedio());
        System.out.println("Mejor Individuo Ultima Iteracion: "+this.individuos.get(0).toString());
        System.out.println("Mejor Individuo Histórico: "+this.estado.getMejorIndividuo().toString());
        System.out.println("Peor Individuo Ultima Iteracion: "+this.individuos.get(this.individuos.size()-1).toString());
        System.out.println("Peor Individuo Histórico: "+this.estado.getPeorIndividuo().toString());;
        System.out.println("Cantidad de Mutaciones: " + this.estado.getCantMutaciones() + " / " + this.estado.getCorridas());
    }
}
