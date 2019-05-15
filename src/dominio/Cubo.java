package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representa el cubo en el programa
 *
 * @author ordenador
 */
public class Cubo implements Cloneable, Serializable {

    /**
     * Caras en orden a como debe verse el cubo físico para aplicar las
     * operaciones correctamente El cubo debe colocarse con la cara0 (roja) de
     * frente, y la cara verde arriba (cara5)
     */
    private Cara cara4;  //verde  (arriba)
    private Cara cara0, cara1, cara2, cara3;  //roja, blanca, naranja (atras), amarilla
    private Cara cara5;     //azul (abajo)

    /**
     *
     * @param cara0
     * @param cara1
     * @param cara2
     * @param cara3
     * @param cara4
     * @param cara5
     */
    public Cubo(Cara cara0, Cara cara1, Cara cara2, Cara cara3, Cara cara4, Cara cara5) {
        this.cara4 = cara4;
        this.cara0 = cara0;
        this.cara1 = cara1;
        this.cara2 = cara2;
        this.cara3 = cara3;
        this.cara5 = cara5;
    }

    /**
     * Colocando el cubo en la posición indicada (rojo, frente; verde, arriba),
     * un movimiento X corresponde a girar X0 X1 X2 una fila hacia la derecha
     *
     * @param fila
     */
    public void X(int fila) {
        ArrayList<Cara> carasHermanas = carasHermanas();
        Cara aux = carasHermanas.get(3);
        for (int i = 0; i < carasHermanas.size(); i++) {
            aux = carasHermanas.get(i).girar(aux, fila);
        }

        if (fila == 0) {
            cara4.transponerIzquierdo();
        }

        if (fila == 2) {
            cara5.transponerDerecho();
        }
        colocar();
    }

    /**
     * Con el cubo bien colocado, el movimiento Y consiste en girar una columna
     * hacia abajo Y0,Y1,Y2
     *
     * @param fila
     */
    public void Y(int fila) {
        girarInverso();
        X(2 - fila);
    }

    /**
     * Con el cubo bien colocado, el movimiento Z consiste en girar una cara
     * hacia la derecha Es decir Z0 transpone la cara roja, Z1 aplica sobre el
     * plano detrás de la cara roja (intermedio) y Z2 transpone la cara naranja
     *
     * @param fila
     */
    public void Z(int fila) {
        girarAbajo();
        X(2 - fila);
    }

    /**
     * Para realizar una de las operaciones posibles
     *
     * @param op
     */
    public void operarCubo(ArrayList<String> op) {
        for (String s : op) {
            switch (s.charAt(0)) {
                case 'X':
                    X(Integer.parseInt(s.charAt(1) + ""));
                    break;
                case 'Y':
                    Y(Integer.parseInt(s.charAt(1) + ""));
                    break;
                case 'Z':
                    Z(Integer.parseInt(s.charAt(1) + ""));
                    break;
            }
        }
    }

    /**
     * Manipula el cubo en una posición diferente de la original (cara roja al
     * frente, verde arriba) es necesario para algunas operaciones
     */
    public void girarIzquierda() {
        ArrayList<Cara> caras = (ArrayList<Cara>) getCaras().clone();
        cara0 = caras.get(3);
        cara1 = caras.get(0);
        cara2 = caras.get(1);
        cara3 = caras.get(2);
        cara4.transponerIzquierdo();
        cara5.transponerDerecho();

    }

    /**
     * Manipula el cubo en una posición diferente de la original (cara roja al
     * frente, verde arriba) es necesario para algunas operaciones
     */
    public void girarDerecho() {
        ArrayList<Cara> caras = (ArrayList<Cara>) getCaras().clone();
        cara0 = caras.get(1);
        cara1 = caras.get(2);
        cara2 = caras.get(3);
        cara3 = caras.get(0);
        cara4.transponerDerecho();
        cara5.transponerIzquierdo();
    }

    /**
     * Manipula el cubo en una posición diferente de la original (cara roja al
     * frente, verde arriba) es necesario para algunas operaciones
     */
    public void girarAbajo() {
        ArrayList<Cara> caras = (ArrayList<Cara>) getCaras().clone();
        cara0 = caras.get(4);
        cara1.transponerIzquierdo();
        cara2 = caras.get(5);
        cara2.transponerDerecho();
        cara2.transponerDerecho();
        cara3.transponerDerecho();

        cara4 = caras.get(2);
        cara4.transponerDerecho();
        cara4.transponerDerecho();
        cara5 = caras.get(0);

    }

    /**
     * Manipula el cubo en una posición diferente de la original (cara roja al
     * frente, verde arriba) es necesario para algunas operaciones
     */
    public void girarArriba() {
        ArrayList<Cara> caras = (ArrayList<Cara>) getCaras().clone();
        cara0 = caras.get(5);
        cara1.transponerDerecho();
        cara2 = caras.get(4);
        cara2.transponerDerecho();
        cara2.transponerDerecho();

        cara3.transponerIzquierdo();

        cara4 = caras.get(0);

        cara5 = caras.get(2);
        cara5.transponerDerecho();
        cara5.transponerDerecho();

    }

    /**
     * Manipula el cubo en una posición diferente de la original (cara roja al
     * frente, verde arriba) es necesario para algunas operaciones
     */
    public void girarDirecto() {
        ArrayList<Cara> caras = (ArrayList<Cara>) getCaras().clone();
        cara0.transponerDerecho();
        cara1 = caras.get(4);
        cara1.transponerDerecho();

        cara2.transponerIzquierdo();

        cara3 = caras.get(5);
        cara3.transponerDerecho();

        cara4 = caras.get(3);
        cara4.transponerDerecho();

        cara5 = caras.get(1);
        cara5.transponerDerecho();
    }

    /**
     * Manipula el cubo en una posición diferente de la original (cara roja al
     * frente, verde arriba) es necesario para algunas operaciones
     */
    public void girarInverso() {
        ArrayList<Cara> caras = (ArrayList<Cara>) getCaras().clone();
        cara0.transponerIzquierdo();
        cara1 = caras.get(5);
        cara1.transponerIzquierdo();

        cara2.transponerDerecho();

        cara3 = caras.get(4);
        cara3.transponerIzquierdo();

        cara4 = caras.get(1);
        cara4.transponerIzquierdo();

        cara5 = caras.get(3);
        cara5.transponerIzquierdo();

    }

    /**
     * Devuelve las caras que están colocadas alrededor del cubo cuando este
     * está en la posición indicada
     *
     * @return
     */
    public ArrayList<Cara> carasHermanas() {
        ArrayList<Cara> r = new ArrayList();
        r.add(cara0);
        r.add(cara1);
        r.add(cara2);
        r.add(cara3);
        return r;
    }

    /**
     * Devuelve las caras en orden según la posición indicada
     *
     * @return
     */
    public ArrayList<Cara> getCaras() {
        ArrayList<Cara> caras = new ArrayList();
        caras.add(cara0);
        caras.add(cara1);
        caras.add(cara2);
        caras.add(cara3);
        caras.add(cara4);
        caras.add(cara5);

        return caras;

    }

    /**
     * Muestra una representación de las caras del cubo la cara4 debe situarse
     * arriba y la cara5 abajo en el cubo físico
     *
     * @return
     */
    @Override
    public String toString() {
        String r = "Cara 0:\n";
        r += cara0 + "\n";
        r += "Cara1:\n";
        r += cara1 + "\n";
        r += "Cara2:\n";
        r += cara2 + "\n";
        r += "Cara3:\n";
        r += cara3 + "\n";
        r += "Cara4:\n";
        r += cara4 + "\n";
        r += "Cara5:\n";
        r += cara5 + "\n";

        return r;
    }

    /**
     * Verdadero cuando dos cubos son idénticos
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        boolean iguales = true;
        Cubo otro = (Cubo) obj;
        otro.colocar();
        colocar();

        ArrayList<Cara> caras = getCaras();
        ArrayList<Cara> carasObj = otro.getCaras();
        if (caras.size() == carasObj.size()) {
            for (int i = 0; i < caras.size() && iguales; i++) {
                if (!caras.get(i).equals(carasObj.get(i))) {
                    iguales = false;
                }
            }
        } else {
            iguales = false;
        }
        return iguales;
    }

    /**
     * Devuelve el color de las caras en orden
     *
     * @return
     */
    public ArrayList<String> getColoresCaras() {
        ArrayList<String> casillas = new ArrayList();
        casillas.add(cara0.getColorCara());
        casillas.add(cara1.getColorCara());
        casillas.add(cara2.getColorCara());
        casillas.add(cara3.getColorCara());
        casillas.add(cara4.getColorCara());
        casillas.add(cara5.getColorCara());
        return casillas;
    }

    /**
     * Coloca el cubo en la posición indicada tal como debe situarse físicamente
     * para su resolución
     */
    public void colocar() {
        ArrayList<String> colores = getColoresCaras();
        if (!colocado()) {
            switch (colores.indexOf("rojo")) {
                case 0:
                case 1:
                case 2:
                case 3:
                    while (!getColoresCaras().get(0).equals("rojo")) {
                        girarDerecho();
                    }
                    break;
                default:
                    while (!getColoresCaras().get(0).equals("rojo")) {
                        girarAbajo();
                    }
            }
            while (!colocado()) {
                girarDirecto();
            }
        }
    }

    /**
     * Verdadero si el cubo está colocado en la posición indicada
     *
     * @return
     */
    public boolean colocado() {
        ArrayList<String> cuboColocado = new ArrayList();
        cuboColocado.add("rojo");
        cuboColocado.add("blanco");
        cuboColocado.add("naranja");
        cuboColocado.add("amarillo");
        cuboColocado.add("verde");
        cuboColocado.add("azul");
        boolean colocado = false;
        if (cuboColocado.equals(getColoresCaras())) {
            colocado = true;
        }
        return colocado;
    }

    /**
     * Devuelve una copia de este cubo
     *
     * @return
     */
    @Override
    public Object clone() {
        return new Cubo((Cara) cara0.clone(), (Cara) cara1.clone(), (Cara) cara2.clone(), (Cara) cara3.clone(),
                (Cara) cara4.clone(), (Cara) cara5.clone());
    }

    /**
     * Devuelve el número de casillas que están en su sitio
     *
     * @return
     */
    public int completado() {
        int r = 0;
        for (Cara c : this.getCaras()) {
            r += c.completado();
        }
        return r;
    }
    
    /**
     * Devuelve la suma de las distancias de las casillas a su posición correcta
     * Si una casilla está en su cara vale 0 si está en una cara adyacente 1...
     * @return 
     */
    public int getValorDistanciaManhatan(){
        ArrayList<Cara> caras=this.getCaras();
        int valor=0;
        for (Cara c:caras){
            valor+=c.getValorCara();
        }
        return valor;
    }
    

    /**
     * Identifica un cubo
     *
     * @return
     */
    public int hashCode() {
        int hashcode = 7;
        int primos[] = {2, 3, 5, 7, 11, 13,
            17, 19, 23, 29, 31, 37,
            41, 43, 47, 53, 101, 59,
            61, 67, 71, 73, 79, 83,
            103, 89, 97, 107, 109, 113,
            127, 131, 137, 139, 149, 151,
            157, 163, 167, 173, 179, 181,
            191, 193, 197, 199, 211, 223,
            227, 229, 233, 239, 241, 251};
        ArrayList<Cara> caras = getCaras();
        ArrayList<Fila> filas = null;
        ArrayList<String> casillas = null;
        int i = 0;
        for (Cara cara : caras) {
            filas = cara.getFilas();
            for (Fila f : filas) {
                casillas = f.getCasillas();
                for (String c : casillas) {
                    hashcode += primos[i++] * get(c) * (i);
                }
            }
        }
        return hashcode;
    }

    /**
     * Asigna un entero a cada color
     * @param color
     * @return 
     */
    public int get(String color) {
        int r = 1;
        switch (color) {
            case "rojo":
                r = 257;
                break;
            case "blanco":
                r = 263;
                break;
            case "naranja":
                r = 269;
                break;
            case "amarillo":
                r = 271;
                break;
            case "verde":
                r = 277;
                break;
            case "azul":
                r = 281;
                break;
        }
        return r;
    }

}
