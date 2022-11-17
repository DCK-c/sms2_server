package org.subit.sms.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;
    private long time_stamp;

    public Response(ReturnCode code, T data) {
        this.time_stamp = System.currentTimeMillis();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
    }
}
