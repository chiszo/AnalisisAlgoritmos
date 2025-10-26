package edu.upn.CasoPractico1.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuicksortController {

    private List<Integer> a = new ArrayList<>();

    @GetMapping("/quicksort")
    public String Inicio(){
        return "quicksort";
    }

    @PostMapping("/agregar")
    public String MetodoQuicksort(@RequestParam("numeros") int numero,Model model){
        a.add(numero);
        model.addAttribute("numeros", a);
        return "quicksort";
    }

    @PostMapping("/quicksort")
    public String ordenacion(Model model){
        if (!a.isEmpty()) {
            int[] arreglo = a.stream().mapToInt(Integer::intValue).toArray();
            quicksort(arreglo, 0, arreglo.length - 1);

            // actualizamos la lista 'a' con los valores ordenados
            a.clear();
            for (int n : arreglo) {
                a.add(n);
            }
        }

        model.addAttribute("numeros", a);
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
