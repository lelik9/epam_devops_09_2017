package com.epam.Philippov.selfCollection;


public class IntList implements ArrayInterface{

    private int[] arr;
    private int currentIndex = -1;


    public IntList() {
        arr = new int[16];
    }

    public IntList(int size) {
        arr = new int[size];
    }

    public void add(int value) {
        // TODO добавление элемента в конец списка
        if(currentIndex > arr.length) {
            extendArray();
        }
        arr[currentIndex+1] = value;
        currentIndex += 1;
    }


    public void add(int value, int index) {
        // TODO добавление элемента в указанную позицию

        if(index >= arr.length-1) {
            extendArray();
        }

        if(index <= currentIndex){
            int[] newArr = new int[arr.length];

            System.arraycopy(arr,0, newArr,0, index);
            newArr[index] = value;
            System.arraycopy(arr, index, newArr,index+1, currentIndex-index);

            arr = newArr;
            currentIndex += 1;
        }

    }

    public void set(int value, int index) {
        // TODO изменяет значение указанного элемента
        if(index <= currentIndex) {
            arr[index] = value;
            currentIndex += 1;
        }
    }

    public boolean remove(int value) {
        // TODO удаление первого вхождения указанного элемента (если он присутствует в списке)
        // TODO возвращает true - если элемент был удален, в противном случае false
        int index = indexOf(value);

        if (index >= 0){
            fastRemove(index);
            trimToSize();
            return true;
        }

        return false;
    }



    public boolean removeAll(int value) {
        // TODO удаление всех вхождений указанного элемента (если он присутствует в списке)
        // TODO возвращает true - если элемент был удален, в противном случае false
        IntList newArr = new IntList();

        for (int i = 0; i <= currentIndex; i++) {
            if (arr[i] != value) {
                newArr.add(value);
            }
        }

        arr = newArr.toArray();

        return newArr.size() > 0;

    }

    public int removeFrom(int index) {
        // TODO удаление элемента по указанному индексу
        // TODO возвращает удаленное значение
        int value = get(index);

        fastRemove(index);
        trimToSize();

        return value;
    }

    public int get(int index) {
        // TODO значение указанного элемента
        if (index <= currentIndex){
            return arr[index];
        }

        return -1; //FIXME throw exception
    }

    public int[] toArray() {
        // TODO возвращаем копию внутреннего массива
        int[] newArr = new int[currentIndex+1];

        System.arraycopy(arr, 0, newArr, 0, currentIndex + 1);

        return newArr;
    }

    public int size() {
        // TODO текущий размер списка
        return currentIndex+1;
    }

    public int indexOf(int value) {
        // TODO поиск элемента (с головы списка к хвосту)
        for (int i = 0; i <= currentIndex; i++) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(int value) {
        // TODO поиск элемента (c хвоста списка к голове)
        for (int i = currentIndex; i <= currentIndex; i--) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public void trimToSize() {
        // TODO уменьшает размер внутреннего массива до актуального значения
        if(arr.length >= currentIndex * 3) {
            int[] newArr = new int[currentIndex * 2];
            System.arraycopy(arr, 0, newArr, 0, currentIndex);
            arr = newArr;
        }
    }

    private void extendArray() {
        int[] newArr = new int[arr.length*2];
        System.arraycopy(arr,0, newArr,0, currentIndex);
        arr = newArr;
    }

    private void fastRemove(int index) {
        System.arraycopy(arr, index, arr, index+1, currentIndex-index-1);
        currentIndex -= 1;
    }
}
