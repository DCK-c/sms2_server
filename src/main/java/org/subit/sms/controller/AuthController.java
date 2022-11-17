package org.subit.sms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subit.sms.dto.LoginDTO;
import org.subit.sms.dto.Response;
import org.subit.sms.dto.ReturnCode;
import org.subit.sms.dto.User;

@RestController
public class AuthController {
    @PostMapping("/login")
    public Response<LoginDTO> login(User user) {
        return new Response<>(ReturnCode.Success, new LoginDTO());
    }
}
