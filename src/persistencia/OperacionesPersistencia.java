package persistencia;

import dominio.Estado;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
}
