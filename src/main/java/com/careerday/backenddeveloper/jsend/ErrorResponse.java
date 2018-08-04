package com.careerday.backenddeveloper.jsend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse extends Response{
    String errorStatus = "error";

    public ErrorResponse() {
        super(null);
        super.setStatus(errorStatus);
    }

    /**
     * Put the error in data and
     *
     * @param data
     */
    public ErrorResponse(String data) {
        super(data);
        super.setStatus(errorStatus);
    }

    /**
     * *
     * Puts the error in the message field and can also contain some data
     *
     * @param data
     * @param message
     */
    public ErrorResponse(Object data, String message) {
        super(data);
        super.setMessage(message);
        super.setStatus(errorStatus);
    }

    @Override
    public ResponseEntity<Response> send(HttpStatus status) {
        return super.send(status);
    }
}
