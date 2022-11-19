package org.subit.sms.service.Impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.subit.sms.Exception.*;
import org.subit.sms.config.RedisConfig;
import org.subit.sms.data.Account;
import org.subit.sms.data.repository.AccountRepo;
import org.subit.sms.dto.User;
import org.subit.sms.service.AuthorizationService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthorizationService {

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

    @Transactional
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

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Resource
    private JavaMailSender mailSender;

    @Value("${sms.account.validate-base-url}")
    private String baseUrl;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void SendCaptcha(String username, String email) throws UsernameNotFoundException, CaptchaException {
        if (Objects.isNull(username)) throw new UsernameNotFoundException();
        String auth_code = SaFoxUtil.getRandomString(60);
        String key = "auth:" + username;
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.html");
            Map<String, Object> model = new HashMap<>();
            model.put("url", baseUrl + "?code=" + auth_code);
            String templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(email);
            helper.setSubject("[SubIT]");
            helper.setText(templateHtml, true);
//            redisTemplate.opsForValue().set(key, auth_code);
            mailSender.send(message);
        } catch (MessagingException | TemplateException | IOException e) {
            throw new CaptchaException();
        }
    }

    @Override
    public void activateAccount(String captcha) {

    }

}
