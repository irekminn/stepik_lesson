package org.stepik.algorithms;

import java.util.Scanner;

/*
наибольший общий делитель
По данным двум числам 1 ≤ a,b ≤2⋅10^9
найдите их наибольший общий делитель.*/

public class GreatestDivisor {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String number = scan.nextLine();
    String[] strArray = number.trim().split(" ");
    long numberA = Long.parseLong(strArray[0]);
    long numberB = Integer.parseInt(strArray[1]);
    long result = recursiveSearchDivisor(numberA, numberB);
    System.out.println(result);
  }
  
  private static long recursiveSearchDivisor(long a, long b) {
    if (a == 0) {
      return b;
    }
    if (b == 0) {
      return a;
    }
    long result = 0;
    if (a >= b) {
      result = recursiveSearchDivisor(a % b, b);
    }
    if (b >= a) {
      result = recursiveSearchDivisor(a, b % a);
    }
    return result;
  }
}
