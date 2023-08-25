package aed;

import java.util.Scanner;
import java.io.PrintStream;

class Archivos {
    float[] leerVector(Scanner entrada, int l) {
        float[] v = new float[l];
        for (int i = 0; i <l; i++) {
            v[i] =entrada.nextFloat();
        }
        return v;
    }

    float[][] leerMatriz(Scanner entrada, int f, int c) {
        float[][] m = new float[f][c];
        for (int i = 0; i < f; i++) {
            m[i] =leerVector(entrada, c);
        }
        return m;
    }

    void imprimirPiramide(PrintStream salida, int alto) {
        if (alto == 0)return;
        int espacio = alto - 1;
        int asterisco = 1;
        for (int i = 0; i < alto; i++) {
            salida.print(stringDe(" ", espacio));
            salida.print(stringDe("*", asterisco));
            salida.print(stringDe(" ", espacio));
            salida.println();
            asterisco=asterisco + 2;
            espacio --;
        }
        return;
    }


    String stringDe(String caracter, int cantidad) {
        String t = "";
        for (int i = 0; i < cantidad; i++) {t=t+ caracter; }
        return t;
    }
}