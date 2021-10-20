package org.stepik.algorithms;

import java.util.*;

public class Fibonacci {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String number = scan.nextLine();
    int fibNum;
    int[] arr = new int[Integer.parseInt(number) + 1];
    for (int i = 0; i < arr.length; i++) {
      if (i == 0) {
        arr[i] = 0;
        continue;
      }
      if (i == 1 || i == 2) {
        arr[i] = 1;
        continue;
      }
      arr[i] = arr[i - 2] + arr[i - 1];
    }
    fibNum = arr[arr.length - 1];
    System.out.println(fibNum);
  }
}

