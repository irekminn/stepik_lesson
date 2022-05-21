package org.stepik.algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class PointsAndLines {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in/*new File("src/org/stepik/algorithms/text.txt")*/);
    String fistLine = scanner.nextLine();
    String[] fistArrayLine = fistLine.split(" ");
    int countLines = Integer.parseInt(fistArrayLine[0]);

    Set<Points> resultArray = new TreeSet<>(Points::compareTo);
    for (int i = 0; i < countLines; i++) {
      Integer[] arrayStartEnd = createArrayFromScannerLine(scanner.nextLine());
      resultArray.add(new Points(arrayStartEnd[0], TypePoint.START, null));
      resultArray.add(new Points(arrayStartEnd[1], TypePoint.END, null));
    }

    Integer[] arrPoints = createArrayFromScannerLine(scanner.nextLine());
    scanner.close();

    IntStream.range(0, arrPoints.length)
        .mapToObj(i -> new Points(arrPoints[i], TypePoint.POINT, i))
        .forEach(resultArray::add);

    int count = 0;
    int countPoints = Integer.parseInt(fistArrayLine[1]);
    int[] printArrayPoints = new int[countPoints];

    for (Points point : resultArray) {
      if (point.type() == TypePoint.START) {
        count++;
      } else if (point.type() == TypePoint.END) {
        count--;
      } else {
        printArrayPoints[point.index()] = count;
        countPoints--;
        if (countPoints == 0) {
          break;
        }
      }
    }

    System.out.println(Arrays.toString(printArrayPoints).replaceAll("^\\[|,|]$", ""));
  }

  private static Integer[] createArrayFromScannerLine(String strLine) {
    return Stream.of(strLine)
        .map(str -> str.split(" "))
        .flatMap(Arrays::stream)
        .map(Integer::parseInt)
        .toArray(Integer[]::new);
  }

  record Points(int value, TypePoint type, Integer index) implements Comparable<Points> {

    @Override
    public int compareTo(Points o) {
      if (this == o) {
        return 0;
      }

      if (type() == TypePoint.POINT && o.type() == TypePoint.END) {
        return (value() <= o.value()) ? -1 : 1;
      }

      if (type() == TypePoint.END && o.type() == TypePoint.POINT) {
        return (value() <= o.value()) ? 1 : -1;
      }

      if (type() == TypePoint.POINT && o.type() == TypePoint.START) {
        return (value() >= o.value()) ? 1 : -1;
      }

      if (type() == TypePoint.START && o.type() == TypePoint.END) {
        return (value() <= o.value()) ? -1 : 1;
      }

      return (value() >= o.value()) ? 1 : -1;
    }
  }

  enum TypePoint {
    END,
    POINT,
    START

  }
}

