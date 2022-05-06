package org.stepik.algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

class NumberOfInversions {

  private static long inverseCount;

  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    var sizeArray = scanner.nextInt();
    var arrayValue = new int[sizeArray];
    for (int i = 0; i < arrayValue.length; i++) {
      arrayValue[i] = scanner.nextInt();
    }
    scanner.close();
    sort(arrayValue);
    System.out.println(inverseCount);
  }

  public static int[] sort(int[] array) {
    inverseCount = 0;
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return array;
    }
    return sort(array, 0, array.length - 1);
  }

  static int[] sort(int[] array, int low, int high) {
    if (low > high - 1) {
      return new int[]{array[low]};
    }
    int mid = low + (high - low) / 2;

    return merge(sort(array, low, mid), sort(array, mid + 1, high));
  }

  static int[] merge(int[] array1, int[] array2) {
    int cursor1 = 0;
    int cursor2 = 0;
    int[] merged = new int[array1.length + array2.length];
    int mergedCursor = 0;

    while (cursor1 < array1.length && cursor2 < array2.length) {

      if (array1[cursor1] <= array2[cursor2]) {
        merged[mergedCursor++] = array1[cursor1++];
      } else {
        merged[mergedCursor++] = array2[cursor2++];
        inverseCount += (array1.length - cursor1);
      }
    }

    while (cursor1 < array1.length) {
      merged[mergedCursor++] = array1[cursor1++];
    }

    while (cursor2 < array2.length) {
      merged[mergedCursor++] = array2[cursor2++];
    }

    return merged;
  }
}
