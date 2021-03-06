package org.stepik.structure;

import java.util.Scanner;

class ParallelProcessing {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Heap heap = new Heap(scanner.nextInt());
    int processesCount = scanner.nextInt();
    for (int i = 0; i < processesCount; i++) {
      Processor firstFree = heap.getFirstFree();
      System.out.printf("%s %s%n", firstFree.number, firstFree.time);
      long newTime = scanner.nextLong();
      if (newTime != 0) {
        heap.changeTime(newTime);
      }
    }
  }

  private static class Processor {

    private final int number;
    private long time;

    private Processor(int number, long time) {
      this.number = number;
      this.time = time;
    }
  }

  private static class Heap {

    private final Processor[] processors;
    private final int size;

    private Heap(int size) {
      this.size = size;
      processors = new Processor[size];
      for (int i = 0; i < size; i++) {
        processors[i] = new Processor(i, 0);
      }
    }

    private void siftDown(int index) {
      int smallestIndex;
      Processor top = processors[index];
      while (index < size / 2) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = leftChildIndex + 1;

        if (rightChildIndex >= size) {
          smallestIndex =
              processors[leftChildIndex].time > processors[index].time ? index : leftChildIndex;
        } else if (processors[leftChildIndex].time > processors[rightChildIndex].time) {
          smallestIndex = rightChildIndex;
        } else if (processors[leftChildIndex].time == processors[rightChildIndex].time) {
          smallestIndex = processors[leftChildIndex].number > processors[rightChildIndex].number
              ? rightChildIndex : leftChildIndex;
        } else {
          smallestIndex = leftChildIndex;
        }

        if (top.time < processors[smallestIndex].time
            || (top.time == processors[smallestIndex].time
            && top.number <= processors[smallestIndex].number)) {
          break;
        }

        processors[index] = processors[smallestIndex];
        index = smallestIndex;
        processors[index] = top;
      }
    }

    private Processor getFirstFree() {
      return processors[0];
    }

    private void changeTime(long newTime) {
      processors[0].time += newTime;
      siftDown(0);
    }
  }
}