package persistencia;

import dominio.Cara;
import dominio.Cubo;
import dominio.Estado;
import dominio.Fila;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operaciones para el tratamiento de ficheros
 *
 * @author ordenador
 */
public class OperacionesPersistencia {

    /**
     * Escribir en un fichero
     *
     * @param nombre
     * @param contenido
     */
    public static void guardarenFichero(String nombre, String contenido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombre);
            pw = new PrintWriter(fichero);
            pw.println(contenido);

        } catch (Exception e) {
            System.out.println("");
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.out.println("");
                e2.printStackTrace();
            }
        }
    }

    /**
     * Guarda objeto
     *
     * @param o
     * @param ruta
     */
    public static void guardarObjetoFichero(Object o, String ruta) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ruta));
            oos.writeObject(o);
        } catch (IOException ex) {
            Logger.getLogger(OperacionesPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (null != ruta) {
                oos.close();
            }
        } catch (Exception e2) {
            System.out.println("");
            e2.printStackTrace();
        }
    }

    /**
     * Lee objeto
     *
     * @param ruta
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object leerObjetoFichero(String ruta) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        Object obj = null;
        ois = new ObjectInputStream(new FileInputStream(ruta));
        obj = ois.readObject();
        ois.close();
        return obj;
    }

    
    /**
     * Lee un cubo a partir de un archivo con la direccion pasada como parametro
     * el archivo debe estar escrito con el formato del cubo
     * @param dir
     * @return 
     */
    public static Cubo leerCubo(String dir) {
        Cubo cubo = null;
        int casillas = 0;
        Fila fila;
        int filas = 0;
        Cara cara;
        int caras = 0;
        String casillasArray[] = new String[3];
        Fila filasArray[] = new Fila[3];
        Cara carasArray[] = new Cara[6];
        String lineas[];
        String color;
        String linea;

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(dir);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                lineas = linea.split("\\s");
                for (int i = 0; i < lineas.length; i++) {
                    color = lineas[i];
                    if (Cubo.colorValido(color)) {
                        casillasArray[casillas % 3] = color;  //cada 3 es una fila
                        casillas++;
                        if (casillas % 3 == 0) {
                            filasArray[filas % 3] = new Fila(casillasArray.clone());  //etc.
                            filas++;
                            if (filas % 3 == 0) {
                                carasArray[caras] = new Cara(filasArray.clone());
                                caras++;
                            }
                        }

                    }
                }

//                printLinea(lineas);
            }

            cubo = new Cubo(
                    carasArray[0],
                    carasArray[1],
                    carasArray[2],
                    carasArray[3],
                    carasArray[4],
                    carasArray[5]
            );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return cubo;
    }

    
}
