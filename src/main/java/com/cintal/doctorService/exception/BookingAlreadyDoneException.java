package com.cintal.doctorService.exception;

public class BookingAlreadyDoneException extends RuntimeException {

    public BookingAlreadyDoneException() {
        super();
    }

    public BookingAlreadyDoneException(String s) {
        super(s);
    }

    public BookingAlreadyDoneException(String msg, Exception e) {
        super(msg, e);
    }


}
