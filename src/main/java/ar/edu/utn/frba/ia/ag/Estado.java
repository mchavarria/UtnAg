package ar.edu.utn.frba.ia.ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estado {
    private List<Double> aptitudesPromedio = new ArrayList<Double>();
    private List<Double> totalAptitudes = new ArrayList<Double>(); // suma total de aptitudes de al corrida actual
    private List<Individuo> mejoresIndividuos = new ArrayList<Individuo>();
    private List<Individuo> peoresIndividuos = new ArrayList<Individuo>();
    private List<Double> estadisticasPromedio = new ArrayList<Double>();
    private List<Double> aptitudPromedio = new ArrayList<Double>();
    private List<Double> caracteristicasPromedio = new ArrayList<Double>();
    private int ciclos = 0;
    private int cantMutaciones = 0;

    public void agregarTotalAptitudes(Double totalAptitudes) {
        this.totalAptitudes.add(totalAptitudes);
    }

    public void agregarAptitudesPromedio(Double promedio) {
        this.aptitudesPromedio.add(promedio);
    }

    public void agregarMejorIndividuo(Individuo mejorIndividuo) {
        this.mejoresIndividuos.add(mejorIndividuo);
    }

    public void agregarPeorIndividuo(Individuo peorIndividuo) {
        this.peoresIndividuos.add(peorIndividuo);
    }
    
    public void agregarEstadisticasPromedio(Double promedio) {
        this.estadisticasPromedio.add(promedio);
    }
    
    public void agregarAptitudPromedio(Double promedio) {
        this.aptitudPromedio.add(promedio);
    }

    public void agregarCaracteristicasPromedio(Double promedio) {
        this.caracteristicasPromedio.add(promedio);
    }

    public Double getMejorCaracteristicaPromedio() {

    	Collections.sort(this.caracteristicasPromedio);

        return this.caracteristicasPromedio.isEmpty() ? null : this.caracteristicasPromedio.get(0);
    }

    public Double getPeorCaracteristicaPromedio() {
    	
    	Collections.sort(this.caracteristicasPromedio);

        return this.caracteristicasPromedio.isEmpty() ? null : this.caracteristicasPromedio.get(this.caracteristicasPromedio.size() - 1);
    }

    public Double getMejorEstadisticaPromedio() {

    	Collections.sort(this.estadisticasPromedio);

        return this.estadisticasPromedio.isEmpty() ? null : this.estadisticasPromedio.get(0);
    }

    public Double getPeorAptitudPromedio() {
    	
    	Collections.sort(this.aptitudPromedio);

        return this.aptitudPromedio.isEmpty() ? null : this.aptitudPromedio.get(this.aptitudPromedio.size() - 1);
    }

    public Double getMejorAptitudPromedio() {

    	Collections.sort(this.aptitudPromedio);

        return this.aptitudPromedio.isEmpty() ? null : this.aptitudPromedio.get(0);
    }

    public Double getPeorEstadisticaPromedio() {
    	
    	Collections.sort(this.estadisticasPromedio);

        return this.estadisticasPromedio.isEmpty() ? null : this.estadisticasPromedio.get(this.estadisticasPromedio.size() - 1);
    }
    
    public Individuo getMejorIndividuo() {

        List<Individuo> mejoresIndividuos = this.mejoresIndividuos; // para no romer el orden original

        Collections.sort(this.mejoresIndividuos);

        return mejoresIndividuos.isEmpty() ? null : mejoresIndividuos.get(0);
    }

    public Individuo getPeorIndividuo() {

        List<Individuo> peoresIndividuos = this.peoresIndividuos; // para no romper el orden original

        Collections.sort(this.peoresIndividuos);

        return peoresIndividuos.isEmpty() ? null : peoresIndividuos.get(peoresIndividuos.size() - 1);
    }

    public List<Individuo> getMejoresIndividuos() {
        return mejoresIndividuos;
    }

    public List<Individuo> getPeoresIndividuos() {
        return peoresIndividuos;
    }

    public List<Double> getAptitudesPromedio() {
        return aptitudesPromedio;
    }

    public List<Double> getTotalAptitudes() {
        return totalAptitudes;
    }

    public Double getTotalUltimaAptitud() {
        return totalAptitudes.get(totalAptitudes.size() - 1);
    }

    public int getCorridas() {
        return this.totalAptitudes.size();
    }

    public void sumarMutacion() {
        this.cantMutaciones  ++;
    }

    public int getCantMutaciones() {
        return this.cantMutaciones;
    }

    public void generarEstadisticas(List<Individuo> individuos) {

        Double totalAptitudes = new Double(0);
        Individuo mejorIndividuo = individuos.get(0);
        Individuo peorIndividuo = individuos.get(0);

        for (int i = 0; i < individuos.size(); i++) {

            Individuo individuo = individuos.get(i);

            totalAptitudes += individuo.aptitud();

            if (individuo.esMasAptoQue(mejorIndividuo)) {
                mejorIndividuo = individuo;
            }
            else if ( ! individuo.esMasAptoQue(peorIndividuo)) {
                peorIndividuo = individuo;
            }

        }

        this.agregarTotalAptitudes(totalAptitudes);
        this.agregarAptitudesPromedio(totalAptitudes / individuos.size());
        this.agregarMejorIndividuo(mejorIndividuo);
        this.agregarPeorIndividuo(peorIndividuo);
        this.agregarEstadisticasPromedio(UTgeNesUtils.estadisticaEquipo(individuos));
        this.agregarCaracteristicasPromedio(UTgeNesUtils.caracteristicaEquipo(individuos));
        this.agregarAptitudPromedio(UTgeNesUtils.aptitudEquipo(individuos));
    }
}
