package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class DijkstraController {
    @GetMapping("/dijkstra")
    public String mostrarFormulario() {
        return "dijkstra";
    }

    @PostMapping("/dijkstra")
    public String ejecutarDijkstra(@RequestParam("origen") int origen, Model model) {
        // Grafo de ejemplo (matriz de adyacencia)
        int[][] graph = {
                {0, 2, 4, 0, 0},
                {2, 0, 1, 7, 0},
                {4, 1, 0, 3, 5},
                {0, 7, 3, 0, 2},
                {0, 0, 5, 2, 0}
        };

        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[origen] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        model.addAttribute("distancias", dist);
        model.addAttribute("origen", origen);
        return "dijkstra";
    }

    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}