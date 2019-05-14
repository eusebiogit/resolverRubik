package dominio;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Define un problema para aplicar la estrategia A*
 * @author ordenador
 */
public class ProblemaA extends ProblemaVoraz{

    /**
     * 
     * @param inicial 
     */
    public ProblemaA(Estado inicial) {
        super(inicial);
    }
    
    /**
     * 
     * @param inicial 
     */
    public ProblemaA(Cubo inicial) {
        super(inicial);
    }
    /**
     * Devuelve los nodos de búsqueda sucesores del nodo de búsqueda actual, a partir
     * de las operaciones que pueden realizarse con el cubo en la posición actual
     * @param e
     * @return 
     */
    public ArrayList<NodoBusqueda> sucesores(NodoBusqueda e){
        ArrayList<Estado> estadosSucesores=this.getEstadosSucesores(e.getActual());
        ArrayList<NodoBusqueda> r=new ArrayList();
        double costoaux=0;
          int h;
          for(Estado estado:estadosSucesores){
            h=heuristica(estado);
            r.add(new NodoBusqueda(e,estado,e.getProfundidad()+1,e.getProfundidad()+h+1));
        }
        return r;
    }
   
      
//    @Override
//    public boolean testObjetivo(Estado e){
//        
//
//        boolean r=true;
//        ArrayList<Cara> caras=e.getActual().getCaras();
//        String color="";
//        for(Cara cara:caras){
//            color=cara.getColorCara();
//            r&=cara.getFilas().get(1).correcta()&&cara.getFilas().get(1).getCasillas().get(0).equals(color);
//            r&=cara.getFilas().get(0).correcta()&&cara.getFilas().get(0).getCasillas().get(0).equals(color);
//            //r&=cara.getFilas().get(2).correcta()&&cara.getFilas().get(2).getCasillas().get(0).equals(color);
//            
////            r&=cara.getFilas().get(1).getCasillas().get(0).equals(color)&&
////                    cara.getFilas().get(2).getCasillas().get(0).equals(color)&&
////                    cara.getFilas().get(2).getCasillas().get(1).equals(color);
//        }
//        
//        
//
//        return r;
//    }
    
    
    

    
    
}
