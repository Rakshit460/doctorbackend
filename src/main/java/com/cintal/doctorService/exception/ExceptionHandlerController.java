package com.cintal.doctorService.exception;

import com.cintal.doctorService.models.ResponseEnvelope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = BookingAlreadyDoneException.class)
    public ResponseEntity bookingAlreadyDoneException(BookingAlreadyDoneException bookingAlreadyDoneException) {
        System.out.println("inside exception "+bookingAlreadyDoneException.getMessage());

        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setError(bookingAlreadyDoneException.getMessage());
        responseEnvelope.setStatus(400);
        return ResponseEntity.ok().body(responseEnvelope);


    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity internalServerError() {
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setError("Internal server error");
        responseEnvelope.setStatus(500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseEnvelope);

    }

}
