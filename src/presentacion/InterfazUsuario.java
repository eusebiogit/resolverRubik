package presentacion;

import dominio.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Se desarrollará una interfaz de usuario
 *
 * @author ordenador
 */
public class InterfazUsuario {

    public void menu() throws Exception {

        Fila f0 = new Fila("rojo", "rojo", "rojo");
        Fila f1 = new Fila("rojo", "rojo", "rojo");
        Fila f2 = new Fila("rojo", "rojo", "blanco");

        Cara c0 = new Cara(f0, f1, f2);

        f0 = new Fila("blanco", "blanco", "blanco");
        f1 = new Fila("blanco", "blanco", "blanco");
        f2 = new Fila("naranja", "amarillo", "rojo");

        Cara c1 = new Cara(f0, f1, f2);

        f0 = new Fila("naranja", "naranja", "naranja");
        f1 = new Fila("naranja", "naranja", "naranja");
        f2 = new Fila("blanco", "naranja", "naranja");

        Cara c2 = new Cara(f0, f1, f2);

        f0 = new Fila("amarillo", "amarillo", "amarillo");
        f1 = new Fila("amarillo", "amarillo", "amarillo");
        f2 = new Fila("amarillo", "blanco", "amarillo");

        Cara c3 = new Cara(f0, f1, f2);

        f0 = new Fila("verde", "verde", "verde");
        f1 = new Fila("verde", "verde", "verde");
        f2 = new Fila("verde", "verde", "verde");

        Cara c4 = new Cara(f0, f1, f2);

        f0 = new Fila("azul", "azul", "azul");
        f1 = new Fila("azul", "azul", "azul");
        f2 = new Fila("azul", "azul", "azul");

        Cara c5 = new Cara(f0, f1, f2);

        Cubo cubo = new Cubo(c0, c1, c2, c3, c4, c5);

//        Problema p = new Problema(cubo);
//        ProblemaA p = new ProblemaA(cubo);
        ProblemaBRPM p = new ProblemaBRPM(cubo);
//        ProblemaVoraz p = new ProblemaVoraz(cubo);

//        BusquedaVoraz b = new BusquedaVoraz(p, true);
//        BusquedaPodaFuerte b = new BusquedaPodaFuerte(p, true);
        BusquedaA b = new BusquedaA(p, true);
//        BusquedaAleatorio b=new BusquedaAleatorio(p,true);
//        BusquedaAnchura b=new BusquedaAnchura(p,false);
        //BusquedaProfundidadSimple b=new BusquedaProfundidadSimple(p,true);
//         BusquedaProfundidadAcotada b=new BusquedaProfundidadAcotada(p,8,true);
//        BusquedaProfundidadIterativa b = new BusquedaProfundidadIterativa(p, 8, 1, true);

        System.out.println(b.buscar());

    }

    /**
     * Genera un cubo corregido
     *
     * @return
     */
    public Cubo getCuboCorrecto() {
        Fila f0 = new Fila("rojo", "rojo", "rojo");
        Fila f1 = new Fila("rojo", "rojo", "rojo");
        Fila f2 = new Fila("rojo", "rojo", "rojo");

        Cara c0 = new Cara(f0, f1, f2);

        f0 = new Fila("blanco", "blanco", "blanco");
        f1 = new Fila("blanco", "blanco", "blanco");
        f2 = new Fila("blanco", "blanco", "blanco");

        Cara c1 = new Cara(f0, f1, f2);

        f0 = new Fila("naranja", "naranja", "naranja");
        f1 = new Fila("naranja", "naranja", "naranja");
        f2 = new Fila("naranja", "naranja", "naranja");

        Cara c2 = new Cara(f0, f1, f2);

        f0 = new Fila("amarillo", "amarillo", "amarillo");
        f1 = new Fila("amarillo", "amarillo", "amarillo");
        f2 = new Fila("amarillo", "amarillo", "amarillo");

        Cara c3 = new Cara(f0, f1, f2);

        f0 = new Fila("verde", "verde", "verde");
        f1 = new Fila("verde", "verde", "verde");
        f2 = new Fila("verde", "verde", "verde");

        Cara c4 = new Cara(f0, f1, f2);

        f0 = new Fila("azul", "azul", "azul");
        f1 = new Fila("azul", "azul", "azul");
        f2 = new Fila("azul", "azul", "azul");

        Cara c5 = new Cara(f0, f1, f2);
        return new Cubo(c0, c1, c2, c3, c4, c5);

    }

}
