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

        // Arraylist para imprimir diccionario
        ArrayList<Palabra> palabras = new ArrayList<Palabra>();

        //ARBOL
        BST_Palabra myBST = new BST_Palabra();
        Node root = null;

        //separar traduccion de las palabras y anadir a Binary Search Tree 
        for(int k = 0; k<palabras_separadas.size(); k++){
            String[] separado = palabras_separadas.get(k).split("\\,");
            Palabra p = new Palabra(separado[0], separado[1], separado[2]);
            palabras.add(p);

            root = myBST.insert(root, p);
        }

        boolean continuar = true;

        while(continuar){
            //imprimir diccionario
            v.imprimir_diccionario(palabras);

            //solicitar oracion a traducir al usuario
            String[] s = v.solicitar_oracion();
            String origen = "";

            for(int k = 0; k<s.length ;k++){
                // System.out.print(s[k]+" ");
                origen = origen.concat(s[k]+" ");
            }
            
            String traduccion = v.traducir(root, s);
            
            v.imprimir_traduccion(origen, traduccion);
        }
    }
}

class Vista{

    Scanner scan = new Scanner(System.in);

    private String solicitar_string(String s, boolean numeros){
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
                else if(numeros){
                    if((texto.equals("1"))||(texto.equals("2"))||(texto.equals("3"))){
                        txt = texto;
                        System.out.println("-------------------------------------------------------");
                        continuar = false;

                    }
                    else{
                        System.out.println("\t Error: debe de ingresar un texto valido entre 1 y 3.");
                        System.out.println();
                    }
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
    }

    public String[] solicitar_oracion(){
        System.out.println("- Traduccion -");
        System.out.println("Por favor ingresar la oracion con espacios...");
        String s = "\nIngrese la oracion a traducir:\n>> ";
        String[] s2 = solicitar_string(s, false).toLowerCase().split(" ");
        return s2;
    }

    private String solicitar_idioma_origen(){
        System.out.println("Ingrese el idioma de origen de traduccion...");
        System.out.println("1. Ingles");
        System.out.println("2. Espanol");
        System.out.println("3. Frances");
        String s = ">> ";
        String s2 = solicitar_string(s, true);
        return s2;
    }

    private String solicitar_idioma_destino(){
        System.out.println("Ingrese el idioma de destino de traduccion...");
        System.out.println("1. Ingles");
        System.out.println("2. Espanol");
        System.out.println("3. Frances");
        String s = ">> ";
        String s2 = solicitar_string(s, true);
        return s2;
    }

    public String traducir(Node myRoot, String[] myOracion){
        String origen = solicitar_idioma_origen();
        String destino = solicitar_idioma_destino();
        String[] oracion = myOracion;

        for(int k = 0; k<oracion.length ;k++){
            switch (destino) {
                //Ingles
                case "1":
                    if(!(buscar_palabra(myRoot, oracion[k], origen)==null)){
                        oracion[k] = buscar_palabra(myRoot, oracion[k], origen).get_ingles();
                    }
                    break;

                //Espanol
                case "2":
                    if(!(buscar_palabra(myRoot, oracion[k], origen)==null)){
                        oracion[k] = buscar_palabra(myRoot, oracion[k], origen).get_espanol();
                    }
                    break;
            
                //frances
                default:
                    if(!(buscar_palabra(myRoot, oracion[k], origen)==null)){
                        oracion[k] = buscar_palabra(myRoot, oracion[k], origen).get_frances();
                    }
                    break;
            }
        }

        String traduccion = "";

        for(int i = 0; i<oracion.length ;i++){
            traduccion = traduccion.concat(oracion[i]+" ");
        }

        return traduccion;
    }

    private Palabra buscar_palabra(Node myRoot, String s, String origen){

        Palabra myPalabra = null;
        
        boolean continuar = true;

        Node myNewRoot = myRoot;

        while(continuar){

            if((myNewRoot.data.get_espanol().equals(s))||(myNewRoot.data.get_frances().equals(s))||(myNewRoot.data.get_ingles().equals(s))){
                myPalabra = myNewRoot.data;
                // System.out.println("f");
                continuar = false;
            }
            else{
                // System.out.println("t");

                switch (origen) {
                    //Ingles
                    case "1":
                        if(myNewRoot.data.get_ingles().compareTo(s)>0){
                            if(!(myNewRoot.left==null)){
                                myNewRoot = myNewRoot.left;
                            }
                            else{
                                continuar = false;
                            }
                        }
                        else{
                            if(!(myNewRoot.right==null)){
                                myNewRoot = myNewRoot.right;
                            }
                            else{
                                continuar = false;
                            }
                        }
                        break;

                    //espanol
                    case "2":
                        if(myNewRoot.data.get_espanol().compareTo(s)>0){
                            if(!(myNewRoot.left==null)){
                                myNewRoot = myNewRoot.left;
                            }
                            else{
                                continuar = false;
                            }
                        }
                        else{
                            if(!(myNewRoot.right==null)){
                                myNewRoot = myNewRoot.right;
                            }
                            else{
                                continuar = false;
                            }
                        }
                        break;
                
                    //frances
                    default:
                        if(myNewRoot.data.get_frances().compareTo(s)>0){
                            if(!(myNewRoot.left==null)){
                                myNewRoot = myNewRoot.left;
                            }
                            else{
                                continuar = false;
                            }
                        }
                        else{
                            if(!(myNewRoot.right==null)){
                                myNewRoot = myNewRoot.right;
                            }
                            else{
                                continuar = false;
                            }
                        }
                        break;
                }
            }
        }

        return myPalabra;
    }

    public void imprimir_traduccion(String origen, String traduccion){
        System.out.println("- TRADUCCION -");
        System.out.println("Oracion de Origen: "+(origen.substring(0, 1).toUpperCase() + origen.substring(1)));
        System.out.println("Traduccion: "+(traduccion.substring(0, 1).toUpperCase() + traduccion.substring(1)));
        System.out.println("-------------------------------------------------------");
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