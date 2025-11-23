package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FloydController {

    @GetMapping("/floyd")
    public String mostrarFormulario() {
        return "floyd";
    }

    @PostMapping("/floyd")
    public String calcularFloyd(
            @RequestParam String matriz,
            Model model) {
        try {
            String[] filas = matriz.split(";");
            int n = filas.length;
            int[][] dist = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] valores = filas[i].split(",");
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Integer.parseInt(valores[j].trim());
                }
            }
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
            model.addAttribute("resultado", dist);
            model.addAttribute("n", n);
        } catch (Exception e) {
            model.addAttribute("error", "Entrada inválida. Usa solo números separados por coma y punto y coma.");
        }
        return "floyd";
    }
}