package org.subit.sms.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Service;
import org.subit.sms.data.Account;
import org.subit.sms.data.repository.AccountRepo;
import org.subit.sms.dto.User;
import org.subit.sms.handler.Exception.*;
import org.subit.sms.service.AuthenticationService;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Resource
    AccountRepo accountRepo;

    @Override
    public Account login(User user) throws UsernamePasswordNotMatchException, UserForbiddenException, AccountInactivatedException {
        Account loginTarget = accountRepo.findAccountByUsernameAndDeletedIsFalse(user.getUsername())
                .orElseThrow(UsernamePasswordNotMatchException::new);  // no data match in the database

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
    public void modifyPassword(User user) throws UsernameNotFoundException, PasswordNotMatchException {
        Account targetUser = accountRepo.findAccountByUsernameAndDeletedIsFalse(user.getUsername())
                .orElseThrow(UsernameNotFoundException::new);

        // check auth
        if (!Objects.equals(targetUser.getPassword(), user.getCaptcha())) throw new PasswordNotMatchException();

        //modify password
        targetUser.setPassword(user.getPassword());
        accountRepo.save(targetUser);
    }

    @Override
    public void SendCaptcha(User user, String email) {

    }

    @Override
    public void activateAccount(String captcha) {

    }

}
