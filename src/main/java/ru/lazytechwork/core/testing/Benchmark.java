package ru.lazytechwork.core.testing;

import java.util.HashMap;

/**
 * Easy to use tests benchmarking solution
 */
public class Benchmark {
    private final HashMap<String, Long> measuringBenchmarkPoints = new HashMap<>();
    private final HashMap<String, Long> benchmarkResults = new HashMap<>();

    /**
     * Start benchmark with specified name
     *
     * @param name name of benchmark
     */
    public void startBenchmark(String name) {
        measuringBenchmarkPoints.put(name, System.currentTimeMillis());
    }

    /**
     * Stop benchmark and save benchmark result
     *
     * @param name name of benchmark
     * @return benchmark result
     */
    public Long stopBenchmark(String name) {
        if (measuringBenchmarkPoints.containsKey(name)) {
            benchmarkResults.put(name, System.currentTimeMillis() - measuringBenchmarkPoints.get(name));
            return benchmarkResults.get(name);
        }
        return 0L;
    }

    /**
     * Get all results of current benchmark
     *
     * @return all results of current benchmark
     */
    public HashMap<String, Long> getBenchmarkResults() {
        return benchmarkResults;
    }
}
