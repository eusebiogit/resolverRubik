/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resolverrubik;

import presentacion.InterfazUsuario;

/**
 *
 * @author ordenador
 */
public class ResolverRubik {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        InterfazUsuario iu=new InterfazUsuario();
        iu=new InterfazUsuario();
        
        //Puede cargarse como parametro posicion del archivo que contiene el cubo
        if(args.length==1){
            iu.menu(args[0]);
            
        }else{
            iu.menu("cubo/cubo.txt");
        }
        
        
    }
    
}
