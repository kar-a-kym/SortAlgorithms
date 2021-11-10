package sort.impl;

import sort.Sorter;

import java.util.List;

public class BubbleSorter implements Sorter {
    @Override
    public List<Integer> sort(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size() - 1; j++) {
                if (numbers.get(j) > numbers.get(j+1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j+1));
                    numbers.set(j + 1, temp);
                }
            }
        }
        return numbers;
    }
}
