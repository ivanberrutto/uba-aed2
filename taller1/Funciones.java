package aed;

class Funciones {
    int cuadrado(int x) {
        return x * x;
    }

    double distancia(double x, double y) {
        return Math.sqrt((x*x)+(y*y));
    }

    boolean esPar(int n) {
        boolean resultant;
        if ((n % 2) == 0) {
            resultant = true;
        } else {
            resultant = false;
        }
        return resultant;
    }

    boolean esBisiesto(int n) {
        return (((n % 4 == 0) && (n % 100 != 0)) || (n % 400 == 0) );
    }

    int factorialIterativo(int n) {
        int resultant = 1;
        for(int i=n; i>0; i=i-1){
            resultant = resultant*i;
        }
        return resultant;
    }

    int factorialRecursivo(int n) {
        int resultant;
        if (n == 0){
            resultant = 1;
        } else {
            resultant = factorialRecursivo(n-1) * n;
        }
        return resultant;
    }

    boolean esPrimo(int n) {
        boolean resultant = true;
        for (int i = 2; i < n; i++){
            if (n % i == 0){
                resultant = false;
            }
        }
        if ((n == 0) || (n == 1)){
            resultant = false;
        }
        return resultant;
    }

    int sumatoria(int[] numeros) {
        int resultant = 0;
        for (int n : numeros){
            resultant = resultant + n;
        }
        return resultant;
    }

    int busqueda(int[] numeros, int buscado) {
        int resultant = (-1);
        for (int i = 0; i < numeros.length; i++){
            if (numeros[i] == buscado){
                resultant = i;
            }
        }
        return resultant;
    }

    boolean tienePrimo(int[] numeros) {
        boolean resultant = false;
        for (int n : numeros){
            if (esPrimo(n)){
                resultant = true;
            }
        }
        return resultant;
    }

    boolean todosPares(int[] numeros) {
        boolean resultant = true;
        for (int n: numeros){
            if (n % 2 != 0){
                resultant = false;
            }
        }
        return resultant;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean resultant = true;
        if (s1.length() > s2.length()){
            resultant = false;
        }else {
            for (int i = 0; i < s1.length(); i++){
                if (s1.charAt(i) != s2.charAt(i)){
                    resultant = false;
                }
            }
        }
        return resultant;
    }

    boolean esSufijo(String s1, String s2) {
        boolean resultant = true;
        if (s1.length() > s2.length()){
            resultant = false;
        }else {
            for (int i = 0; i < s1.length(); i++){
                if (s1.charAt((s1.length() - 1) - i) != s2.charAt((s2.length() - 1) - i)){
                    resultant = false;
                }
            }
        }
        return resultant;
    }
}
