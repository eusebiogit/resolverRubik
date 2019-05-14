package dominio;

/**
 * Se realizará la busqueda con la estrategia A* definida en ProblemaBusquedaA
 * @author ordenador
 */
public class BusquedaA extends BusquedaVoraz{

    /**
     * 
     * @param problema
     * @param poda 
     */
    public BusquedaA(Problema problema,boolean poda) {
        super(problema,poda);
    }
    
}
