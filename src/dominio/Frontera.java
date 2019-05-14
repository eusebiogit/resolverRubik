package dominio;

import java.util.Vector;

/**
 * La frontera contiene los nodos de búsqueda que deben ser procesados para
 * alcanzar la solución
 *
 * @author ordenador
 */
public class Frontera {

    private Vector<NodoBusqueda> frontera;

    /**
     *
     */
    public Frontera() {
        frontera = new Vector<NodoBusqueda>();
    }

    /**
     * Retira un nodo de búsqueda de la frontera
     *
     * @return
     */
    public NodoBusqueda elimina() {
        NodoBusqueda r = frontera.firstElement();
        frontera.remove(r);
        return r;
    }

    /**
     * Inserta un node de búsqueda por atrás en la frontera
     *
     * @param es
     */
    public void insertar(NodoBusqueda es) {
        frontera.add(es);
    }

    /**
     * Inserta un node de búsqueda en cuanquier posición de la frontera
     *
     * @param es
     */
    public void insertarAleatorio(NodoBusqueda es) {
        frontera.add((int) uniforme(0, frontera.size() - 1), es);
    }

    /**
     * Utilizado para obtener una posición aleatoria de la frontera
     *
     * @param a
     * @param b
     * @return
     */
    private static double uniforme(int a, int b) {
        return a + (b - a) * Math.random();
    }

    /**
     * Inserta un nodo ordenado en función de un valor
     *
     * @param es
     */
    public void insertarOrdenado(NodoBusqueda es) {
        boolean dentro = false;
        if (!frontera.isEmpty()) {
            for (int i = 0; i < frontera.size() && !dentro; i++) {
                if (es.compareTo(frontera.get(i)) < 0) {
                    frontera.add(i, es);
                    dentro = true;
                }
            }
        }
        if (!dentro) {
            insertar(es);
        }
    }

    /**
     * Inserta un nodo de búsqueda por delante a la frontera
     *
     * @param primero
     */
    public void insertarPrimero(NodoBusqueda primero) {
        frontera.add(0, primero);
    }

    /**
     * Verdadero cuando la frontera no contiene nodos
     *
     * @return
     */
    public boolean esVacia() {
        return frontera.isEmpty();
    }

    /**
     *
     * @return
     */
    public Vector<NodoBusqueda> getFrontera() {
        return frontera;
    }

    /**
     *
     * @param frontera
     */
    public void setFrontera(Vector<NodoBusqueda> frontera) {
        this.frontera = frontera;
    }
}
