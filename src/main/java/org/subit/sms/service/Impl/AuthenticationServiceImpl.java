package org.subit.sms.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.sun.xml.bind.v2.schemagen.Util;
import org.springframework.stereotype.Service;
import org.subit.sms.data.Account;
import org.subit.sms.data.repository.AccountRepo;
import org.subit.sms.dto.User;
import org.subit.sms.handler.Exception.AccountInactivatedException;
import org.subit.sms.handler.Exception.UserForbiddenException;
import org.subit.sms.handler.Exception.UsernamePasswordNotMatchException;
import org.subit.sms.service.AuthenticationService;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Resource
    AccountRepo accountRepo;

    @Override
    public Account login(User user) throws UsernamePasswordNotMatchException, UserForbiddenException, AccountInactivatedException {
        Account loginTarget = accountRepo.findAccountByUsernameAndDeletedIsFalse(user.getUsername());

        // check username and password
        if (loginTarget == null) throw new UsernamePasswordNotMatchException();
        if (!Objects.equals(loginTarget.getPassword(), user.getPassword()))
            throw new UsernamePasswordNotMatchException();

        // check disable
        if (loginTarget.isDisable()) throw new UserForbiddenException();
        if (!loginTarget.isActivated()) throw new AccountInactivatedException();

        return loginTarget;
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public void modifyPassword(User user) {

    }

    @Override
    public void SendCaptcha(User user, String email) {

    }

    @Override
    public void activateAccount(String captcha) {

    }

}
