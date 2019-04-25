package com.dranawhite.base.exception;

/**
 * @author dranawhite
 * @version : DranawhiteException.java, v 0.1 2019-04-25 15:26 dranawhite Exp $$
 */
public class DranawhiteException extends RuntimeException {

    public DranawhiteException() {
        super();
    }

    public DranawhiteException(Throwable tr) {
        super(tr);
    }

    public DranawhiteException(String message) {
        super(message);
    }
}
