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

    /**
     * Muestra la página principal del TSP.
     * Crea un TspForm nuevo (con la matriz 4x4 de ejemplo)
     * y lo pasa al modelo para que Thymeleaf lo pinte.
     */
    @GetMapping("/agenteViajero")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tspForm", new AgenteViajeroForm());
        return "agenteViajero"; // Renderiza /resources/templates/tsp-view.html
    }

    /**
     * Procesa el formulario enviado.
     * Recibe el objeto 'tspForm' llenado por Thymeleaf.
     */
    @PostMapping("/resolver")
    public String resolverTsp(@ModelAttribute AgenteViajeroForm tspForm, Model model) {

        // 1. Llamar al servicio con la matriz del formulario
        AgenteViajeroResponse resultado = tspService.resolverTspVoraz(tspForm.getMatrix());

        // 2. Añadir el formulario (con los datos que el usuario envió) de vuelta al modelo
        //    (para que los campos sigan llenos)
        model.addAttribute("tspForm", tspForm);

        // 3. Añadir el resultado (ruta y costo) al modelo
        model.addAttribute("resultado", resultado);

        // 4. Renderizar la misma vista, que ahora mostrará la sección de resultados
        return "agenteViajero";
    }
}
