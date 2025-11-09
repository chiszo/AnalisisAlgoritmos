package edu.upn.CasoPractico1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class KruskalController {

    static class Edge implements Comparable<Edge> {
        int origen, destino, peso;

        public Edge(int origen, int destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.peso, o.peso);
        }

        @Override
        public String toString() {
            return "(" + origen + " - " + destino + ", peso: " + peso + ")";
        }
    }

    static class UnionFind {
        int[] padre, rango;

        public UnionFind(int n) {
            padre = new int[n];
            rango = new int[n];
            for (int i = 0; i < n; i++) padre[i] = i;
        }

        int find(int x) {
            if (padre[x] != x)
                padre[x] = find(padre[x]);
            return padre[x];
        }

        void union(int x, int y) {
            int raizX = find(x);
            int raizY = find(y);

            if (raizX != raizY) {
                if (rango[raizX] < rango[raizY]) {
                    padre[raizX] = raizY;
                } else if (rango[raizX] > rango[raizY]) {
                    padre[raizY] = raizX;
                } else {
                    padre[raizY] = raizX;
                    rango[raizX]++;
                }
            }
        }
    }

    @GetMapping("/kruskal")
    public String mostrarFormulario() {
        return "kruskal";
    }

    @PostMapping("/kruskal")
    public String ejecutarKruskal(
            @RequestParam("vertices") String verticesStr,
            @RequestParam("aristas") String aristasStr,
            Model model) {

        if (verticesStr == null || verticesStr.trim().isEmpty()) {
            model.addAttribute("error", "Ingrese la cantidad de vértices.");
            return "kruskal";
        }

        if (aristasStr == null || aristasStr.trim().isEmpty()) {
            model.addAttribute("error", "Ingrese las aristas.");
            model.addAttribute("verticesIngresados", verticesStr);
            return "kruskal";
        }

        int n;
        try {
            n = Integer.parseInt(verticesStr.trim());
            if (n <= 0) {
                model.addAttribute("error", "El número de vértices debe ser mayor que 0.");
                return "kruskal";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Ingrese un número válido de vértices.");
            return "kruskal";
        }

        List<Edge> edges = new ArrayList<>();
        try {
            String[] partes = aristasStr.split(",");
            for (String p : partes) {
                String[] datos = p.trim().split("-");
                if (datos.length != 3) {
                    model.addAttribute("error", "Formato incorrecto. Use: origen-destino-peso (ej: 0-1-4, 1-2-3)");
                    model.addAttribute("verticesIngresados", verticesStr);
                    model.addAttribute("aristasIngresadas", aristasStr);
                    return "kruskal";
                }
                int origen = Integer.parseInt(datos[0].trim());
                int destino = Integer.parseInt(datos[1].trim());
                int peso = Integer.parseInt(datos[2].trim());
                edges.add(new Edge(origen, destino, peso));
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar las aristas. Revise el formato.");
            model.addAttribute("verticesIngresados", verticesStr);
            model.addAttribute("aristasIngresadas", aristasStr);
            return "kruskal";
        }

        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);

        List<Edge> mst = new ArrayList<>();
        int costoTotal = 0;

        for (Edge edge : edges) {
            int raiz1 = uf.find(edge.origen);
            int raiz2 = uf.find(edge.destino);
            if (raiz1 != raiz2) {
                uf.union(raiz1, raiz2);
                mst.add(edge);
                costoTotal += edge.peso;
            }
        }

        model.addAttribute("verticesIngresados", verticesStr);
        model.addAttribute("aristasIngresadas", aristasStr);
        model.addAttribute("resultado", mst);
        model.addAttribute("costoTotal", costoTotal);

        return "kruskal";
    }
}