package org.jboss.perf.benchmark;

import com.sun.faban.driver.BenchmarkDriver;
import com.sun.faban.driver.BenchmarkOperation;
import com.sun.faban.driver.CycleType;
import com.sun.faban.driver.DriverContext;
import com.sun.faban.driver.FatalException;
import com.sun.faban.driver.FlatMix;
import com.sun.faban.driver.NegativeExponential;
import com.sun.faban.driver.Timing;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import static org.jboss.perf.benchmark.Constants.HASH_OP_NAME;

@BenchmarkDriver(name = Constants.HASHING_SERVLET_DRIVER_NAME,
		threadPerScale = Constants.HASHING_THREADS_PER_SCALE,
		metric = Constants.HASHING_METRIC_NAME,
		opsUnit = Constants.HASING_OPS_UNIT_NAME,
		percentiles = {"99", "99.9", "99.99", "99.999"})
@FlatMix(operations = {
		HASH_OP_NAME},
		mix = {
				Constants.HASH_MIX},
		deviation = Constants.MIX_DEVIATION)
@NegativeExponential(cycleType = CycleType.CYCLETIME,
		cycleMean = Constants.HASHING_CYCLE_MEAN_TIME_MILLIS,
		cycleDeviation = Constants.CYCLE_DEVIATION)
public class HashingServletDriver {

	private static final String HTTP = "http://";
	private static final String PORT_DELIMITER = ":";
	private static final String PATH_SEPERATOR = "/";

	DriverContext context;

	Logger logger;
	String ident;

	int txRate;

	protected String response;

	HttpClient client;
	HttpGet request;


	public HashingServletDriver() {
		try {
			context = DriverContext.getContext();

			logger = context.getLogger();
			ident = context.getDriverName() + "[" + context.getAgentId() + "]." + context.getThreadId() + ": ";

			txRate = context.getScale();

			String port = context.getProperty( "portString" ) ;
			String host = context.getProperty( "hostString" ) ;

			String url = buildUrl( host, port, context.getProperty( "queryString" ) );

			client = HttpClientBuilder.create().build();
			request = new HttpGet(url);

		} catch (Exception e) {
			throw new FatalException( e );
		}
	}

	private String buildUrl(String host, String port, String queryString) {
		StringBuilder urlBuilder = new StringBuilder();

		urlBuilder.append( HTTP );
		urlBuilder.append( host );
		urlBuilder.append( PORT_DELIMITER );
		urlBuilder.append( port );
		urlBuilder.append( PATH_SEPERATOR );
		urlBuilder.append( queryString );

		return urlBuilder.toString();
	}

    @BenchmarkOperation(name="Hash", timing= Timing.MANUAL, percentileLimits={2.0D, 2.0D, 2.0D, 2.0D})
    public void doHash()
            throws Exception
    {

		response = null;


		HttpResponse response = null;
		try {
			DriverContext.getContext().recordTime();
			response = client.execute( request );
			DriverContext.getContext().recordTime();

			BufferedReader rd = new BufferedReader(
					new InputStreamReader( response.getEntity().getContent() ) );

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append( line );
			}

			this.response = result.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//cleanup
		}
    }
}
