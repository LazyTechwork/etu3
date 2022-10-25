package me.lazytechwork.algods.benchmarking;

import me.lazytechwork.core.testing.Benchmark;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SortsBenchmarkTest {
    public static Benchmark benchmark;
    private static final List<Integer> counts = List.of(1000, 10000, 100000, 1000000, 10000000);

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
            benchmark.startBenchmark(benchmarkName);
            // TODO sort
            benchmark.stopBenchmark(benchmarkName);
        });
    }

    @AfterAll
    static void afterAll() throws IOException {
        XYChart chart = new XYChart(500, 400);
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
    }
}
