package edu.upn.CasoPractico1.services;
import edu.upn.CasoPractico1.dto.AgenteViajeroResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgenteViajeroService {
    private static final int INF = Integer.MAX_VALUE;

    public AgenteViajeroResponse resolverTspVoraz(int[][] distancias) {
        int numCiudades = distancias.length;
        List<Integer> ruta = new ArrayList<>();
        boolean[] visitado = new boolean[numCiudades];

        int ciudadActual = 0; // Empezar en la ciudad 0
        ruta.add(ciudadActual);
        visitado[ciudadActual] = true;

        for (int i = 0; i < numCiudades - 1; i++) {
            int ciudadMasCercana = -1;
            int distanciaMinima = INF;

            // Decisión Voraz: Buscar la ciudad más cercana no visitada
            for (int proximaCiudad = 0; proximaCiudad < numCiudades; proximaCiudad++) {
                if (!visitado[proximaCiudad] && distancias[ciudadActual][proximaCiudad] < distanciaMinima) {
                    distanciaMinima = distancias[ciudadActual][proximaCiudad];
                    ciudadMasCercana = proximaCiudad;
                }
            }

            // Moverse a la siguiente ciudad
            if (ciudadMasCercana != -1) {
                ciudadActual = ciudadMasCercana;
                ruta.add(ciudadActual);
                visitado[ciudadActual] = true;
            }
        }

        // Regresar a la ciudad inicial
        ruta.add(0);

        // Calcular el costo total de la ruta
        int costoTotal = calcularCosto(ruta, distancias);

        return new AgenteViajeroResponse(ruta, costoTotal);
    }

    private int calcularCosto(List<Integer> ruta, int[][] distancias) {
        int costo = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            int ciudadA = ruta.get(i);
            int ciudadB = ruta.get(i + 1);
            costo += distancias[ciudadA][ciudadB];
        }
        return costo;
    }
}
