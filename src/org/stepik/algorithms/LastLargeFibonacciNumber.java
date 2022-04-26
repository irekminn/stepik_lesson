package org.stepik.algorithms;
/*
Дано число 1≤n≤10^7, необходимо найти последнюю цифру n-го числа Фибоначчи.
Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их
нужно быть аккуратным с переполнением.В данной задаче, впрочем,
этой проблемы можно избежать, поскольку нас интересует только
последняя цифра числа Фибоначчи.
Sample Input:
841645
Sample Output:
5*/

public class LastLargeFibonacciNumber {
  public static void main(String[] args) {
    java.util.Scanner scan = new java.util.Scanner(System.in);
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
      arr[i] = ((arr[i - 2] % 10) + (arr[i - 1] % 10)) % 10;
    }
    fibNum = arr[arr.length - 1];
    System.out.println(fibNum);
  }
}
