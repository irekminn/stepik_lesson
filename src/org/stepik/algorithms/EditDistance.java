package org.stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class EditDistance {

  private static int[][] distancePrev;
  private static String[] lineA;
  private static String[] lineB;

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File("src/org/stepik/algorithms/text.txt"));
    lineA = scanner.nextLine().trim().split("");
    lineB = scanner.nextLine().trim().split("");
    scanner.close();

    distancePrev = new int[lineA.length + 1][lineB.length + 1];
    Arrays.stream(distancePrev).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));

    System.out.println(editDistance(lineA.length, lineB.length));
  }

  private static int editDistance(int i, int j) {
    if (distancePrev[i][j] == Integer.MAX_VALUE) {
      if (i == 0) {
        distancePrev[i][j] = j;
      } else if (j == 0) {
        distancePrev[i][j] = i;
      } else {

        int ins = editDistance(i, j - 1) + 1;
        int del = editDistance(i - 1, j) + 1;
        int sub = editDistance(i - 1, j - 1) + diff(lineA[i - 1],
            lineB[j - 1]);
        distancePrev[i][j] = min(ins, del, sub);
      }
    }

    return distancePrev[i][j];
  }

  private static int min(int ins, int del, int sub) {
    return Math.min(Math.min(ins, del), sub);
  }

  private static int diff(String a, String b) {
    return a.equals(b) ? 0 : 1;
  }

}
