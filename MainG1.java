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

        ArrayList<String> palabras_separadas = lector.leer_archivo();

        // En lugar de arraylist va el arbol
        ArrayList<Palabra> palabras = new ArrayList<Palabra>();

        for(int k = 0; k<palabras_separadas.size(); k++){
            String[] separado = palabras_separadas.get(k).split("\\,");
            Palabra p = new Palabra(separado[0], separado[1], separado[2]);
            palabras.add(p);
        }

        v.imprimir_diccionario(palabras);

        String s = v.solicitar_oracion();

        System.out.println(s);

        System.out.println();

    }
}

class Vista{

    Scanner scan = new Scanner(System.in);

    private String solicitar_string(String s){
        String txt = "";
        boolean continuar = true;
        try{
            while(continuar){
                System.out.print(s);
                this.scan = new Scanner(System.in);
                String texto = scan.nextLine();
                if(texto.equals("")){
                    System.out.println("\t Error: debe de ingresar un texto valido.");
                    System.out.println();
                }
                else{
                    txt = texto;
                    System.out.println("-------------------------------------------------------");
                    continuar = false;                   
                }
            }
        }
        catch(Exception e){
            System.out.println("\t Error: debe de ingresar un texto valido.");
            System.out.println();
        }
        return txt;
    }

    public void imprimir_diccionario(ArrayList<Palabra> palabras){
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("DICCIONARIO");
        System.out.println("-------------------------------------------------------");
        for(int k = 0; k<palabras.size() ;k++){
            System.out.print(palabras.get(k).get_espanol());
            System.out.print(", ");
            System.out.print(palabras.get(k).get_ingles());
            System.out.print(", ");
            System.out.println(palabras.get(k).get_frances());
        }
        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

    public String solicitar_oracion(){
        String s = "Ingrese la oracion a traducir:\n>> ";
        String s2 = solicitar_string(s);
        return s2;
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