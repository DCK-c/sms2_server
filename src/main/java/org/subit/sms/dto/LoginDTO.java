package org.subit.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.subit.sms.data.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String token;
    private String username;
    private String nickname;
    private String email;
    private int role;

    public LoginDTO(String token, Account account) {
        this.token = token;
        this.username = account.getUsername();
        this.nickname = account.getNickname();
        this.email = account.getEmail();
        this.role = account.getRole();
    }
}
