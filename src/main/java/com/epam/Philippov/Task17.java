package com.epam.Philippov;

class Printer {
  public static <E> void printArray(E[] arr) {
    for (E anArr : arr) {
      System.out.println(anArr);
    }
  }
}

class Task17 {

  public static void main( String args[] ) {
    Integer[] intArray = {1, 2, 3};
    String[] stringArray = {"Hello", "World"};
    Printer.printArray(intArray);
    Printer.printArray(stringArray);
  }
}
