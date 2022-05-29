package org.stepik.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

class Calculator {

  public static final int MULTIPLIER_TWO = 2;
  public static final int MULTIPLIER_THREE = 3;
  public static final int TERM_ONE = 1;

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int number = Integer.parseInt(br.readLine());
      int[] numberOperations = new int[number + 1];
      numberOperations[0] = -1;
      int[] prevNumber = new int[number + 1];
      for (int i = 0; i <= number; i++) {
        int sumOne = (i + TERM_ONE <= number) ? i + TERM_ONE : 0;
        int multiplyTwo = isNumberToRange(i, MULTIPLIER_TWO, number) ? i * MULTIPLIER_TWO : 0;
        int multiplyThree = isNumberToRange(i, MULTIPLIER_THREE, number) ? i * MULTIPLIER_THREE : 0;

        if (sumOne == 0 && multiplyTwo == 0 && multiplyThree == 0) {
          prevNumber[i] = i;
          continue;
        }

        checkOperation(numberOperations, prevNumber, i, sumOne);
        checkOperation(numberOperations, prevNumber, i, multiplyTwo);
        checkOperation(numberOperations, prevNumber, i, multiplyThree);

      }

      System.out.println(numberOperations[number]);
      Set<Integer> sequenceNumbers = getSequenceNumbers(prevNumber);
      System.out.println(sequenceNumbers.toString().replaceAll("^\\[|,|]$", ""));

    } catch (IOException e) {
      System.out.println("-------- Oops!!! --------");
    }
  }

  private static Set<Integer> getSequenceNumbers(int[] prevNumber) {
    Set<Integer> sequence = new TreeSet<>();
    sequence.add(prevNumber[prevNumber.length - 1]);
    for (int i = prevNumber.length - 2; i > 0; i--) {
      sequence.add(prevNumber[i]);
      i = prevNumber[i];
    }
    return sequence;
  }

  private static boolean isNumberToRange(int number, int multiplier, int range) {
    return 1 <= number * multiplier && number * multiplier <= range;
  }

  private static void checkOperation(int[] numOperations, int[] prevNumber, int index, int result) {
    if (result == 0) {
      return;
    }

    if (numOperations[result] == 0) {
      numOperations[result] = numOperations[index] == 0 ? 1 : numOperations[index] + 1;
      prevNumber[result - 1] = index;
      return;
    }

    if (numOperations[result] > numOperations[index] + 1) {
      numOperations[result] = numOperations[index] + 1;
      prevNumber[result - 1] = index;
    }
  }
}
