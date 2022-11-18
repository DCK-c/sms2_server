package org.subit.sms.service;

import org.subit.sms.data.Account;
import org.subit.sms.dto.User;
import org.subit.sms.handler.Exception.*;


public interface AuthenticationService {
    /*
    provide login service
     */
    Account login(User user) throws UsernamePasswordNotMatchException, UserForbiddenException, AccountInactivatedException;

    /*
    logout user by username
     */
    void logout();

    /*
    modify password
    user.password represent the new password due to frontend will check the same between new and old password
     */
    void modifyPassword(User user) throws UsernameNotFoundException, PasswordNotMatchException;

    /*
    Service below provide Sign Up feature
     */
    /*
    insert this user to database, and send the activate link to his email
     */
    void SendCaptcha(User user, String email);

    /*
    verify the captcha and activate the account
     */
    void activateAccount(String captcha);
}
