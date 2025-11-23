package edu.upn.CasoPractico1.dto;

public class MonteCarloForm {
    private int n; // Número de puntos a lanzar (intentos)

    public MonteCarloForm() {
        this.n = 1000; // Valor por defecto
    }

    public MonteCarloForm(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
