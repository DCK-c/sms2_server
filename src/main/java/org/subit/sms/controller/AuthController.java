package org.subit.sms.controller;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.subit.sms.Exception.*;
import org.subit.sms.data.Account;
import org.subit.sms.dto.LoginDTO;
import org.subit.sms.dto.Response;
import org.subit.sms.dto.User;
import org.subit.sms.service.AuthorizationService;

import javax.annotation.Resource;

@RestController
public class AuthController {

    @Resource
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        try {
            Account account = authorizationService.login(user);
            account.setId(1);
            account.setUsername("123");
            account.setPassword("123");
            account.setRole(1);
            StpUtil.login(10001, SaLoginConfig
                    .setExtra("username", account.getUsername())
                    .setExtra("role", account.getRole())
                    .setExtra("nickname", account.getNickname())
            );
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return Response.success(new LoginDTO(
                    tokenInfo.tokenValue,
                    account.getUsername(),
                    account.getNickname(),
                    account.getEmail(),
                    account.getRole()
            ));
        } catch (AccountInactivatedException | UsernamePasswordNotMatchException | UserForbiddenException e) {
            return Response.error(e.getReturnCode());
        }
    }

    @GetMapping("/logout")
    public Response logout() {
        if (StpUtil.isLogin()) {
            authorizationService.logout();
            return Response.success("logout successful");
        } else return Response.success("You are not login");

    }

    @PostMapping("/modifyPassword")
    public Response modifyPassword(@RequestBody User user) {
        try {
            authorizationService.modifyPassword(user);
            return Response.success("Password Change Success");
        } catch (UsernameNotFoundException | PasswordNotMatchException e) {
            return Response.error(e.getReturnCode());
        }
    }

}
