package edu.upn.CasoPractico1.controller;
import edu.upn.CasoPractico1.dto.PermutacionSolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PermutacionController {

    @GetMapping("/permutaciones")
    public String mostrarFormulario() {
        return "permutaciones"; // nombre del HTML en templates/
    }

    @PostMapping("/permutaciones")
    public String generarPermutaciones(@RequestParam String cadena, Model model) {
        List<String> resultado = PermutacionSolver.generarPermutaciones(cadena);
        model.addAttribute("cadena", cadena);
        model.addAttribute("resultado", resultado);
        return "permutaciones";
    }
}