package org.stepik.algorithms;

import java.util.HashMap;
import java.util.Scanner;

class HuffmanDecode {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    StringBuilder lines = new StringBuilder();
    while (scan.hasNextLine()) {
      lines.append(scan.nextLine()).append("-");
    }
    scan.close();
    String[] input = lines.toString().split("-");
    String endString = input[input.length - 1];
    HashMap<String, String> dict = new HashMap<>();
    for (int i = 1; i < input.length - 1; i++) {
      dict.put(input[i].split(": ")[1], input[i].split(": ")[0]);
    }
    StringBuilder accumulate = new StringBuilder();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < endString.length(); i++) {
      accumulate.append(endString.charAt(i));
      if (dict.get(accumulate.toString()) != null) {
        result.append(dict.get(accumulate.toString()));
        accumulate = new StringBuilder();
      }
    }
    System.out.println(result);
  }
}
