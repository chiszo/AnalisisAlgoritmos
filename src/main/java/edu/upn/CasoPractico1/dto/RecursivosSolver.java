package edu.upn.CasoPractico1.dto;

import java.util.Arrays;

public class RecursivosSolver {

    public static int[] quickSort(int[] arr) {
        quickSortRec(arr, 0, arr.length - 1);
        return arr;
    }

    private static void quickSortRec(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortRec(arr, low, pi - 1);
            quickSortRec(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static int busquedaBinaria(int[] arr, int valor) {
        return busquedaBinariaRec(arr, 0, arr.length - 1, valor);
    }

    private static int busquedaBinariaRec(int[] arr, int izquierda, int derecha, int valor) {
        if (derecha >= izquierda) {
            int medio = izquierda + (derecha - izquierda) / 2;
            if (arr[medio] == valor)
                return medio;
            if (arr[medio] > valor)
                return busquedaBinariaRec(arr, izquierda, medio - 1, valor);
            return busquedaBinariaRec(arr, medio + 1, derecha, valor);
        }
        return -1;
    }

    public static String resolver(String numeros, int buscar) {
        String[] partes = numeros.split(",");
        int[] arr = new int[partes.length];
        for (int i = 0; i < partes.length; i++) arr[i] = Integer.parseInt(partes[i].trim());
        quickSort(arr);
        int pos = busquedaBinaria(arr, buscar);
        String resultado = Arrays.toString(arr) + " | ";
        resultado += (pos != -1) ? "Elemento encontrado en la posición " + pos : "Elemento no encontrado";
        return resultado;
    }
}