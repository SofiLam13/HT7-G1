

import java.util.Scanner;
import java.util.ArrayList;

public class Vista{

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
                    if(!(buscar_palabra(myRoot, oracion[k], origen, k, true)==null)){
                        oracion[k] = buscar_palabra(myRoot, oracion[k], origen, k, false).get_ingles();
                    }
                    break;

                //Espanol
                case "2":
                    if(!(buscar_palabra(myRoot, oracion[k], origen, k, true)==null)){
                        oracion[k] = buscar_palabra(myRoot, oracion[k], origen, k, false).get_espanol();
                    }
                    break;
            
                //frances
                default:
                    if(!(buscar_palabra(myRoot, oracion[k], origen, k, true)==null)){
                        oracion[k] = buscar_palabra(myRoot, oracion[k], origen, k, false).get_frances();
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

    public Palabra buscar_palabra(Node myRoot, String s, String origen, int contador_2, boolean imprimir){

        Palabra myPalabra = null;
        
        boolean continuar = true;

        Node myNewRoot = myRoot;

        if(imprimir){
            System.out.println(" RECORRIDO DE ARBOL No."+(contador_2+1)+" - Objetivo: "+s+" -");
        }

        int contador = 0;

        if(imprimir){
            System.out.println("Nodo "+(contador+1)+": "+(myNewRoot.data.get_ingles()));
        }

        while(continuar){

            if((myNewRoot.data.get_espanol().equals(s))||(myNewRoot.data.get_frances().equals(s))||(myNewRoot.data.get_ingles().equals(s))){
                myPalabra = myNewRoot.data;
                if(imprimir){
                    System.out.println("\tPalabra encontrada.");    
                }
                continuar = false;
            }
            else{
                switch (origen) {
                    //Ingles
                    case "1":
                        if(myNewRoot.data.get_ingles().compareTo(s)>0){
                            if(!(myNewRoot.left==null)){
                                myNewRoot = myNewRoot.left;
                                if(imprimir){
                                    System.out.println("Nodo "+(contador+1)+": "+(myNewRoot.data.get_ingles()));
                                }
                                contador++;
                            }
                            else{
                                if(imprimir){
                                    System.out.println("\tPalabra no encontrada.");
                                }
                                continuar = false;                                
                            }
                        }
                        else{
                            if(!(myNewRoot.right==null)){
                                myNewRoot = myNewRoot.right;
                                if(imprimir){
                                    System.out.println("Nodo "+(contador+1)+": "+(myNewRoot.data.get_ingles()));
                                }
                                contador++;
                            }
                            else{
                                if(imprimir){
                                    System.out.println("\tPalabra no encontrada.");
                                }
                                continuar = false; 
                            }
                        }
                        break;

                    //espanol
                    case "2":
                        if(myNewRoot.data.get_espanol().compareTo(s)>0){
                            if(!(myNewRoot.left==null)){
                                myNewRoot = myNewRoot.left;
                                if(imprimir){
                                    System.out.println("Nodo "+(contador+1)+": "+(myNewRoot.data.get_ingles()));
                                }
                                contador++;
                            }
                            else{
                                if(imprimir){
                                    System.out.println("\tPalabra no encontrada.");
                                }
                                continuar = false; 
                            }
                        }
                        else{
                            if(!(myNewRoot.right==null)){
                                myNewRoot = myNewRoot.right;
                                if(imprimir){
                                    System.out.println("Nodo "+(contador+1)+": "+(myNewRoot.data.get_ingles()));
                                }
                                contador++;
                            }
                            else{
                                if(imprimir){
                                    System.out.println("\tPalabra no encontrada.");
                                }
                                continuar = false; 
                            }
                        }
                        break;
                
                    //frances
                    default:
                        if(myNewRoot.data.get_frances().compareTo(s)>0){
                            if(!(myNewRoot.left==null)){
                                myNewRoot = myNewRoot.left;
                                if(imprimir){
                                    System.out.println("Nodo "+(contador+1)+": "+(myNewRoot.data.get_ingles()));
                                }
                                contador++;
                            }
                            else{
                                if(imprimir){
                                    System.out.println("\tPalabra no encontrada.");
                                }
                                continuar = false; 
                            }
                        }
                        else{
                            if(!(myNewRoot.right==null)){
                                myNewRoot = myNewRoot.right;
                                if(imprimir){
                                    System.out.println("Nodo "+(contador+1)+": "+(myNewRoot.data.get_ingles()));
                                }
                                contador++;
                            }
                            else{
                                if(imprimir){
                                    System.out.println("\tPalabra no encontrada.");
                                }
                                continuar = false; 
                            }
                        }
                        break;
                }
            }
        }

        System.out.println("-------------------------------------------------------");

        return myPalabra;
    }

    public void imprimir_traduccion(String origen, String traduccion){
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("- TRADUCCION -");
        System.out.println("-------------------------------------------------------");
        System.out.println("Oracion de Origen: "+(origen.substring(0, 1).toUpperCase() + origen.substring(1)));
        System.out.println("Traduccion: "+(traduccion.substring(0, 1).toUpperCase() + traduccion.substring(1)));
        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

    public boolean continuar_en_menu(){
        String s = ">> ";
        System.out.println("Desea seguir traduciendo?");
        System.out.println("1. Si");
        System.out.println("2. No");
        String s2 = solicitar_string(s, true);
        boolean continuar = false;
        if(s2.equals("1")){
            continuar = true;
        }
        return continuar;
    }

    public void despedida(){
        System.out.println("Muchas gracias por usar nuestro traductor!");
        System.out.println("Esperamos verte pronto!!!");
        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

}

