package dominio;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Profundidad caso general
 *
 * @author ordenador
 */
public class BusquedaProfundidadSimple extends Busqueda {

    /**
     *
     * @param problema
     * @param poda
     */
    public BusquedaProfundidadSimple(Problema problema, boolean poda) {
        super(problema, poda);
    }

    /**
     * Para hacer la búsqueda en profundidad los nodos se insertan por delante
     * @param LS
     * @param actual
     */
    @Override
    protected void creaListaNodosArbol(ArrayList<NodoBusqueda> LS,
            NodoBusqueda actual) {
        //profundidad simple ultimo en entrar primero en salir
        for (NodoBusqueda n : LS) {
            if (!poda(n)) {
                frontera.insertarPrimero(n);
            }
        }

    }

}
