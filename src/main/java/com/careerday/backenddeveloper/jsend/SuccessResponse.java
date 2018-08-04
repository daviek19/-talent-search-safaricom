package com.careerday.backenddeveloper.jsend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SuccessResponse extends Response {

    String successStatus = "success";

    public SuccessResponse() {
        super(null);
        super.setStatus(successStatus);
    }

    public SuccessResponse(Object data) {
        super(data);
        super.setStatus(successStatus);
    }

    public SuccessResponse(String dataName, Object data) {
        super(dataName, data);
        super.setStatus(successStatus);
    }

    @Override
    public ResponseEntity<Response> send(HttpStatus status) {
        return super.send(status);
    }
}