package com.study.arithmetic;

public class SortPractice {

    public static void main(String[] args) {
        int[] array = {4, 1, 5, 6, 2, 7, 3};
        shellSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void shellSort(int[] array) {
        int j = 0;
        int temp;
        for (int increment = array.length / 2; increment > 0; increment /= 2) {
            for (int i = increment; i < array.length; i++) {
                temp = array[i];
                for (j = i; j >= increment; j -= increment) {
                    if (array[j - increment] > temp) {
                        array[j] = array[j - increment];
                    } else {
                        break;
                    }
                }
                array[j] = temp;
            }
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int index = getIndex(array, low, high);
            quickSort(array, 0, index);
            quickSort(array, index + 1, high);
        }
    }

    public static int getIndex(int[] array, int low, int high) {
        int temp = array[low];
        while (low < high) {
            while (low < high && array[high] > temp) {
                high--;
            }
            array[low] = array[high];

            while (low < high && array[low] < temp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }
}
