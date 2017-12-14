//package test.java.com.epam.Philippov.selfCollection;
//
//
//import java.util.Arrays;
//
//public class IntList<E> implements ArrayInterface<E> {
//
//  private static final int DEFAULT_SIZE = 16;
//  private int[] arr;
//  private int currentIndex = -1;
//
//
//  public IntList() {
//    this(DEFAULT_SIZE);
//  }
//
//  public IntList(int size) {
//    arr = new int[size];
//  }
//
//  /**
//   * добавление элемента в конец списка
//   * @param value
//   */
//  public void add(int value) {
//    if (currentIndex > arr.length) {
//      extendArray();
//    }
//
//    add(value, currentIndex + 1);
//  }
//
//  /**
//   * добавление элемента в указанную позицию
//   * @param value
//   * @param index
//   */
//  public void add(int value, int index) {
//    if (index > currentIndex + 1) {
//      System.err.println("Index out of bounds");
//      return;
//    }
//
//    if (index >= arr.length - 1) {
//      extendArray();
//    }
//
//    int length = (currentIndex - 1 > 0) ? currentIndex - 1 : 1;
//    System.arraycopy(arr, index, arr, index + 1, length);
//    arr[index] = value;
//
//    currentIndex += 1;
//
//  }
//
//  /**
//   * изменяет значение указанного элемента
//   * @param value
//   * @param index
//   */
//  public void set(int value, int index) {
//    if (index <= currentIndex) {
//      arr[index] = value;
//    } else if (index == 0 && currentIndex == -1) {
//      arr[0] = value;
//      currentIndex += 1;
//    }
//  }
//
//  /**
//   * удаление первого вхождения указанного элемента (если он присутствует в списке)
//   * возвращает true - если элемент был удален, в противном случае false
//   * @param value
//   * @return
//   */
//  public boolean remove(int value) {
//    int index = indexOf(value);
//
//    if (index >= 0) {
//      fastRemove(index);
//      trimToSize();
//      return true;
//    }
//
//    return false;
//  }
//
//  /**
//   * удаление первого вхождения указанного элемента (если он присутствует в списке)
//   * возвращает true - если элемент был удален, в противном случае false
//   * @param value
//   * @return
//   */
//  public boolean removeAll(int value) {
//
//    IntList newArr = new IntList();
//
//    for (int i = 0; i <= currentIndex; i++) {
//      if (arr[i] != value) {
//        newArr.add(value);
//      }
//    }
//
//    int oldSize = currentIndex + 1;
//    arr = newArr.toArray();
//    currentIndex = arr.length - 1;
//
//    return currentIndex != oldSize;
//
//  }
//
//  /**
//   * удаление элемента по указанному индексу
//   * возвращает удаленное значение
//   * @param index
//   * @return
//   */
//  public int removeFrom(int index) {
//
//    int value = get(index);
//
//    fastRemove(index);
//    trimToSize();
//
//    return value;
//  }
//
//  /**
//   * значение указанного элемента
//   * @param index
//   * @return
//   */
//  public int get(int index) {
//    if (index <= currentIndex) {
//      return arr[index];
//    }
//
//    return -1; //FIXME throw exception
//  }
//
//  /**
//   * возвращаем копию внутреннего массива
//   * @return
//   */
//  public int[] toArray() {
//    return Arrays.copyOf(arr,currentIndex + 1);
//  }
//
//  /**
//   * текущий размер списка
//   * @return
//   */
//  public int size() {
//    return currentIndex + 1;
//  }
//
//  /**
//   * поиск элемента (с головы списка к хвосту)
//   * @param value
//   * @return
//   */
//  public int indexOf(int value) {
//    for (int i = 0; i <= currentIndex; i++) {
//      if (arr[i] == value) {
//        return i;
//      }
//    }
//
//    return -1;
//  }
//
//  /**
//   * поиск элемента (c хвоста списка к голове)
//   * @param value
//   * @return
//   */
//  public int lastIndexOf(int value) {
//    for (int i = currentIndex; i <= currentIndex && i >= 0; i--) {
//      if (arr[i] == value) {
//        return i;
//      }
//    }
//    return -1;
//  }
//
//  /**
//   * уменьшает размер внутреннего массива до актуального значения
//   */
//  public void trimToSize() {
//    if (arr.length >= currentIndex * 2) {
//      int[] newArr = new int[currentIndex * 2];
//      System.arraycopy(arr, 0, newArr, 0, currentIndex);
//      arr = newArr;
//    }
//  }
//
//  private void extendArray() {
//    int[] newArr = new int[arr.length * 2];
//    System.arraycopy(arr, 0, newArr, 0, currentIndex);
//    arr = newArr;
//  }
//
//  private void fastRemove(int index) {
//    if (index + 1 >= arr.length) {
//      System.arraycopy(arr, index + 1, arr, index, currentIndex - index - 1);
//    }
//    currentIndex -= 1;
//  }
//}
