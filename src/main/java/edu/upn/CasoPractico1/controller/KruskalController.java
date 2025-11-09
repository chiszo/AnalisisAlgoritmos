package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class KruskalController {

    // ✅ Clases internas accesibles por Thymeleaf
    public static class Edge implements Comparable<Edge> {
        public int src, dest, weight;
        public Edge(int s, int d, int w) { src = s; dest = d; weight = w; }
        @Override
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    public static class Subset {
        int parent, rank;
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    @GetMapping("/kruskal")
    public String ejecutarKruskal(Model model) {
        int vertices = 5;
        List<Edge> edges = new ArrayList<>();

        // 🔹 Grafo de ejemplo
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        // 🔹 Ordenar por peso
        Collections.sort(edges);

        Subset[] subsets = new Subset[vertices];
        for (int v = 0; v < vertices; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        List<Edge> result = new ArrayList<>();
        int e = 0, i = 0;

        while (e < vertices - 1 && i < edges.size()) {
            Edge next = edges.get(i++);
            int x = find(subsets, next.src);
            int y = find(subsets, next.dest);

            if (x != y) {
                result.add(next);
                union(subsets, x, y);
                e++;
            }
        }

        model.addAttribute("aristas", result);
        return "kruskal";
    }
}
