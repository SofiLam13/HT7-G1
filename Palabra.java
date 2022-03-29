/**
 * Universidad del Valle de Guatemala
 * Algoritmos y estructura de datos
 * Hoja de trabajo 7
 * Daniel Gómez 21429
 * Sofi Lam 21548
 * Grupo 1
 */

public class Palabra {
    private String espanol;
    private String ingles;
    private String frances;

    public Palabra(String en, String es, String fran){
        this.ingles = en;
        this.espanol = es;
        this.frances = fran;
    }

    public String get_espanol(){
        return this.espanol;
    }

    public String get_ingles(){
        return this.ingles;
    }

    public String get_frances(){
        return this.frances;
    }
}
