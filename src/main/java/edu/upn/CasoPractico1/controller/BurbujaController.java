package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/burbuja")
public class BurbujaController {

    @GetMapping
    public String mostrarFormulario() {
        return "burbuja"; // templates/burbuja.html
    }

    @PostMapping("/sumarPares")
    public String sumarPares(@RequestParam("numeros") String numeros, Model model) {
        try {
            int[] arreglo = Arrays.stream(numeros.trim().split("[,\\s]+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int suma = 0;
            for (int n : arreglo) {
                if (n % 2 == 0) suma += n;
            }

            model.addAttribute("original", Arrays.toString(arreglo));
            model.addAttribute("resultado", "Suma de números pares: " + suma);

        } catch (Exception e) {
            model.addAttribute("error", "Ingrese solo números separados por coma o espacio. Ejemplo: 2, 5, 8, 3");
        }
        return "burbuja";
    }
}
