package dominio;

import java.util.ArrayList;

/**
 * Se aplica la estrategia de búsqueda primero el mejor recursiva que valora a
 * los nodos con el mayor valor entre su heurística más su coste y el valor del nodo padre
 * @author ordenador
 */
public class ProblemaBRPM extends ProblemaVoraz{
    
    /**
     *
     * @param inicial
     */
    public ProblemaBRPM(Estado inicial) {
        super(inicial);
    }

    /**
     *
     * @param inicial
     */
    public ProblemaBRPM(Cubo inicial) {
        super(inicial);
    }

    /**
     * Devuelve los nodos de búsqueda sucesores del nodo de búsqueda actual, a
     * partir de las operaciones que pueden realizarse con el cubo en la
     * posición actual
     *
     * @param e
     * @return
     */
    public ArrayList<NodoBusqueda> sucesores(NodoBusqueda e) {
        ArrayList<Estado> estadosSucesores = this.getEstadosSucesores(e.getActual());
        ArrayList<NodoBusqueda> r = new ArrayList();
        double costoaux = 0;
        int festado,fanterior,h;
        for (Estado estado : estadosSucesores) {
            festado = heuristica(estado)+e.getProfundidad()+1;
            fanterior = heuristica(e.getEstado())+e.getProfundidad();
            h=Math.max(festado, fanterior);
            r.add(new NodoBusqueda(e, estado, e.getProfundidad() + 1, h));
        }
        return r;
    }
    
    
    
    
}
