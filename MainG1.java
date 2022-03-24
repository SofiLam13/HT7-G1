/**
 * Universidad del Valle de Guatemala
 * Algoritmos y estructura de datos
 * Hoja de trabajo 7
 * Daniel Gómez 21429
 * Sofi Lam 21548
 * Grupo 1
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class MainG1 {
    public static void main(String[] args) {
        
        FileReader lector = new FileReader();
        Vista v = new Vista();

        ArrayList<String> palabras = lector.leer_archivo();

        // En lugar de arraylist va el arbol
        ArrayList<Palabra> palabras2 = new ArrayList<Palabra>();

        for(int k = 0; k<palabras.size() ;k++){
            String[] separado = palabras.get(k).split(",");
            Palabra p = new Palabra(separado[0], separado[1], separado[2]);
            palabras2.add(p);
        }

        v.imprimir_diccionario(palabras2);

    }
}

class Vista{

    public void imprimir_diccionario(ArrayList<Palabra> palabras){
        System.out.println();
        System.out.println("DICCIONARIO");
        for(int k = 0; k<palabras.size() ;k++){
            System.out.print(palabras.get(k).get_espanol());
            System.out.print(", ");
            System.out.print(palabras.get(k).get_ingles());
            System.out.print(", ");
            System.out.println(palabras.get(k).get_frances());
        }
        System.out.println();
    }
}

class FileReader{

    public ArrayList<String> leer_archivo(){

        ArrayList<String> palabras = new ArrayList<String>();

        try{
            String i = "diccionario.txt";
            File myFile = new File(i);
            Scanner scan = new Scanner(myFile);

            String s = "";

            while(scan.hasNextLine()){
                s = scan.nextLine();
                palabras.add(s);
            }
            scan.close();
        }
        catch(Exception e){
            String s = "FileReader: leer_palabras(): "+e.getMessage();
            throw new RuntimeException(s);
        }

        return palabras;
    }
}