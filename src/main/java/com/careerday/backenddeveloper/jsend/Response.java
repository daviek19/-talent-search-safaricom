package com.careerday.backenddeveloper.jsend;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Response {

    protected String status;
    private Object data;
    private String message;

    public Response() {
        this(null);
    }

    public Response(Object data) {
        this.data = data;
    }

    public Response(String dataName, Object data) {
        HashMap<String, Object> response = new HashMap<>();
        response.put(dataName, data);
        this.data = response;
    }

    public ResponseEntity<Response> send(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

    public Object getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
