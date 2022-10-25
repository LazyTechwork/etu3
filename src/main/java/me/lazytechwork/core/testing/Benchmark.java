package me.lazytechwork.core.testing;

import javax.naming.NameNotFoundException;
import java.util.HashMap;

/**
 * Easy to use tests benchmarking solution
 */
public class Benchmark {
    private HashMap<String, Long> measuringBenchmarkPoints;
    private HashMap<String, Long> benchmarkResults;

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
    public Long stopBenchmark(String name) throws NameNotFoundException {
        if (measuringBenchmarkPoints.containsKey(name)) {
            benchmarkResults.put(name, System.currentTimeMillis() - measuringBenchmarkPoints.get(name));
            return benchmarkResults.get(name);
        } else throw new NameNotFoundException("Benchmark with specified name is not started yet");
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
