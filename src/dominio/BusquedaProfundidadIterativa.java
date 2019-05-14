package dominio;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Se incrementa el límite de la profundidad en cada iteración
 *
 * @author ordenador
 */
public class BusquedaProfundidadIterativa extends BusquedaProfundidadAcotada {

    protected int iteracion;
    protected double tiempoInicioTotal;

    protected double complejidadTemporalTotal;
    protected int complejidadEspacialFinal;

    protected Vector<Double> complejidadTemporalIteraciones;
    protected Vector<Integer> complejidadEspacialIteraciones;

    /**
     *
     * @param problema
     * @param profundidadMaxima
     * @param iteracion
     * @param poda
     */
    public BusquedaProfundidadIterativa(Problema problema,
            int profundidadMaxima, int iteracion, boolean poda) {
        super(problema, profundidadMaxima, poda);
        this.iteracion = iteracion;
        complejidadTemporalIteraciones = new Vector();
        complejidadEspacialIteraciones = new Vector();
    }

    /**
     * Cuando se completan los nodos en una iteración y la solución no es
     * encontrada, se incrementa el limite
     *
     * @return
     */
    @Override
    public String buscar() {
        String solucion=null;
        double tiempoInicioTotal = System.currentTimeMillis();
        do {
            solucion = super.buscar();
            if (solucion == null) {
                profundidadMaxima += iteracion;
                complejidadTemporalIteraciones.add(super.getComplejidadTemporal());
                complejidadEspacialIteraciones.add(super.getComplejidadEspacial());
            }
        } while (solucion == null);
        complejidadTemporalTotal = (System.currentTimeMillis() - tiempoInicioTotal) / 1000;
        complejidadEspacialFinal = super.getComplejidadEspacial();
        return solucion;
    }

    /**
     *
     * @return
     */
    @Override
    public double getComplejidadTemporal() {
        return complejidadTemporalTotal;
    }

    /**
     *
     * @return
     */
    @Override
    public int getComplejidadEspacial() {
        return complejidadEspacialFinal;
    }

    /**
     *
     * @return
     */
    public Vector<Double> getComplejidadTemporalIteraciones() {
        return complejidadTemporalIteraciones;
    }

    /**
     *
     * @return
     */
    public Vector<Integer> getComplejidadEspacialIteraciones() {
        return complejidadEspacialIteraciones;
    }

    /**
     *Devuelve estadísticas de todo el proceso
     * @return
     */
    @Override
    public String toString() {
        String r = "Complejidad espacial en cada iteracion:\n";
        for (int i = 0; i < complejidadEspacialIteraciones.size(); i++) {
            r += "\tIteracion " + i + ": " + complejidadEspacialIteraciones.get(i) + "\n";
        }
        r += "Complejidad temporal en cada iteracion:\n";
        for (int i = 0; i < complejidadTemporalIteraciones.size(); i++) {
            r += "\tIteracion " + i + ": " + complejidadTemporalIteraciones.get(i) + "\n";
        }
        r += "Profundidad Maxima final: " + profundidadMaxima;
        r += "\nComplejidad Total:\n";
        r += super.toString();
        return r;
    }

}
