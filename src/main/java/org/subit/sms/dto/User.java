package org.subit.sms.dto;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String Captcha;
}
