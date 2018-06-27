package com.dranawhite.base.util;

import org.springframework.util.backoff.BackOffExecution;
import org.springframework.util.backoff.ExponentialBackOff;

/**
 * @author liangyq
 * @version [1.0, 2018/4/12 15:47]
 */
public final class RetryUtil {

	private static final int DEFAULT_RETRY_COUNT = 9;
	private static final double DEFAULT_MULTIPLIER = 2;
	private static final long DEFAULT_INITIAL_INTERVAL = 6 * 1000;
	private static final int DEFAULT_MAX_INTERVAL = 120000;
	private static ExponentialBackOff backOff;
	public static final long STOP = BackOffExecution.STOP;

	static {
		backOff = new ExponentialBackOff();
		long maxElapsedTime = calculateMaxElapsedTime();
		backOff.setMaxElapsedTime(maxElapsedTime);
		backOff.setMultiplier(DEFAULT_MULTIPLIER);
		backOff.setInitialInterval(DEFAULT_INITIAL_INTERVAL);
		backOff.setMaxInterval(DEFAULT_MAX_INTERVAL);
	}

	public static long getTtlByCounter(long counter) {
		BackOffExecution execution = backOff.start();
		long ttl = -1L;
		while (counter > -1) {
			ttl = execution.nextBackOff();
			if (ttl == STOP) {
				return STOP;
			}
			counter--;
		}
		return ttl;
	}

	private static long calculateMaxElapsedTime() {
		int counter = 0;
		long elapasedTime = DEFAULT_INITIAL_INTERVAL;
		long maxIntervalTime;
		while (++counter < DEFAULT_RETRY_COUNT) {
			long tmpMaxIntervalTime = (long) (DEFAULT_INITIAL_INTERVAL * Math.pow(DEFAULT_MULTIPLIER, counter));
			maxIntervalTime = tmpMaxIntervalTime < DEFAULT_MAX_INTERVAL ? tmpMaxIntervalTime : DEFAULT_MAX_INTERVAL;
			elapasedTime += maxIntervalTime;
		}
		return elapasedTime;
	}
}
