package com.university.response;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseData<T> {

    private T data;
    private String message;
    private Integer status;
    private long timestamp = Instant.now().toEpochMilli();

    public ResponseData(T data, String message, Integer status) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public ResponseData(String message, Integer status) {
        this.message = message;
        this.status = status;
        this.timestamp = Instant.now().toEpochMilli();
    }
}
