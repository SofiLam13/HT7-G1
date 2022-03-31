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

public class FileReader{

    public ArrayList<String> leer_archivo(){

        ArrayList<String> palabras = new ArrayList<String>();

        try{
            String i = "diccionario.txt";
            File myFile = new File(i);
            Scanner scan = new Scanner(myFile);

            String s = "";

            while(scan.hasNextLine()){
                s = scan.nextLine();
                palabras.add(s.toLowerCase());
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