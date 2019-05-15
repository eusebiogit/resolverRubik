package dominio;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * En esta estrategia, cada vez que se encuentra una solución parcial mejor que
 * la anterior, se borra la frontera por completo. Las consecuencias son que avanza rápido
 * pero las soluciones son largas y la frontera puede quedar vacia.
 *
 * @author ordenador
 */
public class BusquedaPodaFuerte extends Busqueda {

    /**
     *
     * @param problema
     * @param poda
     */
    public BusquedaPodaFuerte(Problema problema, boolean poda) {
        super(problema, poda);
    }

    /**
     * Los nodos se insertan ordenados en función de su valor
     *
     * @param LS
     * @param actual
     */
    @Override
    protected void creaListaNodosArbol(ArrayList<NodoBusqueda> LS,
            NodoBusqueda actual) {
        for (NodoBusqueda n : LS) {
            if (!poda(n)) {
                frontera.insertarOrdenado(n);
            }
        }
    }

    /**
     * Muestra la mejor solución encontrada hasta el momento cuando aparece
     * En esta particularización del método, cada vez que aparece una nueva
     * solución, serán reiniciadas la frontera y la tabla hash
     * @param actual
     */
    @Override
    protected void mostrarSolucionParcial(NodoBusqueda actual) {
        int avance = actual.getActual().getActual().completado();
        if (avance > this.avance && avance < 54) {
            this.estados = new Hashtable();
            this.frontera=new Frontera();
            creaListaNodosArbol(this.problema.sucesores(actual),actual);
            this.avance = avance;
            System.out.println("Solución parcial, cubo mejorado: ");
            System.out.println("Completado: " + this.avance);
            System.out.println(actual);
        }
    }

}
