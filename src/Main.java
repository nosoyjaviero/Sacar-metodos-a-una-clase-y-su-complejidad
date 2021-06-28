/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

//import com.sun.jdi.Method;
import java.lang.reflect.Method;
import java.time.temporal.Temporal;

/**
 *
 * @author Javier
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ListaCircular listaCircular = new ListaCircular();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader b = new BufferedReader(new FileReader("‪C:\\Users\\Javier\\Desktop\\a.java"));
         ChooseFile file = new ChooseFile();

        // File fichero = new File(file.getFile().toString());
        System.out.println(file.getFile().toString());
        renombrarClase(file.getFile().toString());

        obtenerMetodos();

        for (int i = 0; i < obtenerMetodos().size() - 9; i++) {
            listaCircular.agregarAlFinal(obtenerMetodos().get(i));
        }

        String fraseIntroducida = "";

        String metodo_A_Seleccionar = "";
        Nodo aux = listaCircular.inicio;

        do {
            //muestra la lista
            System.out.println("Lista de metodo de la clase");
            System.out.println("_______________________________");
            listaCircular.listar();
            System.out.println("_______________________________");
            System.out.println("Digte d para avanzar a la siguiente posicion");
            System.out.println("S para seleccionarr");

            fraseIntroducida = br.readLine();
            if (fraseIntroducida.equals("d")) {
                aux = aux.getSiguiente();
                metodo_A_Seleccionar = aux.getValor().toString();
                System.out.println("Estas en el " + metodo_A_Seleccionar);
            } else {
                if (fraseIntroducida.equals("s")) {
                    System.out.println("Opcion seleccionada! " + metodo_A_Seleccionar);
                } else {
                    System.out.println("Digite una opcion correcta");
                }
            }
        } while (!fraseIntroducida.equals("s"));
    }

    public static ArrayList<String> obtenerMetodos() {
        ArrayList<String> AraayMetodosClase = new ArrayList<String>();
        Class c = temp.class;
        Method[] met = c.getMethods();
        for (Method method : met) {
            String name = method.getName();
            //anade a arraylist
            AraayMetodosClase.add(name);
        }
        return AraayMetodosClase;
    }

    public static void escribirLineaFichero(File Ffichero, String SCadena) {
        try {
            if (!Ffichero.exists()) {
                Ffichero.createNewFile();
            }
            BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ffichero, true), "utf-8"));

            Fescribe.write(SCadena + "\r\n");
            Fescribe.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void BorrarFichero(File Ffichero) {
        try {

            if (Ffichero.exists()) {

                Ffichero.delete();
            }
        } catch (Exception ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
    }

    public static void eliminarPackageAlFichero(File FficheroAntiguo) {
        String[] palabras;
        String frase = "";

        String SnombFichNuev = (new File(".").getAbsolutePath()) + "\\src\\proyecto1\\Temporal.class";

        File FficheroNuevo = new File(SnombFichNuev);
        BorrarFichero(FficheroNuevo);
        try {

            if (FficheroAntiguo.exists()) {

                BufferedReader Flee = new BufferedReader(new FileReader(FficheroAntiguo));
                String Slinea;

                while ((Slinea = Flee.readLine()) != null) {

                    if (Slinea.contains("import")) {
                        escribirLineaFichero(FficheroNuevo, "");
                    } else {
                        escribirLineaFichero(FficheroNuevo, Slinea);
                    }
                }

                // FficheroNuevo.renameTo(FficheroAntiguo);
                Flee.close();

            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

    }

    public static void renombrarClase(String ruta) {
        File FficheroAntiguo = new File(ruta);
        String[] palabras;
        String frase = "";

        String SnombFichNuev = (new File(".").getAbsolutePath()) + "\\src\\temp.java";

        File FficheroNuevo = new File(SnombFichNuev);
        BorrarFichero(FficheroNuevo);
        try {

            if (FficheroAntiguo.exists()) {

                BufferedReader Flee = new BufferedReader(new FileReader(FficheroAntiguo));
                String Slinea;

                while ((Slinea = Flee.readLine()) != null) {

                    if (Slinea.contains("public class")) {

                        palabras = Slinea.split(" ");
                        int index = 0;
                        while ((palabras[index].equalsIgnoreCase(""))) {

                            for (int i = index + 1; i < palabras.length; i++) {
                                if (!(palabras[i].equalsIgnoreCase(""))) {

                                    palabras[index] = palabras[i];
                                    System.out.println("index " + index + " for " + i + " " + palabras[index]);
                                    index++;
                                }
                                System.out.println(palabras[i] + " " + i);
                            }

                        }
                        if (palabras[0].equals("public") && palabras[1].equals("class")) {
                            palabras[2] = "temp{";

                        }
                        for (int i = 0; i < palabras.length; i++) {
                            frase = frase + palabras[i] + " ";
                        }
                        escribirLineaFichero(FficheroNuevo, frase);
                    } else {
                        escribirLineaFichero(FficheroNuevo, Slinea);
                    }
                }

                String SnomAntiguo = FficheroAntiguo.getName();

                FficheroNuevo.renameTo(FficheroAntiguo);
                Flee.close();

            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        eliminarPackageAlFichero(FficheroNuevo);
    }

}
