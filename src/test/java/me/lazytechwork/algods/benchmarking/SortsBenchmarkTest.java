package me.lazytechwork.algods.benchmarking;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SortsBenchmarkTest {
    private static Benchmark benchmark;
    private static final List<Integer> counts = List.of(10, 15, 25, 50, 100, 125, 150, 200, 500, 750, 1500, 2500, 4350, 5000, 7565, 10000);
    private static final Random random = new Random();

    @BeforeAll
    static void beforeAll() {
        benchmark = new Benchmark();
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
            String benchmarkName = "InsertionSort %d".formatted(count);
            benchmark.startBenchmark(benchmarkName);
            // TODO sort
            benchmark.stopBenchmark(benchmarkName);
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
        chart.setYAxisTitle("Time");

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
