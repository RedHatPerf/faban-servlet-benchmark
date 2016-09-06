# Web Framework benchmkark
## Overview
 This benchmark has been created to measure performance differences between JbossWeb ([EAP6.4.9](http://developers.redhat.com/products/eap/overview/)) and Undertow ([Wildfly 10.x.x](http://wildfly.org/))

 The benchmark uses the [Faban framework](http://faban.org/) to drive load from multiple client machines to the System Under Test (SUT).
 Faban orchestrates the execution of benchmark methods across multiple clients, collates are calculates the system results.

##

A web servlet is hosted on the SUT. The Web servlet responds to GET HTTP requests, calculates a hash, the length based on a http parameter passed to the servlet and returns the result to the calling client.

Variable length response are created depending on the client request

## How to build deploy and run the benchmark

### Configure benchmark

### Build benchmark

The benchmark uses maven as the build tool.

1. start Java Application server.
2. mvn clean install

This will build the serlvet, faban driver, faban harness jar and deploy the servlet war onto EAP/Wildfly

### Run Benchmark

1. Navigate to http://test:9980/


