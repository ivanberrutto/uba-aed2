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
                    raizactual.izq.padre=raizactual;
                    cardinal++;
                } else{
                    ponernodo(raizactual.izq,elem);
                }
            } else {
                if(raizactual.der==null){
                    raizactual.der=new Nodo(elem);
                    raizactual.der.padre=raizactual;
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
    private boolean notienedescendencia(Nodo nodo){
        return nodo.izq==null && nodo.der==null;
    }
    private void eliminarnodo(Nodo raizactual, T elem){


            if(raizactual.valor.compareTo(elem)>0){
                //si la raiz es mas grande que el elemento entonces lo pongo en la izquierda

                if(notienedescendencia(raizactual)){
                    raizactual = null;
                }
                else if(raizactual.izq==null){

                }
            }
            else if(raizactual.valor.compareTo(elem)<0){


            } else {
            }

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

        if(!versiestavalor(raiz,elem)) {
            //no hago nada
        } else {
            Nodo prev = raiz;
            Nodo actual = raiz;
            if (actual == null) {
            } else {
                while (actual !=null && actual.valor != elem) {
                    if (versiestavalor(actual.izq, elem)) {
                        prev = actual;
                        actual = actual.izq;
                    } else {
                        prev = actual;
                        actual = actual.der;
                    }
                }if(actual==null){}else {
                    if (actual.izq == null && actual.der == null) {
                        if(prev.izq==actual){
                            prev.izq = null;}
                        else if(prev.der==actual){
                            prev.der = null;
                        }
                    } else if (actual.izq == null) {
                        //if(actual.padre!=null)actual.der.padre=actual.padre;
                        if(prev.izq==actual){
                            prev.izq = actual.der;}
                        else if(prev.der==actual){
                            prev.der = actual.der;
                        }
                    } else if (actual.der == null) {
                        //if(actual.padre!=null)actual.izq.padre=actual.padre;
                        if(prev.izq==actual){
                            prev.izq = actual.izq;}
                        else if(prev.der==actual){
                            prev.der = actual.izq;
                        }
                    } else {
                        //busco el inmediato sucesor
                        Nodo actualsucesor = actual.der;
                        Nodo prevsucesor = actual.der;
                        if (actualsucesor.izq==null){
                            if(prev.izq==actual){
                                prev.izq.valor = actualsucesor.valor;}
                            else if(prev.der==actual){
                                prev.der.valor = actualsucesor.valor;
                            }
                            actual.der=actualsucesor.der;}
                        while (actualsucesor.izq != null) {
                            prevsucesor = actualsucesor;
                            actualsucesor = actualsucesor.izq;
                        }

                        actual.valor = actualsucesor.valor;
                        prevsucesor.izq = actualsucesor.der;
                    }
                    cardinal--;
                    //eliminarnodo(raiz,elem);
                }

            }
        }


    }
    private boolean pertenece(T elem,ArrayList<T> sec){
        return sec.contains(elem);
    }

    public String toString(){
        ArrayList<T> pasados = new ArrayList<>();
        String mistring = "{" + minimo();
        pasados.add(minimo());
        Nodo actualmenor = raiz;
        while(pasados.size()<cardinal){
            while (actualmenor.izq!=null &&
                    !pertenece(actualmenor.izq.valor,pasados)){
                actualmenor = actualmenor.izq;
            }
            if(!pertenece(actualmenor.valor,pasados)){
                mistring = mistring + "," + actualmenor.valor;
                pasados.add(actualmenor.valor);
            }
            if(actualmenor.der!=null && !pertenece(actualmenor.der.valor,pasados)){
                actualmenor= actualmenor.der;
            }else{
                if(actualmenor.padre!=null){actualmenor=actualmenor.padre;}
                else{actualmenor=actualmenor.der;}
            }
        }
        mistring=mistring+"}";
        return mistring;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        int dedito;
        ABB_Iterador(){dedito=0;}

        public boolean haySiguiente() {
            return _actual.valor != maximo();
        }

        public T siguiente() {
            ArrayList<T> pasados = new ArrayList<>();
            String mistring = "{" + minimo();
            pasados.add(minimo());
            Nodo actualmenor = raiz;
            while(pasados.size()<=dedito){
                while (actualmenor.izq!=null &&
                        !pertenece(actualmenor.izq.valor,pasados)){
                    actualmenor = actualmenor.izq;
                }
                if(!pertenece(actualmenor.valor,pasados)){
                    mistring = mistring + "," + actualmenor.valor;
                    pasados.add(actualmenor.valor);
                }
                if(actualmenor.der!=null && !pertenece(actualmenor.der.valor,pasados)){
                    actualmenor= actualmenor.der;
                }else{
                    if(actualmenor.padre!=null){actualmenor=actualmenor.padre;}
                    else{actualmenor=actualmenor.der;}
                }
            }
            dedito= dedito+1;
            return pasados.get(pasados.size()-1);
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
