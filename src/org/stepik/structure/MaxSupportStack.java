package org.stepik.structure;

import java.util.Scanner;
import java.util.Stack;

class MaxSupportStack {

  public static final String PUSH = "push";
  public static final String MAX = "max";
  public static final String POP = "pop";

  public static void main(final String[] args) throws Exception {
    Scanner in = new Scanner(System.in);
    int requestCount = in.nextInt();

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> maxStack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= requestCount; i++) {
      String request = in.nextLine();
      if (request.contains(PUSH)) {
        stack.push(Integer.parseInt(request.split(" ")[1]));
        if (maxStack.isEmpty() || maxStack.peek() < stack.peek()) {
          maxStack.push(stack.peek());
        } else {
          maxStack.push(maxStack.peek());
        }
      } else if (request.equals(MAX)) {
        sb.append(maxStack.peek()).append("\n");
      } else if (request.equals(POP)) {
        stack.pop();
        maxStack.pop();
      }
    }
    System.out.println(sb);
  }
}