package org.stepik.algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class BinarySearch {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String lineValue = scanner.nextLine();
    int[] arrayValue = createArray(lineValue);
    String lineKey = scanner.nextLine();
    scanner.close();
    int[] arrayKey = createArray(lineKey);
    System.out.println(getStringFromArray(arrayValue, arrayKey));
  }

  private static String getStringFromArray(int[] arrayValue, int[] arrayKey) {
    return Arrays.stream(arrayKey)
        .map(key -> Arrays.binarySearch(arrayValue, key))
        .mapToObj(i -> (i < 0) ? "-1" : String.valueOf(i + 1))
        .collect(Collectors.joining(" "));
  }

  private static int[] createArray(String line) {
    return Arrays.stream(line.split(" "))
        .skip(1)
        .mapToInt(Integer::parseInt)
        .toArray();
  }
}
