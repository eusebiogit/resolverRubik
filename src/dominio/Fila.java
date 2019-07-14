package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author ordenador
 */
public class Fila implements Cloneable, Serializable {

    private int casilla0;
    private int casilla1;
    private int casilla2;

    /**
     *
     * @param casilla0
     * @param casilla1
     * @param casilla2
     */
    public Fila(String casilla0, String casilla1, String casilla2) {
        this.casilla0 =  traducirAInt(casilla0);
        this.casilla1 =  traducirAInt(casilla1);
        this.casilla2 =  traducirAInt(casilla2);

    }

    private int traducirAInt(String color) {
        int res = 0;
        switch (color) {
            case "rojo":
                res = 0;
                break;
            case "blanco":
                res = 1;
                break;
            case "naranja":
                res = 2;
                break;
            case "amarillo":
                res = 3;
                break;
            case "verde":
                res = 4;
                break;
            case "azul":
                res = 5;
                break;
        }
        return res;
    }

    private String traducirAString(int color) {
        String res = "";
        switch (color) {
            case 0:
                res = "rojo";
                break;
            case 1:
                res = "blanco";
                break;
            case 2:
                res = "naranja";
                break;
            case 3:
                res = "amarillo";
                break;
            case 4:
                res = "verde";
                break;
            case 5:
                res = "azul";
                break;
        }
        return res;
    }

    /**
     *
     * @param array
     */
    public Fila(String array[]) {
        if (array.length == 3) {
            this.casilla0 = traducirAInt(array[0]);
            this.casilla1 = traducirAInt(array[1]);
            this.casilla2 = traducirAInt(array[2]);
        }

    }

    /**
     * Casillas de una fila de izquierda a derecha
     *
     * @return
     */
    public ArrayList<String> getCasillas() {
        ArrayList<String> r = new ArrayList();
        r.add(traducirAString(casilla0));
        r.add(traducirAString(casilla1));
        r.add(traducirAString(casilla2));
        return r;
    }

    /**
     * Gira una fila
     *
     * @param l
     * @return
     */
    public Fila girar(Fila l) {

        Fila actual = (Fila) clone();
        casilla0 = traducirAInt(l.getCasillas().get(0));
        casilla1 = traducirAInt(l.getCasillas().get(1));
        casilla2 = traducirAInt(l.getCasillas().get(2));

        return actual;
    }

    /**
     *
     * @return
     */
    @Override
    public Object clone() {
        Object fila = new Fila(traducirAString(casilla0), traducirAString(casilla1), traducirAString(casilla2));
        return fila;
    }

    /**
     *
     * @return
     */
    public String toString() {
        String r = traducirAString(casilla0);
        String aux;
        r = rellenar(r);
        aux = rellenar(traducirAString(casilla1));
        r += " || " + aux + " || ";
        aux = rellenar(traducirAString(casilla2));
        r += aux;
        return r;
    }

    /**
     * usado por toString para dar formato
     *
     * @param nombre
     * @return
     */
    private String rellenar(String nombre) {
        for (int i = nombre.length(); i <= 8; i++) {
            nombre += " ";
        }
        return nombre;
    }

    /**
     * todas las casillas del mismo color, posible heurística
     *
     * @return
     */
    public boolean correcta() {
        return casilla0==casilla1
                && casilla1==casilla2;

    }

    @Override
    public boolean equals(Object obj) {
        boolean iguales = true;
        ArrayList<String> casillas = getCasillas();
        ArrayList<String> casillasObj = ((Fila) obj).getCasillas();

        if (casillas.size() == casillasObj.size()) {
            for (int i = 0; i < casillas.size(); i++) {
                if (!casillas.get(i).equals(casillasObj.get(i))) {
                    iguales = false;
                }
            }

        } else {
            iguales = false;
        }
        return iguales;
    }

}
