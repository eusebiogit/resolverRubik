package dominio;

import java.util.ArrayList;


/**
 *
 * @author ordenador
 */
public class BusquedaAnchura extends Busqueda{

    public BusquedaAnchura(Problema problema,boolean poda) {
        super(problema,poda);
    }
    

    
/**
 * Para realizar la búsqueda en anchura los nodos se insertan por atrás
 * @param LS
 * @param actual 
 */    
@Override
   protected void creaListaNodosArbol(ArrayList<NodoBusqueda> LS, 
                                            NodoBusqueda actual) {
            //primero en anchura, frontera tipo cola
        for(NodoBusqueda n:LS){
             if(!poda(n))
                frontera.insertar(n);
        }
    }

    
    
    
}
