package edu.upn.CasoPractico1.dto;

import java.util.ArrayList;
import java.util.List;

public class PermutacionSolver {

    public static List<String> generarPermutaciones(String cadena) {
        List<String> resultado = new ArrayList<>();
        backtrack("", cadena, resultado);
        return resultado;
    }

    private static void backtrack(String prefijo, String resto, List<String> resultado) {
        if (resto.isEmpty()) {
            resultado.add(prefijo);
        } else {
            for (int i = 0; i < resto.length(); i++) {
                char c = resto.charAt(i);
                String nuevoPrefijo = prefijo + c;
                String nuevoResto = resto.substring(0, i) + resto.substring(i + 1);
                backtrack(nuevoPrefijo, nuevoResto, resultado);
            }
        }
    }
}