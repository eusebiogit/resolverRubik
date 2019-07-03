package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author ordenador
 */
public class Fila implements Cloneable, Serializable{
    
    private String casilla0;
    private String casilla1;
    private String casilla2;

    /**
     * 
     * @param casilla0
     * @param casilla1
     * @param casilla2 
     */
    public Fila(String casilla0, String casilla1, String casilla2) {
        this.casilla0 = casilla0;
        this.casilla1 = casilla1;
        this.casilla2 = casilla2;
    }
    
    /**
     * 
     * @param array 
     */
    public Fila(String array[]) {
        if(array.length==3){
            this.casilla0=array[0];
            this.casilla1=array[1];
            this.casilla2=array[2];
        }
    
    }
    

    /**
     * Casillas de una fila de izquierda a derecha
     * @return 
     */
    public ArrayList<String> getCasillas(){
        ArrayList<String> r=new ArrayList();
        r.add(casilla0);
        r.add(casilla1);
        r.add(casilla2);
        return r;
    }
    
    /**
     * Gira una fila
     * @param l
     * @return 
     */
    public Fila girar(Fila l){
        
        Fila actual =(Fila)clone();
        casilla0=l.getCasillas().get(0);
        casilla1=l.getCasillas().get(1);
        casilla2=l.getCasillas().get(2);
        
        return actual;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public Object clone(){
      Object fila=new Fila(casilla0,casilla1,casilla2);
      return fila;
    }
    
    /**
     * 
     * @return 
     */
    public String toString(){
        String r=casilla0;
        String aux;
        r=rellenar(r);
        aux=rellenar(casilla1);
        r+=" || "+aux+" || ";
        aux=rellenar(casilla2);
        r+=aux;
        return r;
    }
    /**
     * usado por toString para dar formato
     * @param nombre
     * @return 
     */
    private String rellenar(String nombre){
        for(int i=nombre.length();i<=8;i++)
            nombre+=" ";
        return nombre;
    }
    
    /**
     * todas las casillas del mismo color, posible heurística
     * @return 
     */
    public boolean correcta(){
        return casilla0.equals(casilla1)&&
                casilla1.equals(casilla2);
        
    }

    
    
    
    
    
    
    
    
    
    @Override
    public boolean equals(Object obj) {
        boolean iguales=true;
        ArrayList<String> casillas=getCasillas();
        ArrayList<String> casillasObj=((Fila)obj).getCasillas();
        
        if(casillas.size()==casillasObj.size()){
            for(int i=0;i<casillas.size();i++){
                if(!casillas.get(i).equals(casillasObj.get(i))){
                    iguales=false;
                }
            }
            
        }else{
                    iguales=false;
        }
        return iguales;
    }
    
}
