package edu.upn.CasoPractico1.dto;
import java.util.List;
import java.util.ArrayList;
public class FilaFormT1 {
    private List<Integer> costos;

    public FilaFormT1() {
        this.costos = new ArrayList<>();
    }

    public FilaFormT1(int n) {
        costos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            costos.add(0);
        }
    }

    public List<Integer> getCostos() {
        return costos;
    }
    public void setCostos(List<Integer> costos) {
        this.costos = costos;
    }
}
