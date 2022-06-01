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

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in/*new File("src/org/stepik/structure/text.txt")*/);
    String line = scanner.nextLine();
    scanner.close();
    Stack<String> stackIn = new Stack<>();
    Stack<Integer> indexStack = new Stack<>();
    Pattern pattern = java.util.regex.Pattern.compile(REGEX);
    String[] array = Stream.of(line)
        .flatMap(pattern::splitAsStream)
        .toArray(String[]::new);
    if (array.length == 1 && array[0].equals("")) {
      System.out.println("Success");
      return;
    }
    if (array.length == 1) {
      System.out.println(1);
      return;
    }
    for (int i = 0; i < array.length; i++) {

      switch (array[i]) {
        case FIGURE_START, ROUND_START, SQUARE_START -> {
          stackIn.push(array[i]);
          indexStack.push(i);
        }
        case FIGURE_END, ROUND_END, SQUARE_END -> {
          if (stackIn.size() == 0) {
            System.out.println(i + 1);
            return;
          }

          int rightBracket = stackIn.peek().charAt(0);
          rightBracket += array[i].equals(ROUND_END) ? 1 : 2;

          if (array[i].charAt(0) == rightBracket) {
            stackIn.pop();
            indexStack.pop();
          } else {
            System.out.println(i + 1);
            return;
          }

        }
      }
    }

    if (stackIn.size() == 0) {
      System.out.println("Success");
    } else {
      System.out.println(indexStack.pop() + 1);
    }
  }
}