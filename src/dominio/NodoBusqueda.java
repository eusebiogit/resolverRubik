package dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 * Para obtener crear el árbol de búsqueda en función de los estados
 *
 * @author ordenador
 */
public class NodoBusqueda implements Comparable, Serializable {

    private NodoBusqueda padre;
    private Estado actual;
    private int profundidad;
    private int valor;

    /**
     *
     * @param padre
     * @param actual
     * @param profundidad
     * @param valor
     */
    public NodoBusqueda(NodoBusqueda padre, Estado actual, int profundidad, int valor) {
        this.padre = padre;
        this.actual = actual;
        this.profundidad = profundidad;
        this.valor = valor;

    }

    /**
     *
     * @return
     */
    public NodoBusqueda getPadre() {
        return padre;
    }

    /**
     *
     * @return
     */
    public Estado getEstado() {
        return actual;
    }

    /**
     * Profundidad del árbol, orden en la lista de nodos derivados unos de otros
     *
     * @return
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     * Valor asociado a este nodo de búsqueda
     *
     * @return
     */
    public int getValor() {
        return valor;
    }

    /**
     * Acción aplicada al nodo de búsqueda padre para obtener el nodo actual
     *
     * @return
     */
    public String getAccion() {
        return actual.getAccion();
    }

    /**
     * Estado actual
     *
     * @return
     */
    public Estado getActual() {
        return actual;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return actual.hashCode();
    }

    /**
     * Verdadero si dos nodos de búsqueda son iguales
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodoBusqueda other = (NodoBusqueda) obj;

        return other.getActual().equals(actual);
    }

    /**
     * Compara dos nodos de búsqueda en función de su valor
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Object o) {
        return (int) ((int) valor - ((NodoBusqueda) o).getValor());
    }

}
