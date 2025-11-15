package edu.upn.CasoPractico1.controller;
import edu.upn.CasoPractico1.dto.AgenteViajeroForm;
import edu.upn.CasoPractico1.dto.AgenteViajeroResponse;
import edu.upn.CasoPractico1.services.AgenteViajeroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgenteViajeroController {
    private final AgenteViajeroService tspService;

    public AgenteViajeroController(AgenteViajeroService tspService) {
        this.tspService = tspService;
    }

    @GetMapping("/agenteViajero")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tspForm", new AgenteViajeroForm());
        return "agenteViajero"; // Renderiza /resources/templates/tsp-view.html
    }

    @PostMapping("/resolver")
    public String resolverTsp(@ModelAttribute AgenteViajeroForm tspForm, Model model) {

        AgenteViajeroResponse resultado = tspService.resolverTspVoraz(tspForm.getMatrix());
        model.addAttribute("tspForm", tspForm);
        model.addAttribute("resultado", resultado);
        return "agenteViajero";
    }
}
