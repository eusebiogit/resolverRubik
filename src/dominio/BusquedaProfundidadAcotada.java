package dominio;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import persistencia.OperacionesPersistencia;

/**
 * Se buscará en profundidad con una cota máxima
 *
 * @author ordenador
 */
public class BusquedaProfundidadAcotada extends BusquedaProfundidadSimple {

    protected int profundidadMaxima;

    /**
     *
     * @param problema
     * @param profundidadMaxima
     * @param poda
     */
    public BusquedaProfundidadAcotada(Problema problema, int profundidadMaxima, boolean poda) {
        super(problema, poda);
        this.profundidadMaxima = profundidadMaxima;
    }

    /**
     * Estrategia para la búsqueda en profundidad
     *
     * @return
     */
    @Override
    public String buscar() {
        estados = new Hashtable();
        ArrayList<NodoBusqueda> LS = null;
        NodoBusqueda actual = null;
        NodoBusqueda inicial = new NodoBusqueda(null, problema.getInicial(), 0, 0);
        frontera.insertar(inicial);
        double tiempoInicio = System.currentTimeMillis();
        boolean resuelto = false;
        String solucion = null;
        do {
            actual = frontera.elimina();
            if (problema.testObjetivo(actual.getActual())) {
                resuelto = true;
                solucion = "Solución Final: \n\n" + actual;
            } else {
                LS = problema.sucesores(actual);
                if (LS.get(0).getProfundidad() <= profundidadMaxima) { //busqueda profundidad acotada
                    creaListaNodosArbol(LS, actual);
                    complejidadEspacial += LS.size();
                }
            }
            mostrarSolucionParcial(actual);
        } while (!resuelto && !frontera.esVacia());
        complejidadTemporal = (System.currentTimeMillis() - tiempoInicio) / 1000;
        return solucion;
    }

}
