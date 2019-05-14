package dominio;

import java.util.ArrayList;
import java.util.Vector;

/**
 * La búsqueda voraz toma los nodos ordenados pero sin heurística
 *
 * @author ordenador
 */
public class BusquedaVoraz extends Busqueda {

    public BusquedaVoraz(Problema problema, boolean poda) {
        super(problema, poda);
    }

    /**
     * Los nodos se insertan ordenados en función de su valor
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
}
