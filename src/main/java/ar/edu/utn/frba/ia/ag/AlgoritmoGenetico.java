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

    public Individuo ejecutar() {

        this.generarPoblacionInicial(individuoClass);

        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.FINE, "Población inicial");

        for (Individuo individuo : this.individuos) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.FINE, individuo.toString());
        }

        while (!this.configuracion.getCriterioDeParo().parar(this.individuos)) {

            this.estado.generarEstadisticas(individuos);

            this.seleccion();

            this.cruzamiento();

            this.mutacion();
        }

        Collections.sort(this.individuos);

        this.loggearEstado();

        return this.individuos.get(0);
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

        for (int i = 0; i < this.estado.getMejoresIndividuos().size(); i++) {

            Individuo individuo = this.estado.getMejoresIndividuos().get(i);
            Double aptitudPromedio = this.estado.getAptitudesPromedio().get(i);

            Logger.getLogger(
                    Logger.GLOBAL_LOGGER_NAME).log(
                    Level.SEVERE,
                    "Promedio: "
                            + aptitudPromedio
                            + " // Mejor Individuo "
                            + individuo.toString());

            System.out.println(this.estado.getMejoresIndividuos().get(i).aptitud() + "	"
                    + this.estado.getAptitudesPromedio().get(i) + "	"
                    + this.estado.getPeoresIndividuos().get(i).aptitud());
        }

        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe("Individuo mas Apto: " + this.individuos.get(0).toString());

        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe("Cantidad de Veces que muto: " + this.estado.getCantMutaciones() + " / " + this.estado.getCorridas());
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe("Individuo Campeon: " + this.estado.getMejorIndividuo());
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe("Peor Individuo: " + this.estado.getPeorIndividuo());
    }
}
