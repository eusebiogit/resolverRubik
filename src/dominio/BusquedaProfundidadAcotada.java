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
    public ArrayList<Estado> buscar() {
        estados = new Hashtable();
        System.out.println("profundidadMaxima: " + profundidadMaxima);
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
                if (complejidadEspacial % 10000 == 0) {
                    System.out.println(" Completado: " + actual.getActual().getActual().completado() + " complejidadEspacial" + complejidadEspacial);
                }
                int h = actual.getActual().getActual().completado();
                solucionParcial(actual);
                if (LS.get(0).getProfundidad() <= profundidadMaxima) { //busqueda profundidad acotada
                    creaListaNodosArbol(LS, actual);
                    complejidadEspacial += LS.size();
                }
            }
        } while (!resuelto && !frontera.esVacia());
        if (resuelto) {
            solucion = creaSolucion(actual);
        } else {
            //No hay solucion => solucion = null
        }
        complejidadTemporal = (System.currentTimeMillis() - tiempoInicio) / 1000;
        return solucion;
    }

    /**
     * Devuelve las mejores las soluciones más completas que va encontrando
     * durante el proceso
     *
     * @param actual
     */
    @Override
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

}
