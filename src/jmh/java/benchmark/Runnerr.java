package benchmark;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import sort.Sorter;
import sort.impl.BubbleSorter;
import sort.impl.QuickSorter;
import sort.impl.RadixSorter;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
@Measurement(iterations = 1, time = 10, timeUnit = NANOSECONDS)
@OutputTimeUnit(NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class Runnerr {

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Param({"10000", "100000", "1000000"})
    private int numberAmount;

    @Benchmark
    public void radixSort() {
        Random random = new Random(System.currentTimeMillis());
        List<Integer> collect = IntStream.range(1, numberAmount).map(i -> random.nextInt(10000000)).boxed().collect(Collectors.toList());
        //System.out.println(collect);
        Sorter radixSorter = new RadixSorter();
        List<Integer> sorted2 = radixSorter.sort(collect);
    }

    @Benchmark
    public void quickSort() {
        Random random = new Random(System.currentTimeMillis());
        List<Integer> collect = IntStream.range(1, 10000).map(i -> random.nextInt(10000000)).boxed().collect(Collectors.toList());
        Sorter radixSorter = new QuickSorter();
        long before = System.nanoTime();
        List<Integer> sorted2 = radixSorter.sort(collect);
        long after = System.nanoTime();
        System.out.println(after - before);
    }

    @Benchmark
    public void bubbleSort() {
        Random random = new Random(System.currentTimeMillis());
        List<Integer> collect = IntStream.range(1, 10000).map(i -> random.nextInt(10000000)).boxed().collect(Collectors.toList());
        //System.out.println(collect);
        Sorter radixSorter = new BubbleSorter();
        List<Integer> sorted2 = radixSorter.sort(collect);
    }
}
