package org.stepik.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class HuffmanCodes {

  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    var str = scanner.nextLine();
    scanner.close();
    var strArray = str.toCharArray();
    var charPower = HelperUtils.searchPowerChar(strArray);
    var listNodes = HelperUtils.createListNodes(strArray, charPower);
    listNodes.sort(Node::compareTo);
    var root = HelperUtils.createTree(listNodes);
    var sb = new StringBuilder();
    var printCharCode = new StringBuilder();
    //System.out.println(Arrays.toString(strArray));
    for (int i = 0; i < strArray.length; i++) {
      var code = HelperUtils.getCodeChar(root, strArray[i], new StringBuilder());
      if ("null".equals(code.toString())) {
        code.delete(0, code.length());
        code.append("0");
      }
      sb.append(code.reverse());
      if (charPower[i] != 0) {
        printCharCode.append(strArray[i]).append(":").append(" ").append(code).append('\n');
      }
    }
    System.out.println(Arrays.stream(charPower).filter(i -> i != 0).count() + " " + sb.length());
    System.out.print(printCharCode);
    System.out.println(sb);
  }
}

class HelperUtils {

  public static int[] searchPowerChar(char[] strArray) {
    var cloneArray = strArray.clone();
    var charPower = new int[cloneArray.length];
    for (int i = 0; i < cloneArray.length; i++) {
      if (cloneArray[i] == 0) {
        continue;
      }
      charPower[i] = 1;
      for (int j = i + 1; j < cloneArray.length; j++) {
        if (cloneArray[i] == cloneArray[j]) {
          charPower[i] += 1;
          cloneArray[j] = 0;
        }
      }
    }
    return charPower;
  }

  public static ArrayList<Node<Character>> createListNodes(char[] strArray, int[] charPower) {
    return IntStream.range(0, strArray.length)
        .filter(i -> charPower[i] != 0)
        .mapToObj(i -> new Node<>(strArray[i], charPower[i]))
        .collect(Collectors.toCollection(ArrayList<Node<Character>>::new));
  }

  public static Node<Character> createTree(ArrayList<Node<Character>> listNodes) {
    if (listNodes.size() == 1) {
      return listNodes.get(0);
    }
    for (int i = 0; i < listNodes.size(); i++) {
      var leftNode = listNodes.get(i);
      leftNode.setCode("0");
      var rightNode = listNodes.get(i + 1);
      rightNode.setCode("1");
      var newNode = new Node<Character>(null, leftNode.getPower() + rightNode.getPower());
      newNode.connectNodes(leftNode, rightNode);
      listNodes.remove(leftNode);
      listNodes.remove(rightNode);
      listNodes.add(newNode);
      listNodes.sort(Node::compareTo);
      createTree(listNodes);
    }
    return listNodes.get(0);
  }

  public static StringBuilder getCodeChar(Node<Character> root, char ch, StringBuilder sb) {
    if (root.getValue() != null && root.getValue() == ch) {
      sb.append(root.code);
      return sb;
    }
    if (root.leftNode != null && sb.isEmpty()) {
      sb = getCodeChar(root.leftNode, ch, sb);
      if (root.code != null && root.getValue() == null && !sb.isEmpty()) {
        sb.append(root.code);
      }
    }

    if (root.rightNode != null && sb.isEmpty()) {
      sb = getCodeChar(root.rightNode, ch, sb);
      if (root.code != null && root.getValue() == null && !sb.isEmpty()) {
        sb.append(root.code);
      }
    }

    return sb;
  }
}

class Node<T> implements Comparable<Node<T>> {

  private final T value;
  private final int power;
  Node<T> leftNode = null;
  Node<T> rightNode = null;
  String code = null;

  Node(T value, int power) {
    this.value = value;
    this.power = power;
  }

  public int getPower() {
    return power;
  }

  public T getValue() {
    return value;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void connectNodes(Node<T> leftNode, Node<T> rightNode) {
    this.leftNode = leftNode;
    this.rightNode = rightNode;
  }

  @Override
  public int compareTo(Node<T> next) {
    return Integer.compare(getPower(), next.getPower());
  }

  @Override
  public String toString() {
    return "Node{char=" + value + ", power=" + power + ", code=" + code
        + ", leftNode=" + leftNode + ", rightNode=" + rightNode + "}";
  }
}
