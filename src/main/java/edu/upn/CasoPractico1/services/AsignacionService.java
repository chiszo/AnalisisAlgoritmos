package edu.upn.CasoPractico1.services;
import org.springframework.stereotype.Service;

@Service
public class AsignacionService {

    private int costoMinimoGlobalFB;

    public int enfoqueFuerzaBruta(int[][] costos) {
        int N = costos.length;
        costoMinimoGlobalFB = Integer.MAX_VALUE;
        boolean[] tareaAsignada = new boolean[N];
        resolverFuerzaBruta(costos, 0, tareaAsignada, 0);
        return costoMinimoGlobalFB;
    }

    private void resolverFuerzaBruta(int[][] costos, int empleado, boolean[] tareaAsignada, int costoActual) {
        int N = costos.length;
        if (costoActual >= costoMinimoGlobalFB) {
            return;
        }
        if (empleado == N) {
            costoMinimoGlobalFB = costoActual;
            return;
        }
        for (int tarea = 0; tarea < N; tarea++) {
            if (!tareaAsignada[tarea]) {
                tareaAsignada[tarea] = true;
                resolverFuerzaBruta(costos, empleado + 1, tareaAsignada, costoActual + costos[empleado][tarea]);
                tareaAsignada[tarea] = false; // Backtrack
            }
        }
    }

    public int enfoqueDPBitmask(int[][] costos) {
        int N = costos.length;
        if (N == 0) return 0;

        Integer[][] memo = new Integer[N][1 << N];
        int mascaraInicial = (1 << N) - 1;

        return resolverDP(costos, 0, mascaraInicial, memo);
    }

    private int resolverDP(int[][] costos, int empleado, int mask, Integer[][] memo) {
        int N = costos.length;

        if (empleado == N) {
            return 0;
        }
        if (memo[empleado][mask] != null) {
            return memo[empleado][mask];
        }
        int costoMinimoParaEsteEstado = Integer.MAX_VALUE;

        for (int tarea = 0; tarea < N; tarea++) {
            if ((mask & (1 << tarea)) != 0) {
                int costo = costos[empleado][tarea] +
                        resolverDP(costos,
                                empleado + 1,
                                mask ^ (1 << tarea),
                                memo);

                costoMinimoParaEsteEstado = Math.min(costoMinimoParaEsteEstado, costo);
            }
        }
        memo[empleado][mask] = costoMinimoParaEsteEstado;
        return costoMinimoParaEsteEstado;
    }
}
