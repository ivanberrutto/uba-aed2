package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    private Nodo primero;
    private int longitud=0;
    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v){ valor = v;}
        // Completar
    }

    public ListaEnlazada() {
        primero = null;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        nuevo.sig = primero;
        if(nuevo.sig !=null){
            nuevo.sig.ant = nuevo;
        }
        primero = nuevo;
        longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null) {
            primero = nuevo;

        } else {
            Nodo actual = primero;
            while (actual.sig != null) {
                actual = actual.sig;
            }
            nuevo.ant = actual;
            actual.sig = nuevo;
        }
        longitud++;
    }

    public T obtener(int i) {
        Nodo minodo = primero;
        for(int j=0 ; j < i ; j++){
            minodo = minodo.sig;
        }
        return minodo.valor;
    }

    public void eliminar(int i) {
        Nodo actual = primero;
        Nodo prev = primero;
        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.sig;
        }
        if (i == 0) {
            primero = actual.sig;
            //primero.ant = null;
        } else {
            prev.ant = actual.ant;
            prev.sig = actual.sig;
        }
        longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo minodo = primero;
        for(int j=0 ; j < indice ; j++){
            minodo = minodo.sig;
        }
        minodo.valor = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> lista_copy = new ListaEnlazada<T>();
        lista_copy.agregarAdelante(primero.valor);
        Nodo actual = primero;
        while (actual.sig != null) {
            actual = actual.sig;
            lista_copy.agregarAtras(actual.valor);
        }
        return lista_copy;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        //longitud = lista.longitud();
        if(lista.longitud()==0){
            primero = null;
        }
        else {
            for(int i=0;i < lista.longitud() ; i++){
                agregarAtras(lista.obtener(i));
            }
        }

    }
    
    @Override
    public String toString() {
        String mistring = "["+primero.valor;
        Nodo actual = primero;
        while (actual.sig != null) {
            actual = actual.sig;
            mistring = mistring + ", " + actual.valor;
        }
        mistring = mistring + "]";
        return mistring;
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        int dedito;
        ListaIterador(){
            dedito = 0;
        }



        public boolean haySiguiente() {
	        return dedito < longitud;
        }
        
        public boolean hayAnterior() {
	        return dedito > 0;
        }

        public T siguiente() {
            int i = dedito;
            dedito = dedito + 1;
            return obtener(i);
        }
        

        public T anterior() {
            dedito = dedito -1;
            return obtener(dedito);
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
