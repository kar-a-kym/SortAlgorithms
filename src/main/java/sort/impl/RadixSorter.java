package sort.impl;

import sort.Sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class RadixSorter implements Sorter {

    public List<Integer> sort(List<Integer> numbers) {
        List<List<Integer>> blocks = initBlocks();
        fillBlocks(numbers, blocks);
        int amount = findMaxDigitAmount(numbers);
        return sortNumbersInsideBlocks(blocks, amount).get(0);
    }

    private int findMaxDigitAmount(List<Integer> numbers) {
        return numbers.stream()
                .map(num -> String.valueOf(num.intValue()).length())
                .max(Comparator.comparingInt(num -> num))
                .orElseThrow(() -> new RuntimeException("Could not find max number"));
    }

    private void fillBlocks(List<Integer> array, List<List<Integer>> blocks) {
        for (Integer number : array) {
            int digit = getDigit(number, 0);
            blocks.get(digit).add(number);
        }
    }

    private List<List<Integer>> sortNumbersInsideBlocks(List<List<Integer>> blocks, int digitAmount) {
        int digitIndex = 1;
        while (digitIndex <= digitAmount) {
            //TODO escape creating new list
            List<List<Integer>> internalBlocks = initBlocks();
            for (List<Integer> block : blocks) {
                for (Integer number : block) {
                    int digit = getDigit(number, digitIndex);
                    internalBlocks.get(digit).add(number);
                }
            }
            blocks = internalBlocks;
            digitIndex++;
        }
        return blocks;
    }

    private List<List<Integer>> initBlocks() {
        List<List<Integer>> blocks = new ArrayList<>();
        IntStream.range(0, 10).forEach((number) -> blocks.add(new ArrayList<>()));
        return blocks;
    }

    private int getDigit(int number, int index) {
        return number / (int) Math.pow(10, index) % 10;
    }

}