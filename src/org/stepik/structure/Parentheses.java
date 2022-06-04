package org.stepik.structure;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Parentheses {

  private static final String REGEX = "";
  public static final String FIGURE_START = "{";
  public static final String ROUND_START = "(";
  public static final String SQUARE_START = "[";
  public static final String FIGURE_END = "}";
  public static final String ROUND_END = ")";
  public static final String SQUARE_END = "]";
  public static final String SUCCESS = "Success";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in/*new File("src/org/stepik/structure/text.txt")*/);
    String line = scanner.nextLine();
    scanner.close();

    if (line.isEmpty()) {
      System.out.println(SUCCESS);
      return;
    }
    if (line.length() == 1) {
      System.out.println(1);
      return;
    }

    Pattern pattern = Pattern.compile(REGEX);
    String[] arrayLine = greatArrayForLine(line, pattern);

    Stack<String> leftBracket = new Stack<>();
    Stack<Integer> indexStack = new Stack<>();
    for (int i = 0; i < arrayLine.length; i++) {

      switch (arrayLine[i]) {
        case FIGURE_START, ROUND_START, SQUARE_START -> {
          leftBracket.push(arrayLine[i]);
          indexStack.push(i);
        }

        case FIGURE_END, ROUND_END, SQUARE_END -> {
          if (leftBracket.size() == 0) {
            System.out.println(i + 1);
            return;
          }

          int rightBracket = leftBracket.peek().charAt(0);
          rightBracket += arrayLine[i].equals(ROUND_END) ? 1 : 2;

          if (arrayLine[i].charAt(0) == rightBracket) {
            leftBracket.pop();
            indexStack.pop();
          } else {
            System.out.println(i + 1);
            return;
          }
        }

      } // end switch
    } // enf for

    if (leftBracket.size() == 0) {
      System.out.println(SUCCESS);
      return;
    }
    System.out.println(indexStack.pop() + 1);
  }

  private static String[] greatArrayForLine(String line, Pattern pattern) {
    return Stream.of(line)
        .flatMap(pattern::splitAsStream)
        .toArray(String[]::new);
  }
}