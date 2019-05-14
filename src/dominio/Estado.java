package dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 * El estado viene determinado por la representación de un cubo y el objetivo
 *
 * @author ordenador
 */
public class Estado implements Serializable {

    private Cubo actual;
    private Cubo alcanzar;
    private String accion;

    /**
     *
     * @param actual
     * @param accion
     */
    public Estado(Cubo actual, String accion) {
        this.actual = actual;
        this.accion = accion;
        inicializarAlcanzar();
    }

    /**
     *
     * @param actual
     * @param accion
     * @param alcanzar
     */
    public Estado(Cubo actual, String accion, Cubo alcanzar) {
        this.actual = actual;
        this.alcanzar = alcanzar;
        this.accion = accion;

    }

    /**
     * Devuelve la acción de este estado
     *
     * @return
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Cubo asociado a este estado
     *
     * @return
     */
    public Cubo getActual() {
        return actual;
    }

    /**
     *
     * @param actual
     */
    public void setActual(Cubo actual) {
        this.actual = actual;
    }

    /**
     * Objetivo
     *
     * @return
     */
    public Cubo getAlcanzar() {
        return alcanzar;
    }

    /**
     *
     * @param alcanzar
     */
    public void setAlcanzar(Cubo alcanzar) {
        this.alcanzar = alcanzar;
    }

    /**
     * Objetivo ideal cubo resuelto
     */
    private void inicializarAlcanzar() {
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
        alcanzar = new Cubo(c0, c1, c2, c3, c4, c5);
    }

    /**
     * Muestra por salida el cubo asociado a ese estado
     * @return 
     */
    public String toString() {
        return this.actual.toString();

    }

    /**
     * Un estado puede ser igual que otro
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        Estado otro = (Estado) obj;
        return otro.getActual().equals(actual) && otro.getAlcanzar().equals(alcanzar);
    }

    /**
     * Identifica un estado
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.actual);
        hash = 97 * hash + Objects.hashCode(this.alcanzar);
        return hash < 0 ? hash * -1 : hash;
    }

}
