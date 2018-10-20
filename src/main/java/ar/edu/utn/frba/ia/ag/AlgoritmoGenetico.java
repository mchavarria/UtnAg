package ar.edu.utn.frba.ia.ag;

import ar.edu.utn.frba.ia.ag.utils.FileUtils;

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
        FileUtils.inicializarCorrida();

        this.generarPoblacionInicial(individuoClass);

        FileUtils.agregarTexto( "Población inicial" );
        for (Individuo individuo : this.individuos) {
        	//FileUtils.agregarTexto( individuo.toString() );
        	FileUtils.agregarTexto(individuo.toString());
        }
        
        FileUtils.agregarTexto( "Promedios Población: " + UTgeNesUtils.estadisticasEquipo(this.individuos) );	
        
        Integer pasada = 1;
        while (!this.configuracion.getCriterioDeParo().parar(this.individuos)) {

            this.estado.generarEstadisticas(this.individuos);

            this.seleccion();
            this.cruzamiento();

            FileUtils.agregarTexto( "" );
            FileUtils.agregarTexto( "Pasada #"+pasada+" - Población:" );
            for (Individuo individuo : this.individuos) {
            	FileUtils.agregarTexto( individuo.toString() );	
            }
            FileUtils.agregarTexto( "Promedios Población: " + UTgeNesUtils.estadisticasEquipo(this.individuos) );

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

        FileUtils.agregarTexto( "" );
        FileUtils.agregarTexto( "----- Algoritmo Genérico Finalizado -----" );
        FileUtils.agregarTexto( "" );
        FileUtils.agregarTexto("Aptitud Promedio de Equipo Ultima Iteracion: "+UTgeNesUtils.aptitudEquipo(this.individuos));
        FileUtils.agregarTexto("Mejor Aptitud Promedio de Equipo Histórica: "+this.estado.getMejorAptitudPromedio());
        FileUtils.agregarTexto("Peor Aptitud Promedio de Equipo  Histórica: "+this.estado.getPeorAptitudPromedio());
        FileUtils.agregarTexto("Estadistica Promedio de Equipo Ultima Iteracion: "+UTgeNesUtils.estadisticaEquipo(this.individuos));
        FileUtils.agregarTexto("Mejor Estadistica Promedio de Equipo Histórica: "+this.estado.getMejorEstadisticaPromedio());
        FileUtils.agregarTexto("Peor Estadistica Promedio de Equipo  Histórica: "+this.estado.getPeorEstadisticaPromedio());
        FileUtils.agregarTexto("Caracteristica Promedio de Equipo Ultima Iteracion: "+UTgeNesUtils.caracteristicaEquipo(this.individuos));
        FileUtils.agregarTexto("Mejor Caracteristica Promedio de Equipo Histórica: "+this.estado.getMejorCaracteristicaPromedio());
        FileUtils.agregarTexto("Peor Caracteristica Promedio de Equipo  Histórica: "+this.estado.getPeorCaracteristicaPromedio());
        FileUtils.agregarTexto("Mejor Individuo Ultima Iteracion: "+this.individuos.get(0).toString());
        FileUtils.agregarTexto("Mejor Individuo Histórico: "+this.estado.getMejorIndividuo().toString());
        FileUtils.agregarTexto("Peor Individuo Ultima Iteracion: "+this.individuos.get(this.individuos.size()-1).toString());
        FileUtils.agregarTexto("Peor Individuo Histórico: "+this.estado.getPeorIndividuo().toString());;
        //System.out.println("Cantidad de Mutaciones: " + this.estado.getCantMutaciones() + " / " + this.estado.getCorridas());
        FileUtils.agregarTexto("Cantidad de Mutaciones: " + this.estado.getCantMutaciones() + " / " + this.estado.getCorridas());
    }
}
