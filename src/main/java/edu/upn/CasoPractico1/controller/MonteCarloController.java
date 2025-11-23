package edu.upn.CasoPractico1.controller;
import edu.upn.CasoPractico1.dto.MonteCarloForm;
import edu.upn.CasoPractico1.services.MonteCarloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MonteCarloController {
    @Autowired
    private MonteCarloService monteCarloService;

    @GetMapping("/montecarlo")
    public String mostrarPagina(Model model) {
        model.addAttribute("monteCarloForm", new MonteCarloForm(1000));
        return "montecarlo";
    }

    @PostMapping("/montecarlo/simular")
    public String simular(@ModelAttribute MonteCarloForm form, Model model) {
        // Validar entrada
        int n = form.getN();
        if (n < 10) n = 10;
        if (n > 10000000) n = 10000000; // Límite para no colgar el server
        form.setN(n);

        MonteCarloService.SimulationResult resultado = monteCarloService.simularArea(n);

        model.addAttribute("resultado", resultado);
        model.addAttribute("monteCarloForm", form);

        return "montecarlo";
    }
}
