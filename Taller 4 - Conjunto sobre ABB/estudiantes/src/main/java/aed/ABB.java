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
                    Nodo nuevo1= new Nodo(elem);
                    nuevo1.padre=raizactual;
                    raizactual.izq=nuevo1;
                    //raizactual.izq.padre=raizactual;
                    cardinal++;
                } else{
                    ponernodo(raizactual.izq,elem);
                }
            } else {
                if(raizactual.der==null){
                    Nodo nuevo2= new Nodo(elem);
                    nuevo2.padre = raizactual;
                    raizactual.der=nuevo2;
                    //raizactual.der.padre=raizactual;
                    cardinal++;
                } else{
                    ponernodo(raizactual.der,elem);
                }
            }
        }
    }

    private boolean versiestavalor2(Nodo raizactual, T elem){
        boolean flag= false;
        if (raizactual==null){
            return false;
        }else{
            if(raizactual.valor.compareTo(elem)==0){
                return true;
            }
            else if(raizactual.valor.compareTo(elem)>0){
                //si la raiz es mas grande que el elemento entonces lo pongo en la izquierda
                if(versiestavalor2(raizactual.izq,elem)){flag=true;}
            } else {
                if(versiestavalor2(raizactual.der,elem)) {flag=true;}
            }
        }
        return flag;
    }
    private boolean versiestavalor(Nodo raizactual, T elem){
        Nodo actual = raizactual;
        while(actual!=null){
            if(actual.valor.compareTo(elem)>0){
                //si la raiz es mas grande que el elemento entonces lo pongo en la izquierda
                actual= actual.izq;
            } else if(actual.valor.compareTo(elem)<0) {
                actual= actual.der;
            }
            else{
                return true;
            }
        }
        return false;
    }
    private boolean notienedescendencia(Nodo nodo){
        return nodo.izq==null && nodo.der==null;
    }
    private void eliminarnodo(Nodo raizactual, T elem){

        if(notienedescendencia(raizactual)){
            if(raizactual.padre!=null){
                if(raizactual.padre.izq==raizactual){
                    raizactual.padre.izq=null;
                } else if(raizactual.padre.der==raizactual){
                    raizactual.padre.der=null;
                }
            } else{
                //si no tiene hijos y enccima no tiene papa significa que es la raiz, la quiero eliminar entonces digo q es null
                raiz = null;
            }
        }
        else if(raizactual.izq==null && raizactual.der!=null){
            //tiene un hijo y es el derecho
            raizactual.der.padre= raizactual.padre;
            if(raizactual.padre!=null) {
                if (raizactual.padre.izq == raizactual) {
                    raizactual.padre.izq = raizactual.der;

                } else if (raizactual.padre.der == raizactual) {
                    raizactual.padre.der = raizactual.der;
                }
            }
            else{
                //raiz.der.padre=null;
                raiz=raiz.der;
            }
            //raizactual.der.padre = raizactual.padre;
        }
        else if(raizactual.der==null && raizactual.izq!=null){
            //tiene un hijo y es el izquierdo
            raizactual.izq.padre = raizactual.padre;
            if(raizactual.padre!=null){

                if(raizactual.padre.izq==raizactual){

                    raizactual.padre.izq=raizactual.izq;
                } else if(raizactual.padre.der==raizactual){
                    raizactual.padre.der=raizactual.izq;
                }
            }
            else{
                //raiz.izq.padre=null;
                raiz=raiz.izq;
            }
            //raizactual.izq.padre = raizactual.padre;
        }
        else if(raizactual.der!=null && raizactual.izq!=null){
            //caso tiene dos hijos

            //busco al menor sucesor

            if(raizactual.der.izq==null){
                //el menor sucesor es el hijo derecho
                // guardo el valor y voy haciendo el nodo que tiene q reemplazar
                Nodo reemplazo1 = new Nodo(raizactual.der.valor);
                // arreglo como quedaria el nodo que le saco el valor
                if(raizactual.der.der!=null){
                    raizactual.der.der.padre = raizactual;
                    raizactual.der= raizactual.der.der;
                    // raizactual.der.padre= raizactual; // me da duda esto
                }else{
                    raizactual.der=null;
                }

                //tengo que cambiar el valor que esta en raizactual
                reemplazo1.izq = raizactual.izq;
                if(reemplazo1.izq!=null){
                    reemplazo1.izq.padre=reemplazo1;
                }
                reemplazo1.der = raizactual.der;
                if(reemplazo1.der!=null){
                    reemplazo1.der.padre=reemplazo1;
                }
                reemplazo1.padre = raizactual.padre;
                if(raizactual.padre!=null){
                    if(raizactual.padre.izq==raizactual){
                        raizactual.padre.izq = reemplazo1;
                    }else if(raizactual.padre.der==raizactual){
                        raizactual.padre.der=reemplazo1;
                    }
                }
                else{
                    raiz=reemplazo1;
                    //si no hay papa significa que es la raiz
                    /*
                    if(raiz.izq==raizactual){
                        raiz.izq = reemplazo1;
                    }else if(raiz.der==raizactual){
                        raiz.der=reemplazo1;
                    }

                     */
                }
            }

            else{
                //busqueda del minimo sucesor
                Nodo prevsucesor = raizactual.der;
                Nodo actualsucesor= raizactual.der;
                while(actualsucesor.izq!=null){
                    prevsucesor=actualsucesor;
                    actualsucesor=actualsucesor.izq;
                }
                Nodo reemplazo2 = new Nodo(prevsucesor.izq.valor);

                // arreglo como quedaria el nodo que le saco el valor
                if(actualsucesor.der!=null){
                    actualsucesor.der.padre=actualsucesor;
                    prevsucesor.izq= actualsucesor.der;
                    //prevsucesor.izq.padre= actualsucesor; //me da duda esto x2
                }else{
                    prevsucesor.izq=null;
                }

                //tengo que cambiar el valor que esta en raizactual
                reemplazo2.izq = raizactual.izq;
                if(reemplazo2.izq!=null){
                    reemplazo2.izq.padre=reemplazo2;
                }
                reemplazo2.der = raizactual.der;
                if(reemplazo2.der!=null){
                    reemplazo2.der.padre=reemplazo2;
                }
                reemplazo2.padre = raizactual.padre;
                if(raizactual.padre!=null){
                    if(raizactual.padre.izq==raizactual){
                        raizactual.padre.izq = reemplazo2;
                    }else if(raizactual.padre.der==raizactual){
                        raizactual.padre.der=reemplazo2;
                    }
                }
                else{
                    //si no hay papa significa que es la raiz
                    raiz=reemplazo2;
                    /*
                    if(raiz.izq==raizactual){
                        raiz.izq = reemplazo2;
                    }else if(raiz.der==raizactual){
                        raiz.der=reemplazo2;
                    }

                     */
                }


            }

        }else{}

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
        Nodo prev = raiz;
        Nodo actual = raiz;
        if(!pertenece(elem)) {
            if (raiz == null) {
                raiz = new Nodo(elem);
            } else {
                while (actual != null) {
                    if (actual.valor.compareTo(elem) > 0) {
                        //si la raiz es mas grande que el elemento entonces lo pongo en la izquierda
                        prev = actual;
                        actual = actual.izq;
                        if (actual == null) {
                            prev.izq = new Nodo(elem);
                            prev.izq.padre = prev;
                            break;
                        }
                    } else {
                        prev = actual;
                        actual = actual.der;
                        if (actual == null) {
                            prev.der = new Nodo(elem);
                            prev.der.padre = prev;
                            break;
                        }
                    }
                }
                //ponernodo(raiz,elem);
            }
            cardinal++;
        }
    }

    public boolean pertenece2(T elem){
        return versiestavalor(raiz,elem);
    }
    public boolean pertenece(T Elem){
        Nodo actual = raiz;
        while(actual!=null){
            if(actual.valor.compareTo(Elem)==0){
                return true;
            }
            else if(actual.valor.compareTo(Elem)>0){
                //si la raiz es mas grande que el elemento entonces lo pongo en la izquierda
                actual= actual.izq;
            } else {
                actual= actual.der;
            }
        }
        return false;
    }
    public void eliminar(T elem) {
        if (pertenece(elem)) {
            raiz = eliminarRecursivo(raiz, elem);
            cardinal--;
        }
    }

    private Nodo eliminarRecursivo(Nodo nodo, T elem) {
        if (nodo == null) {
            return nodo;
        }

        if (elem.compareTo(nodo.valor) < 0) {
            nodo.izq = eliminarRecursivo(nodo.izq, elem);
        } else if (elem.compareTo(nodo.valor) > 0) {
            nodo.der = eliminarRecursivo(nodo.der, elem);
        } else {
            if (nodo.izq == null) {
                return nodo.der;
            } else if (nodo.der == null) {
                return nodo.izq;
            }

            nodo.valor = encontrarMinimoValor(nodo.der);
            nodo.der = eliminarRecursivo(nodo.der, nodo.valor);
        }
        return nodo;
    }

    private T encontrarMinimoValor(Nodo nodo) {
        T minValor = nodo.valor;
        while (nodo.izq != null) {
            minValor = nodo.izq.valor;
            nodo = nodo.izq;
        }
        return minValor;
    }
    public void eliminar3(T elem){
        if (versiestavalor(raiz,elem)){
            if(raiz==null){
                //no hago nada
            }
            if(raiz.valor==elem){
                eliminarnodo(raiz,elem);
                cardinal--;
            }
            else {
            /*
            if(raiz.izq.valor==elem){
                eliminarnodo(raiz.izq,elem);
            }
            if(raiz.der.valor==elem){
                eliminarnodo(raiz.der,elem);
            }

             */
                Nodo prev = raiz;
                Nodo actual = raiz;
                while (actual != null && actual.valor != elem) {
                    if (versiestavalor(actual.izq, elem)) {
                        prev = actual;
                        actual = actual.izq;
                    } else if (versiestavalor(actual.der, elem)) {
                        prev = actual;
                        actual = actual.der;
                    }
                }
                if(prev.izq==actual){
                    eliminarnodo(prev.izq,elem);
                    cardinal--;
                }else if(prev.der==actual){
                    eliminarnodo(prev.der,elem);
                    cardinal--;
                }
            }

        }
    }

    public void eliminar2(T elem){

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
                        if(notienedescendencia(actualsucesor)){
                            prevsucesor.izq = null;
                        }else {
                            actualsucesor.der.padre = actualsucesor.padre;
                            prevsucesor.izq = actualsucesor.der;

                        }
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
