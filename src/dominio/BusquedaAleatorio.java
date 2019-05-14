package dominio;

import java.util.ArrayList;

/**
 *
 * @author ordenador
 */
public class BusquedaAleatorio extends Busqueda {

    /**
     *
     * @param problema
     * @param poda
     */
    public BusquedaAleatorio(Problema problema, boolean poda) {
        super(problema, poda);
    }

    /**
     * Los movimientos son aleatorios así que los nodos se insertan en cualquier
     * lugar de la frontera
     *
     * @param LS
     * @param actual
     */
    @Override
    protected void creaListaNodosArbol(ArrayList<NodoBusqueda> LS, NodoBusqueda actual) {
        for (NodoBusqueda n : LS) {
            if (!poda(n)) {
                frontera.insertarAleatorio(n);
            }

        }
    }
}
