package org.jboss.perf.benchmark;

import com.sun.faban.driver.BenchmarkDefinition;

@BenchmarkDefinition(
    name      = Constants.BENCHMARK_NAME,
    version   = Constants.BENCHMARK_VERSION,
    drivers   = {
        HashingServletDriver.class
        },
    
    scaleName = Constants.SCALE_NAME,
    metric    = Constants.METRIC_NAME
)
public class WebBenchmark {

  public WebBenchmark()  throws Exception {}

}
