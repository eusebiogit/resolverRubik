package dominio;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Para aplicar la búsqueda voraz
 *
 * @author ordenador
 */
public class ProblemaVoraz extends Problema {

    /**
     *
     * @param inicial
     */
    public ProblemaVoraz(Estado inicial) {
        super(inicial);
    }

    /**
     *
     * @param inicial
     */
    public ProblemaVoraz(Cubo inicial) {
        super(inicial);
    }

    /**
     * Devuelve los sucesores del nodo de búsqueda actual
     *
     * @param e
     * @return
     */
    @Override
    public ArrayList<NodoBusqueda> sucesores(NodoBusqueda e) {
        ArrayList<Estado> estadosSucesores = this.getEstadosSucesores(e.getActual());
        ArrayList<NodoBusqueda> r = new ArrayList();
        double costoaux = 0;
        int h;
        for (Estado estado : estadosSucesores) {
            h = heuristica(estado);
            r.add(new NodoBusqueda(e, estado, e.getProfundidad() + 1, h));
        }
        return r;
    }

    /**
     * La heurística implementada tendrá en cuenta las caras obtenidas en el
     * cubo
     *
     * @param e
     * @return
     */
    protected int heuristica(Estado e) {
        return e.getActual().getValorDistanciaManhatan() - e.getActual().completado();
    }

}
