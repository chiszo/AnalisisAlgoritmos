package edu.upn.CasoPractico1.services;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

@Service
public class CambioMonedaService {
    // Denominaciones fijas para este servicio
    private final int[] denominaciones = {1, 5, 10, 25, 50};

    /**
     * Calcula el cambio usando un algoritmo voraz.
     */
    public Map<Integer, Integer> darCambio(int cambio) {

        // El resultado se guardará como: {Moneda -> Cantidad}
        Map<Integer, Integer> resultado = new HashMap<>();

        // Iteramos de mayor a menor
        for (int i = denominaciones.length - 1; i >= 0; i--) {
            int moneda = denominaciones[i];

            // 1. Tomar la cantidad de monedas que "caben"
            int cantidad = cambio / moneda;

            if (cantidad > 0) {
                // 2. Decisión Voraz: Tomar todas las monedas de este valor
                resultado.put(moneda, cantidad);

                // 3. Actualizar el cambio restante
                cambio = cambio % moneda;
            }
        }

        // Nota: En una app real, aquí verificarías si 'cambio' es 0
        // y manejarías el error si no se pudo dar el cambio exacto.

        return resultado;
    }
}
