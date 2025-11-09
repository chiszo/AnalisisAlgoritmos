package edu.upn.CasoPractico1.controller;
import org.springframework.stereotype.Controller;
import edu.upn.CasoPractico1.services.CambioMonedaService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Map;

@Controller
public class CambioMonedaController {
    // 1. Inyección de Dependencias:
    // Spring Boot "inyecta" automáticamente una instancia de CambioService
    // en este controlador.
    private final CambioMonedaService cambioService;

    @Autowired
    public CambioMonedaController(CambioMonedaService cambioService) {
        this.cambioService = cambioService;
    }

    /**
     * Define un "endpoint" web para calcular el cambio.
     * Se accederá vía: GET /api/cambio?monto=XX
     *
     * @param monto El valor que viene en la URL (ej. ?monto=93)
     * @return Un Map que Spring Boot convertirá a JSON.
     */
    @GetMapping("/cambioMoneda")
    public String paginaDeCambio(
            @RequestParam(name = "monto", required = false) Integer monto,
            Model model) {

        if (monto != null && monto > 0) {
            // 1. Si el monto existe, llamamos al servicio
            Map<Integer, Integer> resultado = cambioService.darCambio(monto);

            // 2. Añadimos los resultados al "model"
            model.addAttribute("resultado", resultado);
            model.addAttribute("montoSolicitado", monto); // Para mostrarlo de vuelta
        } else {
            // Si no hay monto, pasamos un resultado vacío
            model.addAttribute("resultado", Collections.emptyMap());
        }

        // 3. Devolvemos el nombre del archivo HTML
        // Spring Boot y Thymeleaf buscarán: /resources/templates/cambio-view.html
        return "cambioMoneda";
    }
}
