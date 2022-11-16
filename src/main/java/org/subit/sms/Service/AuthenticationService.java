package org.subit.sms.Service;

import org.subit.sms.DTO.LoginDTO;
import org.subit.sms.DTO.Response;
import org.subit.sms.DTO.User;


public interface AuthenticationService {
    /*
    provide login service
     */
    public Response<LoginDTO> login(User user);

    /*
    logout user by username
     */
    public Response<Object> logout(String username);

    /*
    modify password
    user.password represent the new password due to frontend will check the same between new and old password
     */
    public Response<Object> modifyPassword(User user);


    /*
    Service below provide Sign Up feature
     */
    /*
    insert this user to database, and send the activate link to his email
     */
    public Response<Object> SendCaptcha(User user, String email);

    /*
    verify the captcha and activate the account
     */
    public Response<Object> activateAccount(String captcha);
}
