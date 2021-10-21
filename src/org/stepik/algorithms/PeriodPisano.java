package org.stepik.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Даны целые числа n:
1≤n≤10^18
и m:
10^52≤m≤10^5
Необходимо найти остаток от деления nn-го числа Фибоначчи на mm.
 */

public class PeriodPisano {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String number = scan.nextLine();
    String[] strArray = number.trim().split(" ");
    long fibNum = Long.parseLong(strArray[0]);
    int modNum = Integer.parseInt(strArray[1]);
    List<Integer> arr = new ArrayList<Integer>();
    for (int i = 0; i < 6 * modNum; i++) {
      switch (i) {
        case (0): arr.add(0);
          break;
        case (1):
        case (2):
          arr.add(1 % modNum);
          break;
        default:
          arr.add((arr.get(i - 2) + arr.get(i - 1)) % modNum);
      }
      if (i > 2 && arr.get(i) == 1 && arr.get(i - 1) == 0) {
        break;
      }
    
    }
    System.out.println(arr.get((int) (fibNum % (arr.size() - 2))));
  }
}


