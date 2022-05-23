package org.stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

class LargestNonIncreasingSubsequence {

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File("src/org/stepik/algorithms/text.txt"));
    int number = Stream.of(scanner.nextLine()).mapToInt(Integer::parseInt).findAny().getAsInt();
    int[] array = Stream.of(scanner.nextLine())
        .map(str -> str.split(" "))
        .flatMap(Arrays::stream)
        .mapToInt(Integer::parseInt)
        .toArray();
    scanner.close();
    longestIncreasingSubsequence(array, number, (int) (Math.pow(10, 9) + 1));

  }

  static void longestIncreasingSubsequence(int[] arr, int n, int lowerLimit) {

    // Add boundary case, when array n is zero
    // Depend on smart pointers

    int[] tailIndices = new int[n + 2];
    Arrays.fill(tailIndices, -1);
    tailIndices[0] = lowerLimit;

    List<int[]> prevIndices = new ArrayList<>();

    for (int i = 0; i < arr.length; i++) {

      int left = 0;
      int right = arr.length;
      while (right - left > 1) {
        int middle = (left + right) >>> 1;
        if (tailIndices[middle] < arr[i]) {
          right = middle;
        } else {
          left = middle;
        }
      }
      tailIndices[right] = arr[i];
      prevIndices.add(new int[]{right, i});
    }
    int len = (int) Arrays.stream(tailIndices).skip(1).takeWhile(i -> i != -1).count();
    int[] result = new int[len];
    int j = 0;
    for (int i = prevIndices.size() - 1; i >= 0; i--) {
      if (prevIndices.get(i)[0] == len) {
        result[j] = prevIndices.get(i)[1] + 1;
        j++;
        len--;
      }
    }
    System.out.println(result.length);
    for (int i = result.length - 1; i >= 0; i--) {
      if (i == 0) {
        System.out.println(result[i]);
      } else {
        System.out.print(result[i] + " ");
      }
    }
  }
}