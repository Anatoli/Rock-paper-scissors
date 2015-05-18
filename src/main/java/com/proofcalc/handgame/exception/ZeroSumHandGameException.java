package com.proofcalc.handgame.exception;

public class ZeroSumHandGameException extends RuntimeException {

    public ZeroSumHandGameException(String message) {
        super(message);
    }

    public ZeroSumHandGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
