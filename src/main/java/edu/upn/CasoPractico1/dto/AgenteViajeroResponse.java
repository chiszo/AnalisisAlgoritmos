package edu.upn.CasoPractico1.dto;

import java.util.List;

public class AgenteViajeroResponse {
    private List<Integer> ruta;
    private int costoTotal;

    // Constructor
    public AgenteViajeroResponse(List<Integer> ruta, int costoTotal) {
        this.ruta = ruta;
        this.costoTotal = costoTotal;
    }

    // Getters (Esenciales para Thymeleaf)
    public List<Integer> getRuta() { return ruta; }
    public int getCostoTotal() { return costoTotal; }
}
