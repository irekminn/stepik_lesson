package org.stepik.structure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TreeDepth {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    int[] intArr = greatArrayFromInput(scanner.nextLine());
    scanner.close();
    System.out.println(treeHeight(n, intArr));
  }

  private static int treeHeight(int n, int[] intArr) {
    int root = -1;
    int height = 1;
    int result = 0;
    boolean isIfOut = false;
    Map<Integer, Integer> tmpMapTree = new HashMap<>();

    for (int i = 0; i < n; i++) {
      int parent = intArr[i];
      if (parent == root) {
        tmpMapTree.put(i, 1);
        continue;
      }
      height += 1;
      while (true) {
        int treePath = intArr[parent];

        if (tmpMapTree.containsKey(treePath)) {
          height = height + tmpMapTree.get(treePath);
          isIfOut = true;
        }

        if (isIfOut || treePath == root) {
          tmpMapTree.put(i, height);
          result = Math.max(height, result);
          height = 1;
          isIfOut = false;
          break;
        }

        parent = treePath;
        height += 1;
      }
    }
    return result;
  }

  private static int[] greatArrayFromInput(String str) {
    return Arrays.stream(str.split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
  }

}