package org.stepik.structure;

import java.util.Arrays;
import java.util.Scanner;

class MaximumInSlidingWindow {

  private static int[] leftBlock;
  private static int[] rightBlock;

  public static void main(final String[] args) throws Exception {
    Scanner in = new Scanner(System.in);

    int n = Integer.parseInt(in.nextLine());
    int[] array = Arrays.stream(in.nextLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
    int m = Integer.parseInt(in.nextLine());
    in.close();
    build(array, m);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      if (i + m == array.length) {
        sb.append(getMax(i, m));
        break;
      } else {
        sb.append(getMax(i, m)).append(" ");
      }
    }
    System.out.println(sb);
  }

  // взято отсюда: https://habr.com/ru/post/116236/
  private static void build(int[] array, int m) {
    int n = array.length;
    leftBlock = new int[n];
    rightBlock = new int[n];
    //m--;

    // Расчитываем leftBlock
    for (int i = 0; i < n; i++) {
      if (i % m != 0) {
        leftBlock[i] = Math.max(array[i], leftBlock[i - 1]);
      } else {
        leftBlock[i] = array[i];
      }
    }

    // Расчитываем rightBlock
    rightBlock[rightBlock.length - 1] = array[array.length - 1];
    for (int i = n - 2; i >= 0; i--) {
      if ((i + 1) % m != 0) {
        rightBlock[i] = Math.max(array[i], rightBlock[i + 1]);
      } else {
        rightBlock[i] = array[i];
      }
    }

  }

  // взято отсюда: https://habr.com/ru/post/116236/
  private static int getMax(int i, int m) {
    return Math.max(rightBlock[i], leftBlock[i + m - 1]);
  }


}