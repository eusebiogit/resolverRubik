package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representa una cara del cubo, un conjunto de filas en un cierto orden
 *
 * @author ordenador
 */
public class Cara implements Cloneable, Serializable {

    private Fila fila0;
    private Fila fila1;
    private Fila fila2;

    /**
     *
     * @param fila0
     * @param fila1
     * @param fila2
     */
    public Cara(Fila fila0, Fila fila1, Fila fila2) {
        this.fila0 = fila0;
        this.fila1 = fila1;
        this.fila2 = fila2;
    }

    /**
     * 
     * @param array 
     */
    public Cara(Fila array[]) {
        if (array.length == 3) {
            this.fila0 = array[0];
            this.fila1 = array[1];
            this.fila2 = array[2];
        }

    }

    /**
     * Devuelve el valor de la cara en función de sus casillas
     *
     * @return
     */
    public int getValorCara() {
        String color = this.getColorCara();
        ArrayList<Fila> filas = this.getFilas();
        ArrayList<String> casillas;
        int valor = 0;
        switch (color) {
            case "rojo":
                for (Fila f : filas) {
                    casillas = f.getCasillas();
                    for (String c : casillas) {
                        switch (c) {
                            case "blanco":
                            case "amarillo":
                            case "verde":
                            case "azul":
                                valor += 1;
                                break;
                            case "naranja":
                                valor += 2;
                                break;
                        }
                    }
                }
                break;
            case "blanco":
                for (Fila f : filas) {
                    casillas = f.getCasillas();
                    for (String c : casillas) {
                        switch (c) {
                            case "rojo":
                            case "naranja":
                            case "verde":
                            case "azul":
                                valor += 1;
                                break;
                            case "amarillo":
                                valor += 2;
                                break;
                        }
                    }
                }
                break;
            case "naranja":
                for (Fila f : filas) {
                    casillas = f.getCasillas();
                    for (String c : casillas) {
                        switch (c) {
                            case "amarillo":
                            case "naranja":
                            case "verde":
                            case "azul":
                                valor += 1;
                                break;
                            case "rojo":
                                valor += 2;
                                break;
                        }
                    }
                }
                break;
            case "amarillo":
                for (Fila f : filas) {
                    casillas = f.getCasillas();
                    for (String c : casillas) {
                        switch (c) {
                            case "rojo":
                            case "naranja":
                            case "verde":
                            case "azul":
                                valor += 1;
                                break;
                            case "blanco":
                                valor += 2;
                                break;
                        }
                    }
                }
                break;
            case "verde":
                for (Fila f : filas) {
                    casillas = f.getCasillas();
                    for (String c : casillas) {
                        switch (c) {
                            case "rojo":
                            case "naranja":
                            case "verde":
                            case "blanco":
                                valor += 1;
                                break;
                            case "azul":
                                valor += 2;
                                break;
                        }
                    }
                }
                break;
            case "azul":
                for (Fila f : filas) {
                    casillas = f.getCasillas();
                    for (String c : casillas) {
                        switch (c) {
                            case "rojo":
                            case "naranja":
                            case "amarillo":
                            case "blanco":
                                valor += 1;
                                break;
                            case "verde":
                                valor += 2;
                                break;
                        }
                    }
                }
                break;
        }
        return valor;
    }

    /**
     *
     * @return
     */
    public ArrayList<Fila> getFilas() {
        ArrayList<Fila> r = new ArrayList();
        r.add(fila0);
        r.add(fila1);
        r.add(fila2);
        return r;
    }

    /**
     * Modifica la información de la cara para simular un giro de una fila, se
     * corresponde con las operaciones X
     *
     * @param c
     * @param fila
     * @return
     */
    public Cara girar(Cara c, int fila) {
        Cara actual = (Cara) clone();
        Fila f = c.getFilas().get(fila);
        getFilas().get(fila).girar(f);
        return actual;
    }

    /**
     * Transponer una cara se corresponde con las operaciones Z Sentido
     * contrario al que siguen las agujas de un reloj
     */
    public void transponerIzquierdo() {
        Fila filaA = new Fila(fila0.getCasillas().get(2), fila1.getCasillas().get(2),
                fila2.getCasillas().get(2));
        Fila filaB = new Fila(fila0.getCasillas().get(1),
                fila1.getCasillas().get(1),
                fila2.getCasillas().get(1));
        Fila filaC = new Fila(fila0.getCasillas().get(0),
                fila1.getCasillas().get(0),
                fila2.getCasillas().get(0));
        fila0.girar(filaA);
        fila1.girar(filaB);
        fila2.girar(filaC);
    }

    /**
     * Transponer una cara se corresponde con las operaciones Z Sentido que
     * siguen las agujas de un reloj
     */
    public void transponerDerecho() {
        Fila filaA = new Fila(fila2.getCasillas().get(0),
                fila1.getCasillas().get(0),
                fila0.getCasillas().get(0));
        Fila filaB = new Fila(fila2.getCasillas().get(1),
                fila1.getCasillas().get(1),
                fila0.getCasillas().get(1));
        Fila filaC = new Fila(fila2.getCasillas().get(2),
                fila1.getCasillas().get(2),
                fila0.getCasillas().get(2));
        fila0.girar(filaA);
        fila1.girar(filaB);
        fila2.girar(filaC);

    }

    /**
     * El color de una cara, es el color de su casilla central
     *
     * @return
     */
    public String getColorCara() {
        return fila1.getCasillas().get(1);
    }

    /**
     * Muestra una cara en la salida
     *
     * @return
     */
    @Override
    public String toString() {
        String r = "";

        r += "------------------------------------\n";
        r += fila0 + "|\n";

        r += fila1 + "|\n";
        r += fila2 + "|\n";

        r += "------------------------------------";
        return r;
    }

    /**
     * Devuelve una copia de esta cara
     *
     * @return
     */
    @Override
    public Object clone() {
        return new Cara((Fila) fila0.clone(), (Fila) fila1.clone(), (Fila) fila2.clone());
    }

    /**
     * Verdadero cuando la cara del argumento es idéntica
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        boolean iguales = true;
        ArrayList<Fila> filas = getFilas();
        ArrayList<Fila> filasObj = ((Cara) obj).getFilas();
        if (filas.size() == filasObj.size()) {
            for (int i = 0; i < filas.size(); i++) {
                if (!filas.get(i).equals(filasObj.get(i))) {
                    iguales = false;
                }
            }
        } else {
            iguales = false;
        }
        return iguales;
    }

    /**
     * Número de casillas que se corresponden con el color de esta cara
     *
     * @return
     */
    public int completado() {
        String color = getColorCara();
        int r = 0;

        for (Fila f : getFilas()) {
            for (String s : f.getCasillas()) {
                if (s.equals(color)) {
                    r++;
                }
            }
        }
        return r;
    }

    /**
     * Hay una cruz en la cara por si sirve de algo
     *
     * @return
     */
    public boolean cruz() {
        return fila0.getCasillas().get(1).equals(getColorCara()) && fila1.correcta()
                && fila2.getCasillas().get(1).equals(getColorCara());
    }

}
