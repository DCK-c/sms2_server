package org.subit.sms.service;

import org.subit.sms.dto.LoginDTO;
import org.subit.sms.dto.Response;
import org.subit.sms.dto.User;


public interface AuthenticationService {
    /*
    provide login service
     */
    Response<LoginDTO> login(User user);

    /*
    logout user by username
     */
    Response<Object> logout(String username);

    /*
    modify password
    user.password represent the new password due to frontend will check the same between new and old password
     */
    Response<Object> modifyPassword(User user);


    /*
    Service below provide Sign Up feature
     */
    /*
    insert this user to database, and send the activate link to his email
     */
    Response<Object> SendCaptcha(User user, String email);

    /*
    verify the captcha and activate the account
     */
    Response<Object> activateAccount(String captcha);
}
