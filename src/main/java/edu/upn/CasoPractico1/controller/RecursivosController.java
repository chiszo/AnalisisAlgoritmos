package edu.upn.CasoPractico1.controller;

import edu.upn.CasoPractico1.dto.RecursivosSolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecursivosController {

    @GetMapping("/recursivos")
    public String mostrarFormulario(Model model) {
        model.addAttribute("req", new Object());
        return "recursivos";
    }

    @PostMapping("/recursivos")
    public String procesar(String numeros, int buscar, Model model) {
        String resultado = RecursivosSolver.resolver(numeros, buscar);
        model.addAttribute("req", new Object());
        model.addAttribute("resultado", resultado);
        return "recursivos";
    }
}