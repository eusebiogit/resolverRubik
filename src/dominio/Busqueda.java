/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static int vecespoda = 0;
    static int heu = 0;

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
     * Si la poda está activada los nodos peores no se añaden a la búsqueda
     *
     * @param n
     * @return
     */
    protected boolean poda(NodoBusqueda n) {
        boolean r = false;
        if (poda) {
            Integer key = n.getActual().hashCode();
            Integer valor = estados.get(key);
            if (valor == null) {
                estados.put(key, n.getProfundidad());
            } else {
                if (valor <= n.getValor()) {
                    r = true;
                    vecespoda++;
                } else {
                    estados.put(key, n.getProfundidad());
                }
            }
        }

        return r;
    }

    /**
     * La búsqueda consiste en extraer uno de los nodos en función de la estrategia
     * comprobar si es una solución y en caso de no serla, generar los sucesores y añadirlos
     * al espacio de búsquedas
     * @return 
     */
    public ArrayList<Estado> buscar() {  //busqueda general
        ArrayList<NodoBusqueda> LS = null;
        ArrayList<Estado> solucion = null;
        NodoBusqueda actual = null;
        NodoBusqueda inicial = new NodoBusqueda(null, problema.getInicial(), 0, 0);
        frontera.insertar(inicial);
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
            solucionParcial(actual);
        } while (!resuelto && !frontera.esVacia());
        if (resuelto) {
            solucion = creaSolucion(actual);
        }
        complejidadTemporal = (System.currentTimeMillis() - tiempoInicio) / 1000;

        return solucion;
    }

    /**
     * Definición del método que inserta en el espacio de búsqueda en función de la 
     * estrategia
     * @param LS
     * @param actual 
     */
    protected abstract void creaListaNodosArbol(
            ArrayList<NodoBusqueda> LS, NodoBusqueda actual);

    /**
     * Una vez alcanzada la solución se devuelven los pasos que han sido dados
     * @param actual
     * @return 
     */
    protected ArrayList<Estado> creaSolucion(NodoBusqueda actual) {
        profundidad = actual.getProfundidad();

        Stack<Estado> pila = new Stack();
        ArrayList<Estado> r = new ArrayList();
        NodoBusqueda aux = actual;
        while (aux != null) {
            pila.add(aux.getActual());
            aux = aux.getPadre();

        }
        while (!pila.empty()) {
            r.add(pila.pop());
        }

        return r;
    }

    /**
     * Devuelve las mejores las soluciones más completas que va encontrando
     * durante el proceso
     *
     * @param actual
     */
    protected void solucionParcial(NodoBusqueda actual) {
        int h = actual.getActual().getActual().completado();

        if (h > heu) {
            String op = "";
            ArrayList<Estado> solucionparcial = creaSolucion(actual);
            for (Estado e : solucionparcial) {
                op += e.getAccion() + "\n\n";
            }
            heu = h;
            persistencia.OperacionesPersistencia.guardarenFichero("/home/ordenador/Escritorio/pasos.txt", "OP :"
                    + actual.getActual().getAccion()
                    + "\n Completado \n" + actual.getActual().getActual().completado() + "\n"
                    + actual.getActual().getActual() + " \n" + op);

        }
    }

    /**
     * Estadísticas
     * @return 
     */
    @Override
    public String toString() {
        return "Complejidad Temporal: " + getComplejidadTemporal() + " s\n"
                + "Complejidad Espacial: " + getComplejidadEspacial() + " nodos\n"
                + "Profundidad: " + profundidad;
    }

}
