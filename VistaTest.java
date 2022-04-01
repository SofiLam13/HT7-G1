import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
public class VistaTest {
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
	
	@Test
	public void testbuscar_palabra() {
		Palabra resultado=  Vista.buscar_palabra(root, "dog", "1", 1, true);
		Palabra esperado= new Palabra("dog", "perro", "chien");
		assertEquals(esperado.get_ingles(), resultado.get_ingles());
	}
	
	@Test
	public void testtraducir() {
		String prueba= Vista.traducir(root, {"The", "woman", "is", "happy"});
		String esperado= "la mujer is happy";
		assertEquals(esperado, prueba);
	}

}
