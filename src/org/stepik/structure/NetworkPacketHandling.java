package org.stepik.structure;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

class NetworkPacketHandling {

  public static void main(final String[] args) throws Exception {
    Scanner in = new Scanner(System.in);

    int size = in.nextInt();
    int packagesCount = in.nextInt();

    int cpuTime = 0;

    Queue<Integer> queue = new LinkedBlockingQueue<>(size);

    for (int i = 0; i < packagesCount; i++) {
      int arrival = in.nextInt();
      int duration = in.nextInt();

      while (!queue.isEmpty() && queue.peek() <= arrival) {
        queue.poll();
      }

      if (cpuTime < arrival) {
        System.out.println(arrival);
        cpuTime = arrival + duration;
        queue.add(cpuTime);
      } else if (queue.size() < size) {
        System.out.println(cpuTime);
        cpuTime += duration;
        queue.add(cpuTime);
      } else {
        System.out.println(-1);
      }
    }
  }
}
