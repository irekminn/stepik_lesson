package org.stepik.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Stairs {

  private static int[] result;

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int countSteps = Integer.parseInt(br.readLine());
      int[] weightSteps = new int[countSteps + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= countSteps; i++) {
        weightSteps[i] = Integer.parseInt(st.nextToken());
      }
      if (countSteps == 1) {
        System.out.println(weightSteps[1]);
        return;
      }
      result = new int[countSteps + 2];
      //result[0] = Integer.MIN_VALUE;
      getAllSumSteps(weightSteps);
      System.out.println(result[result.length - 2]);
    } catch (IOException e) {
      System.out.println("-------- Oops!!! --------");
    }
  }

  private static void getAllSumSteps(int[] weightSteps) {
    result[1] = weightSteps[1];
    for (int i = 2; i < result.length - 1; i++) {
      result[i] = Math.max(result[i - 1], result[i - 2]) + weightSteps[i];
    }
  }
}
