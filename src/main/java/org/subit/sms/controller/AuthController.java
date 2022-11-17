package org.subit.sms.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.subit.sms.data.Account;
import org.subit.sms.dto.LoginDTO;
import org.subit.sms.dto.Response;
import org.subit.sms.dto.ReturnCode;
import org.subit.sms.dto.User;
import org.subit.sms.handler.Exception.AccountInactivatedException;
import org.subit.sms.handler.Exception.UserForbiddenException;
import org.subit.sms.handler.Exception.UsernamePasswordNotMatchException;
import org.subit.sms.service.AuthenticationService;

import javax.annotation.Resource;

@RestController
public class AuthController {

    @Resource
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        try {
            Account account = authenticationService.login(user);
            account.setId(1);
            account.setUsername("123");
            account.setPassword("123");
            account.setRole(1);
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return new Response(ReturnCode.Success, new LoginDTO(
                    tokenInfo.tokenValue,
                    account.getUsername(),
                    account.getNickname(),
                    account.getEmail(),
                    account.getRole()
            ));
        } catch (UsernamePasswordNotMatchException e) {
            return new Response(ReturnCode.UsernamePasswordError, null);
        } catch (UserForbiddenException e) {
            return new Response(ReturnCode.UserForbidden, null);
        } catch (AccountInactivatedException e) {
            return new Response(ReturnCode.AccountInactivated, null);
        }
    }

    @GetMapping("/logout")
    public Response logout() {
        if (StpUtil.isLogin()) {
            authenticationService.logout();
            return new Response(ReturnCode.Success, "logout successful");
        } else {
            return new Response(ReturnCode.Success, "You are not login");
        }
    }

}
