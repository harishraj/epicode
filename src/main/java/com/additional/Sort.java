package com.additional;

import java.util.Arrays;

public class Sort {

    static class QuickSort {

        private Integer[] numbers;
        private int number;

        public void sort(Integer[] values) {
            if (values == null || values.length == 0) {
                return;
            }

            this.numbers = values;
            number = values.length;
            quicksort(0, number - 1);
        }

        private void quicksort(int low, int high) {
            int i = low, j = high;
            int pivot = numbers[low + (high - low) / 2];

            while (i <= j) {
                while (numbers[i] < pivot) {
                    i++;
                }
                while (numbers[j] > pivot) {
                    j--;
                }

                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }

            // Recursion
            if (low < j)
                quicksort(low, j);
            if (i < high)
                quicksort(i, high);
        }

        private void swap(int i, int j) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
    }

    static class MergeSort {
        private Integer[] numbers;
        private Integer[] helper;

        private int number;

        public void sort(Integer[] values) {
            this.numbers = values;
            number = values.length;
            this.helper = new Integer[number];
            mergesort(0, number - 1);
        }

        private void mergesort(int low, int high) {

            if (low < high) {
                int middle = low + (high - low) / 2;
                mergesort(low, middle);
                mergesort(middle + 1, high);
                merge(low, middle, high);
            }
        }

        private void merge(int low, int middle, int high) {

            for (int i = low; i <= high; i++) {
                helper[i] = numbers[i];
            }

            int i = low;
            int j = middle + 1;
            int k = low;
            while (i <= middle && j <= high) {
                if (helper[i] <= helper[j]) {
                    numbers[k] = helper[i];
                    i++;
                } else {
                    numbers[k] = helper[j];
                    j++;
                }
                k++;
            }

            while (i <= middle) {
                numbers[k] = helper[i];
                k++;
                i++;
            }

        }
    }

    public static void main(String[] args) {

        Integer[] test = { 15, 5, 6, 2, 4, 8, 5, 5, 1, 4, 6, 6, 9, 7 };
        Integer[] test1 = { 15, 5, 6, 2, 4, 8, 5, 5, 1, 4, 6, 6, 9, 7 };

        QuickSort quickSort = new QuickSort();
        quickSort.sort(test);
        System.out.println(Arrays.asList(test));

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(test1);
        System.out.println(Arrays.asList(test1));


    }

}
