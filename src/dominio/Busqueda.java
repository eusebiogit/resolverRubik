package dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.OperacionesPersistencia;

/**
 * Operaciones para aplicar la estrategia de búsqueda en el caso general
 *
 * @author ordenador
 */
public abstract class Busqueda {

    protected int avance;
    protected Problema problema;
    protected double costo;
    protected int complejidadEspacial;
    protected double complejidadTemporal;
    protected Frontera frontera;
    protected boolean poda;
    protected int profundidad;
    protected Hashtable<Integer, Integer> estados;

    /**
     *
     * @param poda
     */
    protected Busqueda(boolean poda) {
        this.poda = poda;
        frontera = new Frontera();
        estados = new Hashtable();
        this.avance = 0;
    }

    /**
     *
     * @param problema
     * @param poda
     */
    protected Busqueda(Problema problema, boolean poda) {
        this.problema = problema;
        frontera = new Frontera();
        estados = new Hashtable();
        this.poda = poda;
        this.avance = 0;
    }

    /**
     * Nodos totales para procesar
     *
     * @return
     */
    public int getComplejidadEspacial() {
        return complejidadEspacial;
    }

    /**
     * Tiempo de ejecución de la búsqueda
     *
     * @return
     */
    public double getComplejidadTemporal() {
        return complejidadTemporal;
    }

    /**
     * La poda evita repetir estados que ya han sido visitados
     *
     * @param n
     * @return
     */
    protected boolean poda(NodoBusqueda n) {
        boolean r = false;
        if (poda) {
            Integer key = n.getActual().hashCode();
            Integer valor = estados.get(key);
            if (valor == null || valor > n.getValor()) {
                estados.put(key, n.getValor());
            } else {
                r = true;
            }
        }

        return r;
    }

    /**
     * La búsqueda consiste en extraer uno de los nodos en función de la
     * estrategia comprobar si es una solución y en caso de no serla, generar
     * los sucesores y añadirlos al espacio de búsquedas
     *
     * @return
     */
    public String buscar() {  //busqueda general
        ArrayList<NodoBusqueda> LS = null;
        NodoBusqueda actual = null;
        NodoBusqueda inicial = new NodoBusqueda(null, problema.getInicial(), 0, 0);
        frontera.insertar(inicial);
        String solucion = null;
        double tiempoInicio = System.currentTimeMillis();
        boolean resuelto = false;
        do {
            actual = frontera.elimina();
            if (problema.testObjetivo(actual.getActual())) {
                resuelto = true;
            } else {
                LS = problema.sucesores(actual);
                creaListaNodosArbol(LS, actual);
                complejidadEspacial += LS.size();
            }
            mostrarSolucionParcial(actual);
        } while (!resuelto && !frontera.esVacia());
        if (!this.frontera.esVacia()) {
            solucion = "Solución Final: \n\n" + actual;
        } else {
            solucion = "Solución no encontrada";
        }
        complejidadTemporal = (System.currentTimeMillis() - tiempoInicio) / 1000;
        return solucion;
    }

    /**
     * Muestra la mejor solución encontrada hasta el momento cuando aparece
     *
     * @param actual
     */
    protected void mostrarSolucionParcial(NodoBusqueda actual) {
        int avance = actual.getActual().getActual().completado();
        if (avance > this.avance && avance < 54) {
            this.avance = avance;
            System.out.println("Solución parcial, cubo mejorado: ");
            System.out.println("Completado: " + this.avance);
            System.out.println(actual);
        }
    }

    /**
     * Definición del método que inserta en el espacio de búsqueda en función de
     * la estrategia
     *
     * @param LS
     * @param actual
     */
    protected abstract void creaListaNodosArbol(
            ArrayList<NodoBusqueda> LS, NodoBusqueda actual);

    /**
     * Estadísticas
     *
     * @return
     */
    @Override
    public String toString() {
        return "Complejidad Temporal: " + getComplejidadTemporal() + " s\n"
                + "Complejidad Espacial: " + getComplejidadEspacial() + " nodos\n"
                + "Profundidad: " + profundidad;
    }

}
