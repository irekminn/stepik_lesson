package org.stepik.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class BuildingHeap {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int size = Integer.parseInt(scanner.nextLine());
    String strArray = scanner.nextLine();
    scanner.close();
    int[] array = Arrays.stream(strArray.split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
    List<Integer[]> pairsExchangeIndex = new ArrayList<>();
    // Build heap (rearrange array)
    for (int i = size / 2 - 1; i >= 0; i--) {
      heapify(array, size, i, pairsExchangeIndex);
    }
    System.out.println(pairsExchangeIndex.size());
    for (Integer[] elem : pairsExchangeIndex) {
      System.out.println(Arrays.toString(elem).replaceAll("[\\[,\\]]", ""));
    }
  }

  private static void heapify(int[] array, int n, int i, List<Integer[]> pairsExchangeIndex) {
    int min = i; // Initialize min as root
    int l = 2 * i + 1; // left = 2*i + 1
    int r = 2 * i + 2; // right = 2*i + 2

    // If left child is min than root
    if (l < n && array[l] < array[min]) {
      min = l;
    }

    // If right child is min than largest so far
    if (r < n && array[r] < array[min]) {
      min = r;
    }

    // If largest is not root
    if (min != i) {
      pairsExchangeIndex.add(new Integer[]{i, min});
      int swap = array[i];
      array[i] = array[min];
      array[min] = swap;

      // Recursively heapify the affected subtree
      heapify(array, n, min, pairsExchangeIndex);
    }

  }
}
