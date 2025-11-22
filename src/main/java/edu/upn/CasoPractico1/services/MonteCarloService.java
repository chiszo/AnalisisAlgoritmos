package edu.upn.CasoPractico1.services;

import org.springframework.stereotype.Service;

@Service
public class MonteCarloService {
    public SimulationResult simularArea(int n) {
        int puntosDentro = 0;

        for (int i = 0; i < n; i++) {
            // Generar punto aleatorio (x, y) entre 0.0 y 1.0
            double x = Math.random();
            double y = Math.random();

            // Determinar si está dentro de la figura (Círculo centrado en 0.5, 0.5)
            // Ecuación: (x - 0.5)^2 + (y - 0.5)^2 <= 0.5^2
            double distanciaAlCentro = Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2);

            if (distanciaAlCentro <= (0.5 * 0.5)) {
                puntosDentro++;
            }
        }

        // Calcular área estimada
        // Área Cuadrado = 1 * 1 = 1.
        double areaEstimada = (double) puntosDentro / n;

        return new SimulationResult(n, puntosDentro, areaEstimada);
    }

    public static class SimulationResult {
        public int totalPuntos;
        public int puntosDentro;
        public double areaEstimada;
        public double error;

        public SimulationResult(int total, int dentro, double area) {
            this.totalPuntos = total;
            this.puntosDentro = dentro;
            this.areaEstimada = area;
            double areaReal = Math.PI * 0.5 * 0.5;
            this.error = Math.abs(areaReal - area);
        }
    }
}
