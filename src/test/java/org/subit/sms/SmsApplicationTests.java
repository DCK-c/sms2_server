package org.subit.sms;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.subit.sms.Exception.CaptchaException;
import org.subit.sms.Exception.UsernameNotFoundException;
import org.subit.sms.service.AuthorizationService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SmsApplicationTests {

    @Resource
    JavaMailSender mailSender;

    @Resource
    private AuthorizationService authorizationService;

    @Test
    void contextLoads() throws UsernameNotFoundException, CaptchaException {
        authorizationService.SendCaptcha("steven", "hetianyang@i.pkuschool.edu.cn");
    }

}
