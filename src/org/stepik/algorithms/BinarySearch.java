package org.stepik.algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class BinarySearch {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String lineValue = scanner.nextLine();
    Integer[] arrayValue = createArray(lineValue);
    String lineKey = scanner.nextLine();
    scanner.close();
    Integer[] arrayKey = createArray(lineKey);
    System.out.println(
        Arrays.stream(arrayKey)
            .map(key -> Arrays.binarySearch(arrayValue, key))
            .map(i -> (i < 0) ? "-1" : String.valueOf(i + 1))
            .collect(Collectors.joining(" "))
    );
  }

  private static Integer[] createArray(String line) {
    return Arrays.stream(line.split(" "))
        .skip(1)
        .map(Integer::valueOf)
        .toArray(Integer[]::new);
  }
}
