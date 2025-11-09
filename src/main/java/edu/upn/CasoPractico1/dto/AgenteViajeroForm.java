package edu.upn.CasoPractico1.dto;

public class AgenteViajeroForm {
    private int[][] matrix;

    // Constructor: Inicializa una matriz 4x4 por defecto
    public AgenteViajeroForm() {
        this.matrix = new int[][]{
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
    }

    // Getters y Setters (necesarios para el binding de Thymeleaf)
    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
