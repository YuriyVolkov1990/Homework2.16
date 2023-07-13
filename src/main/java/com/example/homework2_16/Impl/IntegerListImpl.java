package com.example.homework2_16.Impl;

import com.example.homework2_16.Exception.InvalidIndexException;
import com.example.homework2_16.Exception.NullItemException;
import com.example.homework2_16.Interface.IntegerList;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private int size;
    private Integer[] m;

    public IntegerListImpl() {
        m = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        m = new Integer[initSize];
    }


    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        m[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            m[size++] = item;
            return item;
        }

        System.arraycopy(m, index, m, index + 1, size - index);
        m[size++] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        m[size++] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);

        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = m[index];

        if (index != size) {
            System.arraycopy(m, index + 1, m, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) > -1;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            Integer s = m[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            Integer s = m[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return Integer.valueOf(m[index]);
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(m, size);
    }

    @Override
    public void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (Integer.parseInt(String.valueOf(arr[j])) < Integer.parseInt(String.valueOf(arr[minElementIndex]))) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    @Override
    public Integer quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
        return null;
    }
    @Override
    public Integer partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (Integer.parseInt(String.valueOf(arr[j])) <= Integer.parseInt(String.valueOf(pivot))) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }


    @Override
    public boolean binarySearch(Integer[] arr, int element) {
        sortSelection(arr);
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size >= m.length) {
            grow(size);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    private void grow(int oldSize) {
        System.arraycopy(m,0,m = new Integer[(int) (oldSize * 1.5)],0,size);
    }
}
