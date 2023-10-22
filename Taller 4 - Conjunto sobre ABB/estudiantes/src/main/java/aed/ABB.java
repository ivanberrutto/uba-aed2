package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    private int cardinal;
    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;
        Nodo(T v) {
        valor = v;
        izq = null;
        der = null;
        padre = null;
        }
    }
    

    public ABB() {
        raiz=null;
        cardinal=0;
    }
    
    private void ponernodo(Nodo raizactual ,T elem){
        
        if (raizactual== null) {
            //si entre aca es porque no habia raiz inicial
            Nodo nuevo = new Nodo(elem);
            raiz= nuevo;
            //raizactual = nuevo; esto estaba mal porq no referia a la memoria bien
            cardinal++;
        } else{
            if(raizactual.valor.compareTo(elem)==0){ 
                //ya esta puesto el nodo con ese valor osea q no hago nada ahre
            }
            else if(raizactual.valor.compareTo(elem)>0){
                //si la raiz es mas grande que el elemento entonces lo pongo en la izquierda
                
                if(raizactual.izq==null){
                    raizactual.izq=new Nodo(elem);
                    cardinal++;
                } else{
                    ponernodo(raizactual.izq,elem);
                }
            } else {
                if(raizactual.der==null){
                    raizactual.der=new Nodo(elem);
                    cardinal++;
                } else{
                    ponernodo(raizactual.der,elem);
                }
            }
        }
    }

    private boolean versiestavalor(Nodo raizactual, T elem){
        boolean flag= false;
        if (raizactual==null){
            return false;
        }else{
            if(raizactual.valor.compareTo(elem)==0){ 
                return true;
            }
            else if(raizactual.valor.compareTo(elem)>0){
                //si la raiz es mas grande que el elemento entonces lo pongo en la izquierda
                if(versiestavalor(raizactual.izq,elem)){flag=true;}
            } else {
                if(versiestavalor(raizactual.der,elem)) {flag=true;}
            }
        }
        return flag;
    }

    public int cardinal() {
        return cardinal;
    }

    public T minimo(){
        if (cardinal==1) {
            return raiz.valor;

        } else {
            Nodo actual = raiz;
            while (actual.izq != null) {
                actual = actual.izq;
            }
            return actual.valor;
        }
    }

    public T maximo(){
        if (cardinal==1) {
            return raiz.valor;

        } else {
            Nodo actual = raiz;
            while (actual.der != null) {
                actual = actual.der;
            }
            return actual.valor;
        }
    }

    public void insertar(T elem){
        ponernodo(raiz,elem);
    }

    public boolean pertenece(T elem){
        return versiestavalor(raiz,elem);
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
