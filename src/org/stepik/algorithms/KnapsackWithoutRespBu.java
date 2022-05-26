package org.stepik.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class KnapsackWithoutRespBu {

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int knapsackWeight = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int[] elements = new int[n];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        elements[i] = Integer.parseInt(st.nextToken());
      }
      System.out.println(knapsackWithoutRespBu(knapsackWeight, elements));
    } catch (IOException e) {
      System.out.println("-------- Oops!!! --------");
    }
  }

  private static int knapsackWithoutRespBu(int knapsackWeight, int[] elements) {
    int[][] resultArray = new int[knapsackWeight + 1][elements.length + 1];
    for (int i = 0; i < elements.length; i++) {
      for (int w = 0; w < knapsackWeight; w++) {
        if (elements[i] > w + 1) {
          resultArray[w + 1][i + 1] = resultArray[w + 1][i];
        } else {
          resultArray[w + 1][i + 1] = Math.max(resultArray[w + 1][i],
              resultArray[w + 1 - elements[i]][i] + elements[i]);
        }
      }
    }
    print(resultArray);
    return resultArray[knapsackWeight][elements.length];
  }

  private static void print(int[][] resultArray) {
    for (int j = 0; j < resultArray[0].length; j++) {
      for (int[] ints : resultArray) {
        System.out.printf("%d \t", ints[j]);
      }
      System.out.println();
    }
  }
}