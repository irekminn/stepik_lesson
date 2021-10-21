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
    var scan = new Scanner(System.in);
    var number = scan.nextLine();
    var strArray = number.trim().split(" ");
    var fibNum = Long.parseLong(strArray[0]);
    var modNum = Integer.parseInt(strArray[1]);
    List<Integer> arr = new ArrayList<Integer>();
    for (int i = 0; i < fibNum; i++) {
      switch (i) {
        case 0 -> arr.add(0);
        case 1, 2 -> arr.add(1 % modNum);
        default -> arr.add((arr.get(i - 2) + arr.get(i - 1)) % modNum);
      }
      if (i > 2 && arr.get(i) == 1 && arr.get(i - 1) == 0 || arr.size() > 6 * modNum) {
        break;
      }
      
    }
    System.out.println(arr.get((int) (fibNum % (arr.size() - 2))));
  }
}

