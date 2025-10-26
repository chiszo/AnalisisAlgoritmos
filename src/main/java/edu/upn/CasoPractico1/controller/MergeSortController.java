package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class MergeSortController {

    @GetMapping("/mergesort")
    public String mostrarFormulario() {
        return "mergesort";
    }

    @PostMapping("/mergesort")
    public String procesarTexto(@RequestParam("frase") String frase, Model model) {
        if (frase == null || frase.trim().isEmpty()) {
            model.addAttribute("error", "Por favor, escribe una frase con varias palabras.");
            return "mergesort";
        }

        // Dividir frase en palabras
        String[] palabras = frase.trim().split("\\s+");

        // Aplicar merge sort para ordenar por longitud
        mergeSortPorLongitud(palabras, 0, palabras.length - 1);

        model.addAttribute("fraseIngresada", frase);
        model.addAttribute("resultado", Arrays.toString(palabras));

        return "mergesort";
    }

    // --- Algoritmo MergeSort adaptado a palabras por longitud ---
    private void mergeSortPorLongitud(String[] arr, int izq, int der) {
        if (izq < der) {
            int medio = (izq + der) / 2;
            mergeSortPorLongitud(arr, izq, medio);
            mergeSortPorLongitud(arr, medio + 1, der);
            merge(arr, izq, medio, der);
        }
    }

    private void merge(String[] arr, int izq, int medio, int der) {
        String[] temp = new String[der - izq + 1];
        int i = izq, j = medio + 1, k = 0;

        while (i <= medio && j <= der) {
            if (arr[i].length() <= arr[j].length()) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= medio) temp[k++] = arr[i++];
        while (j <= der) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, izq, temp.length);
    }
}
