package org.jboss.perf.benchmark;

public interface Constants {

    // general parameters
    String BENCHMARK_NAME = "JbossPerf-WebBenchmark";
    String BENCHMARK_VERSION = "0.1";
    String SCALE_NAME = "rps";
    String METRIC_NAME = "RPS";
    double MIX_DEVIATION = 2.5;
    double CYCLE_DEVIATION = 5.0;


    // parameters of httpHashing driver
    String HASHING_SERVLET_DRIVER_NAME = "HashingServletDriver";
    String HASHING_METRIC_NAME = "Hashing " + METRIC_NAME;
    String HASH_OP_NAME = "Hash";
    String HASING_OPS_UNIT_NAME = HASH_OP_NAME + " Transactions";
    int HHASHING_CYCLE_MEAN_TIME_MSS = 500;
    int HASHING_CYCLE_MEAN_TIME_MILLIS = HHASHING_CYCLE_MEAN_TIME_MSS;
    int HASHING_THREADS_PER_SCALE = 1;
    int HASH_MIX = 100;

}
