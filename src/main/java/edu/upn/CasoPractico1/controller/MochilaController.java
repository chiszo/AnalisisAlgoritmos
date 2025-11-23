package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class MochilaController {

    @GetMapping("/mochila")
    public String mostrarFormulario() {
        return "mochila";
    }

    @PostMapping("/mochila")
    public String calcularMochila(
            @RequestParam String W,
            @RequestParam String weights,
            @RequestParam String values,
            Model model) {

        try {
            int maxW = Integer.parseInt(W);

            int[] weightsArr = parseArray(weights);
            int[] valuesArr = parseArray(values);

            int n = valuesArr.length;
            int[][] dp = new int[n + 1][maxW + 1];

            for (int i = 1; i <= n; i++) {
                for (int w = 1; w <= maxW; w++) {
                    if (weightsArr[i - 1] <= w) {
                        dp[i][w] = Math.max(
                                valuesArr[i - 1] + dp[i - 1][w - weightsArr[i - 1]],
                                dp[i - 1][w]
                        );
                    } else {
                        dp[i][w] = dp[i - 1][w];
                    }
                }
            }

            model.addAttribute("resultado", dp[n][maxW]);

        } catch (Exception e) {
            model.addAttribute("error", "Entrada inválida — solo números separados por comas.");
        }

        return "mochila";
    }

    private int[] parseArray(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
