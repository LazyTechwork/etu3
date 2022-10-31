package me.lazytechwork.algods.benchmarking;

import me.lazytechwork.algods.utils.ArrayList;
import me.lazytechwork.algods.utils.sort.InsertionSort;
import me.lazytechwork.core.testing.Benchmark;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.*;

import static me.lazytechwork.algods.utils.sorts.SortTestHelper.assertArrayListSorted;

public class SortsBenchmarkTest {
    private static Benchmark benchmark;
    private static final List<Integer> counts = List.of(750, 1500, 2500, 4350, 5000, 7565, 10000);
    private static final Random random = new Random();
    private static final int[] UNSORTED_LIST = new int[counts.get(counts.size() - 1)];

    private static final InsertionSort<Integer> INSERTION_SORT = new InsertionSort<>();

    private void fillArrayList(ArrayList<Integer> array, int count) {
        for (int i = 0; i < count; i++)
            array.add(UNSORTED_LIST[i]);
    }

    @BeforeAll
    static void beforeAll() {
        benchmark = new Benchmark();
        for (int i = 0, l = counts.get(counts.size() - 1); i < l; i++)
            UNSORTED_LIST[i] = random.nextInt(0, 100000);
    }

    @Test
    void benchmarkTimSort() {
        counts.forEach((count) -> {
            String benchmarkName = "TimSort %d".formatted(count);
            benchmark.startBenchmark(benchmarkName);
            // TODO sort
            benchmark.stopBenchmark(benchmarkName);
        });
    }

    @Test
    void benchmarkInsertionSort() {
        counts.forEach((count) -> {
            ArrayList<Integer> array = new ArrayList<>(count);
            fillArrayList(array, count);
            int[] sorted = Arrays.stream(UNSORTED_LIST).limit(count).sorted().toArray();

            String benchmarkName = "InsertionSort %d".formatted(count);
            benchmark.startBenchmark(benchmarkName);
            INSERTION_SORT.sort(array, Comparator.comparingInt(a -> a));
            benchmark.stopBenchmark(benchmarkName);

            assertArrayListSorted(array, sorted, "%s - failed".formatted(benchmarkName));
        });
    }

    @Test
    void benchmarkMergeSort() {
        counts.forEach((count) -> {
            String benchmarkName = "MergeSort %d".formatted(count);
            benchmark.startBenchmark(benchmarkName);
            // TODO sort
            benchmark.stopBenchmark(benchmarkName);
        });
    }

    @Test
    void benchmarkBubbleSort() {
        counts.forEach((count) -> {
            String benchmarkName = "BubbleSort %d".formatted(count);
            int[] arr = new int[count];
            for (int i = 0; i < count; i++)
                arr[i] = random.nextInt();

            benchmark.startBenchmark(benchmarkName);
            int n = count;
            for (int i = 0; i < n - 1; i++)
                for (int j = 0; j < n - i - 1; j++)
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
            benchmark.stopBenchmark(benchmarkName);
        });
    }

    @AfterAll
    static void afterAll() throws IOException {
        // @formatter:off
        XYChart chart = new XYChart(500, 400, Styler.ChartTheme.Matlab);
        chart.setTitle("Sorts benchmarking");
        chart.setXAxisTitle("Elements sorted");
        chart.setYAxisTitle("Time (ms)");

        HashMap<String, Long[]> seriesData = new HashMap<>();

        benchmark.getBenchmarkResults().forEach((key, time) -> {
            String[] seriesInfo = key.split(" ", 2);
            int x = counts.indexOf(Integer.parseInt(seriesInfo[1]));
            Long[] benchmarks;
            if (seriesData.containsKey(seriesInfo[0]))
                benchmarks = seriesData.get(seriesInfo[0]);
            else
                benchmarks = new Long[counts.size()];

            benchmarks[x] = time;
            seriesData.put(seriesInfo[0], benchmarks);
        });

        seriesData.forEach((seriesName, times) -> {
            XYSeries series = chart.addSeries(seriesName, counts, Arrays.asList(times));
            series.setMarker(SeriesMarkers.CIRCLE);
        });

        VectorGraphicsEncoder.saveVectorGraphic(chart, "./build/reports/SortsBenchmark.svg", VectorGraphicsEncoder.VectorGraphicsFormat.SVG);

        // @formatter:on
    }
}
