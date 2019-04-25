package com.dranawhite.base.util;

import com.dranawhite.base.exception.DranawhiteException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liangyq
 * @version [1.0, 2018/5/16 15:44]
 */
public final class ThreadUnit {

	public static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException ex) {
			throw new DranawhiteException(ex);
		}
	}

	public static void wait(CountDownLatch latch) {
		try {
			latch.await();
		} catch (InterruptedException ex) {
			throw new DranawhiteException(ex);
		}
	}

	public static void wait(Object obj) {
		try {
			obj.wait();
		} catch (InterruptedException ex) {
			throw new DranawhiteException(ex);
		}
	}
}
