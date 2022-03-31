/**
 * Universidad del Valle de Guatemala
 * Algoritmos y estructura de datos
 * Hoja de trabajo 7
 * Daniel Gómez 21429
 * Sofi Lam 21548
 * Grupo 1
 */

import java.util.ArrayList;

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

            continuar = v.continuar_en_menu();
        }

        v.despedida();

    }
}