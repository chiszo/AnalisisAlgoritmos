package edu.upn.CasoPractico1.dto;
import java.util.List;
import java.util.ArrayList;

public class AsignacionForm {
    private int n;
    private List<FilaFormT1> filas;
    private String algoritmo;// Guardará "fb" (Fuerza Bruta) o "dp" (Programación Dinámica)

    public AsignacionForm() {
        this.filas = new ArrayList<>();
        this.algoritmo = "dp";
    }

    public AsignacionForm(int n) {
        this.n = n;
        this.filas = new ArrayList<>();
        this.algoritmo = "dp"; // DP será la opción por defecto
        for (int i = 0; i < n; i++) {
            filas.add(new FilaFormT1(n));
        }
    }
    public String getAlgoritmo() {
        return algoritmo;
    }
    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    public List<FilaFormT1> getFilas() {
        return filas;
    }
    public void setFilas(List<FilaFormT1> filas) {
        this.filas = filas;
    }
}
