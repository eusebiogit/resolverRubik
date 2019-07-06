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
        int minimo = 0;
        int maximo = this.frontera.size() - 1;
        int medio;
        while (!dentro) {
            medio = (maximo + minimo) / 2;
            if (maximo <= 0) {
                this.frontera.add(es);
                dentro = true;
            } else {
                if (medio == maximo || medio == minimo) {
                    this.frontera.add(medio, es);
                    dentro = true;
                } else {
                    if (es.compareTo(frontera.get(medio)) < 0) {
                        maximo = medio;
                    } else {
                        minimo = medio;
                    }
                }
            }
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
