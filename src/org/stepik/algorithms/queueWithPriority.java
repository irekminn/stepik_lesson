package org.stepik.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class queueWithPriority<T extends Comparable<T>> {

  private final List<T> array = new ArrayList<>();

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    var queue = new queueWithPriority<Integer>();
    while (scan.hasNextLine()) {
      var line = scan.nextLine();

      if (line.startsWith("Insert")) {
        var lineArray = line.split(" ");
        queue.insert(Integer.valueOf(lineArray[1].trim()));
      }

      if (line.startsWith("ExtractMax")) {
        System.out.println(queue.getMax());
      }
    }
    scan.close();
  }

  public T getMax() {
    T max = array.get(0);
    array.set(0, array.get(array.size() - 1));
    array.remove(array.size() - 1);
    siftDown(0);
    return max;
  }

  public void insert(T elem) {
    array.add(elem);
    siftUp(array.size() - 1);
  }

  private void siftUp(int elemIndex) {
    while (elemIndex > 0) { // elemIndex  0 — мы в корне
      var root = (elemIndex - 1) / 2;
      if (array.get(elemIndex).compareTo(array.get(root)) > 0) {
        swap(elemIndex, root);
      }
      elemIndex = root;
    }
  }

  private void siftDown(int elemIndex) {
    while (2 * elemIndex + 1 < array.size()) { // пока меньше чем количество элементов в куче
      var left = 2 * elemIndex + 1;             // left — индекс левый сын
      var right = 2 * elemIndex + 2;            // right — индекс правый сын
      var j = left;
      if (right < array.size() && array.get(right).compareTo(array.get(left)) > 0) {
        j = right;
      }
      if (array.get(elemIndex).compareTo(array.get(j)) >= 0) {
        break;
      }
      swap(elemIndex, j);
      elemIndex = j;
    }
  }

  private void swap(int elemIndex, int root) {
    T buffer = array.get(root);
    array.set(root, array.get(elemIndex));
    array.set(elemIndex, buffer);
  }
}
