/**
 * Universidad del Valle de Guatemala
 * Algoritmos y estructura de datos
 * Hoja de trabajo 7
 * Daniel Gómez 21429
 * Sofi Lam 21548
 * Grupo 1
 */

class Node{
    Palabra data;
    Node left;
    Node right;
}

class BST_Palabra{

    public Node createNewNode(Palabra k){
        Node a = new Node();
        a.data = k;
        a.left = null;
        a.right = null;
        return a;
    }


    public Node insert(Node node, Palabra val){
        if(node == null){
            return createNewNode(val);
        }
        if((node.data.get_ingles()).compareTo(val.get_ingles())>0){
            node.left = insert(node.left, val);
        }
        else if((node.data.get_ingles()).compareTo(val.get_ingles())<=0){
            node.right = insert(node.right, val);
        }
        return node;
    }
}
