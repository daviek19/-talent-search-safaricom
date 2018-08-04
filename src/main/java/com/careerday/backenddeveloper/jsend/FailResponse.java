package com.careerday.backenddeveloper.jsend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FailResponse extends Response {

    String failStatus = "fail";

    public FailResponse() {
        super(null);
        super.setStatus(failStatus);
    }

    public FailResponse(Object data) {
        super(data);
        super.setStatus(failStatus);
    }

    @Override
    public ResponseEntity<Response> send(HttpStatus status) {
        return super.send(status);
    }
}