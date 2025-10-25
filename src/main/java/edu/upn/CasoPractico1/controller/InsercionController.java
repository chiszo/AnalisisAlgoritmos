package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class InsercionController {
    @GetMapping("/insercion")
    public String mostrarFormulario() {
        return "insercion";
    }

    @PostMapping("/insercion")
    public String ordenarInsercion(@RequestParam("numeros") String numeros, Model model) {

        if (numeros == null || numeros.trim().isEmpty()) {
            model.addAttribute("error", "Por favor, ingrese al menos un número.");
            return "insercion";
        }

        String[] partes = numeros.split(",");

        boolean tieneVacio = Arrays.stream(partes)
                .anyMatch(s -> s.trim().isEmpty());
        if (tieneVacio) {
            model.addAttribute("error", "Hay campos vacíos entre comas. Ejemplo: 123, 3, 1, 12");
            model.addAttribute("numerosIngresados", numeros);
            return "insercion";
        }

        int[] arr;

        try {
            arr = Arrays.stream(partes)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (arr.length == 0) {
                model.addAttribute("error", "No se detectaron números válidos. Ejemplo: 123, 3, 1, 12");
                model.addAttribute("numerosIngresados", numeros);
                return "insercion";
            }

        } catch (NumberFormatException e) {
            model.addAttribute("error", "Ingrese solo números separados por comas. Ejemplo: 123, 3, 1, 12");
            model.addAttribute("numerosIngresados", numeros);
            return "insercion";
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        String resultado = Arrays.toString(arr);

        model.addAttribute("numerosIngresados", numeros);
        model.addAttribute("resultado", resultado);

        return "insercion";
    }
}