package org.subit.sms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String token;
    private String username;
    private String nickname;
    private String email;
}
