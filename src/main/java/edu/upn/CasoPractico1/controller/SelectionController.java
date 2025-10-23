package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;

@Controller
public class SelectionController {
    @GetMapping("/seleccion")
    public String mostrarFormulario() {
        return "seleccion";
    }

    @PostMapping("/seleccion")
    public String ordenarSeleccion(@RequestParam("numeros") String numeros, Model model) {

        String[] partes = numeros.split(",");
        int[] arr = Arrays.stream(partes)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        String resultado = Arrays.toString(arr);

        model.addAttribute("numerosIngresados", numeros);
        model.addAttribute("resultado", resultado);

        return "seleccion"; // vuelve a la misma página
    }
}
