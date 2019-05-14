package dominio;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa el problema escogido, debe escogerse siguiendo la estrategia de
 * búsqueda a aplicar
 *
 * @author ordenador
 */
public class Problema {

    private Estado inicial;

    /**
     *
     * @param inicial
     */
    public Problema(Estado inicial) {
        this.inicial = inicial;
    }

    /**
     *
     * @param actual
     */
    public Problema(Cubo actual) {
        inicial = new Estado(actual, "***");
    }

    /**
     * Estado desde el que se parte para resolver el cubo
     * @return
     */
    public Estado getInicial() {
        return inicial;
    }

    /**
     * Sucesores de ese nodo de búsqueda, es decir todos los nodos de búsqueda que pueden generarse
     * aplicando las operaciones del cubo
     * @param e
     * @return 
     */
    public ArrayList<NodoBusqueda> sucesores(NodoBusqueda e) {
        ArrayList<NodoBusqueda> r = new ArrayList();

        ArrayList<Estado> estadosSucesores = getEstadosSucesores(e.getEstado());
        for (Estado estado : estadosSucesores) {
            r.add(new NodoBusqueda(e, estado, e.getProfundidad() + 1, e.getValor() + 1));
        }
        return r;
    }

    /**
     * Estados sucesores de un estado dado
     * @param e
     * @return 
     */
    public ArrayList<Estado> getEstadosSucesores(Estado e) {
        Cubo actual = e.getActual();
        Cubo c;
        ArrayList<Estado> r = new ArrayList();
        for (int i = 0; i < 3; i++) {
            c = (Cubo) actual.clone();
            c.X(i);
            r.add(new Estado(c, "X" + i));

            c = (Cubo) actual.clone();
            c.Y(i);
            r.add(new Estado(c, "Y" + i));

            c = (Cubo) actual.clone();
            c.Z(i);
            r.add(new Estado(c, "Z" + i));
        }
        return r;
    }

    /**
     * Comprueba que se ha alcanzado la solución
     * @param e
     * @return 
     */
    public boolean testObjetivo(Estado e) {
        return e.getActual().equals(e.getAlcanzar());
    }

}
