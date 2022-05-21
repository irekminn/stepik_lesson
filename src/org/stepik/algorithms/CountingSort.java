package org.stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

class CountingSort {

  public static void main(String[] args) {
    var scanner = new Scanner(System.in/*new File("src/org/stepik/algorithms/text.txt")*/);
    var array = Stream.of(scanner.nextLine())
        .map(str -> str.split(" "))
        .flatMap(Arrays::stream)
        .map(Integer::parseInt)
        .toArray(Integer[]::new);
    scanner.close();
    Arrays.sort(array);
    System.out.println(Arrays.toString(array).replaceAll("^\\[|,|]$", ""));
  }
}
