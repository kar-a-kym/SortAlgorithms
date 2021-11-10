package sort.impl;

import sort.Sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickSorter implements Sorter {

    @Override
    public List<Integer> sort(List<Integer> numbers) {
        List<Integer> numbersCopy = new ArrayList<>();
        Collections.copy(numbersCopy, numbers);
        sort(numbersCopy, 0, numbersCopy.size() - 1);
        return numbersCopy;
    }

    private void sort(List<Integer> numbers, int lowerIndex, int higherIndex) {
        if (lowerIndex >= higherIndex) {
            return;
        }
        int elementIndex = splitWithSorting(numbers, lowerIndex, higherIndex);
        sort(numbers, lowerIndex, elementIndex - 1);
        sort(numbers, elementIndex + 1, higherIndex);
    }

    private int splitWithSorting(List<Integer> numbers, int lowerIndex, int higherIndex) {
        int leftBorderIndex = lowerIndex;
        int rightBorderIndex = higherIndex;
        while (true) {
            for (; leftBorderIndex < higherIndex; ++leftBorderIndex) {
                if (numbers.get(leftBorderIndex) > numbers.get(lowerIndex)) {
                    break;
                }
            }
            for (; rightBorderIndex > lowerIndex; --rightBorderIndex) {
                if (numbers.get(rightBorderIndex) < numbers.get(lowerIndex)) {
                    break;
                }
            }
            if (leftBorderIndex >= rightBorderIndex) {
                break;
            }
            Collections.swap(numbers, leftBorderIndex, rightBorderIndex);
        }
        Collections.swap(numbers, lowerIndex, rightBorderIndex);
        return rightBorderIndex;
    }

}