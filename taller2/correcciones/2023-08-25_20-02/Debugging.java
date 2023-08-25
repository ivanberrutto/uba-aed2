package aed;

class Debugging {
    boolean xor(boolean a, boolean b) {
        return (a || b)&&!(a && b);
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean r = true;
        if (xs.length != ys.length) {
            r = false;
        } else {
            for (int i = 0; i < xs.length; i++) {
                if (xs[i] != ys[i]) {
                    r = false;
                }
            }
        }
        return r;
    }

    boolean ordenado(int[] xs) {
        boolean r = true;
        for (int i = 0; i < xs.length - 1; i++) {
            if (xs[i] > xs [i+1]) {
                r =false;
            }
        }
        return r;
    }

    int maximo(int[] xs) {
        int r = xs[0];
        for (int i = 0; i< xs.length; i++) {
            if (xs[i] > r){
                r =xs[i];
            }
        }
        return r;
    }

    boolean todosPositivos(int[] xs) {
        boolean r = true;
        for (int x : xs) {
            if (x <= 0) {return false;}
        }
        return r;    
    }
}
