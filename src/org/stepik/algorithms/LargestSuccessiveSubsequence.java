package org.stepik.algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

class LargestSuccessiveSubsequence {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Integer number = Stream.of(scanner.nextLine()).map(Integer::parseInt).findAny().get();
    int[] array = Stream.of(scanner.nextLine())
        .map(str -> str.split(" "))
        .flatMap(Arrays::stream)
        .mapToInt(Integer::parseInt)
        .toArray();
    scanner.close();

    int[] lenSubsequences = new int[number];
    for (int i = 0; i < lenSubsequences.length; i++) {
      lenSubsequences[i] = 1;
      for (int j = 0; j <= i - 1; j++) {
        if ((array[i] % array[j] == 0) && lenSubsequences[j] + 1 > lenSubsequences[i]) {
          lenSubsequences[i] = lenSubsequences[j] + 1;
        }
      }
    }

    int maxSize = 0;
    for (int size : lenSubsequences) {
      if (size > maxSize) {
        maxSize = size;
      }
    }
    System.out.println(maxSize);
  }
}