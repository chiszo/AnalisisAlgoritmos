package edu.upn.CasoPractico1.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuicksortController {


    @GetMapping("/quicksort")
    public String Inicio(){
        return "quicksort";
    }

    void quicksort(int [] arreglo, int izquierda, int derecha) {
        int i,j, central;
        float pivote;
        central = (izquierda+derecha) / 2;
        pivote = arreglo [central];
        i=izquierda;
        j=derecha;

        while (i<= j){
            while (arreglo[i]<pivote){
                i++;
            }
            while (arreglo[j]>pivote){
                j--;
            }
            if (i <= j) {
                int aux = arreglo[i];
                arreglo[i]=arreglo[j];
                arreglo[j]=aux;
                i++;
                j--;
            }
        }
        if (izquierda<j) {
            quicksort(arreglo,izquierda,j);
        }
        if (i < derecha) {
            quicksort(arreglo,i,derecha);
        }
    }

}
