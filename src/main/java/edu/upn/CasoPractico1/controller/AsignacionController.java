package edu.upn.CasoPractico1.controller;
import edu.upn.CasoPractico1.services.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.upn.CasoPractico1.dto.AsignacionForm;

@Controller
public class AsignacionController {
    @Autowired
    private AsignacionService optimizacionService;

    @GetMapping("/asignacion")
    public String mostrarPaginaAsignacion(Model model) {
        // Pasa un formulario vacío (o con N=3 por defecto)
        model.addAttribute("asignacionForm", new AsignacionForm(3));
        return "asignacion"; // Nombre del archivo HTML de Thymeleaf
    }

    @PostMapping("/asignacion/optimizar")
    public String optimizarAsignacion(@ModelAttribute AsignacionForm asignacionForm, Model model) {

        int n = asignacionForm.getN();
        int[][] costos = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costos[i][j] = asignacionForm.getFilas().get(i).getCostos().get(j);
            }
        }

        int costoMinimo;
        String algoritmoUsado;

        long inicio = System.nanoTime();

        if ("fb".equals(asignacionForm.getAlgoritmo())) {
            if (n > 10) {
                model.addAttribute("error", "La Fuerza Bruta no se permite para N > 10 (demasiado lento).");
                model.addAttribute("asignacionForm", asignacionForm);
                return "asignacion";
            }
            costoMinimo = optimizacionService.enfoqueFuerzaBruta(costos);
            algoritmoUsado = "Fuerza Bruta O(N!)";

        } else { // Por defecto, usar DP
            costoMinimo = optimizacionService.enfoqueDPBitmask(costos);
            algoritmoUsado = "Prog. Dinámica O(N² * 2^N)";
        }

        long fin = System.nanoTime();
        double tiempoMs = (fin - inicio) / 1_000_000.0; // Convertir a milisegundos

        model.addAttribute("costoMinimo", costoMinimo);
        model.addAttribute("tiempoMs", String.format("%.4f", tiempoMs)); // Formatear el tiempo
        model.addAttribute("algoritmoUsado", algoritmoUsado);
        model.addAttribute("asignacionForm", asignacionForm);

        return "asignacion";
    }

    @PostMapping("/asignacion/generar")
    public String generarMatriz(@RequestParam("n") int n, Model model) {
        if (n > 15) n = 15;
        if (n < 2) n = 2;
        model.addAttribute("asignacionForm", new AsignacionForm(n));
        return "asignacion";
    }
}
