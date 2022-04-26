package org.stepik.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int countItems = scanner.nextInt();
    ContinuousBackpack backpack = new ContinuousBackpack(scanner.nextInt());
    List<Item> itemsContainer = new ArrayList<>();
    while (countItems > 0 && scanner.hasNext()) {
      int costItem = scanner.nextInt();
      int capacityItem = scanner.nextInt();
      itemsContainer.add(new Item(costItem, capacityItem));
      countItems--;
    }
    scanner.close();
    itemsContainer.sort(Item::compareTo);
    int i = 1;
    while (backpack.isNotFull()) {
      int index = itemsContainer.size() - i;
      backpack.addItem(itemsContainer.get(index));
      itemsContainer.remove(index);
      if (index == 0) {
        break;
      }
    }
    System.out.printf("%.3f", backpack.maxCostAllBackpack());
  }
}


class ContinuousBackpack {

  private double capacity;
  List<Item> itemsContainer = new ArrayList<>();

  public ContinuousBackpack(double capacity) {
    this.capacity = capacity;
  }

  public void addItem(Item item) {
    if (isNotFull() && capacity >= item.getCapacityItem()) {
      itemsContainer.add(item);
      capacity = capacity - item.getCapacityItem();
    } else if (isNotFull() && capacity < item.getCapacityItem()) {
      item.setCapacityItem(capacity);
      itemsContainer.add(item);
      capacity = 0;
    }
  }

  public double maxCostAllBackpack() {
    return itemsContainer.stream()
        .map(item -> item.getUnitCost() * item.getCapacityItem())
        .reduce((x, a) -> x = x + a)
        .orElse(0D);
  }

  public boolean isNotFull() {
    return capacity > 0;
  }
}

class Item implements Comparable<Item> {

  private double capacityItem;
  private double unitCost;

  Item(double costItem, double capacityItem) {
    this.capacityItem = capacityItem;
    try {
      this.unitCost = costItem / capacityItem;

    } catch (ArithmeticException e) {
      this.unitCost = 0F;
    }
  }

  public double getCapacityItem() {
    return capacityItem;
  }

  public double getUnitCost() {
    return unitCost;
  }

  public void setCapacityItem(double capacityItem) {
    this.capacityItem = capacityItem;
  }

  @Override
  public int compareTo(Item that) {
    if (getUnitCost() > that.getUnitCost()) {
      return 1;
    }
    if (getUnitCost() < that.getUnitCost()) {
      return -1;
    }
    return 0;
  }
}