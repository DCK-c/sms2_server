package org.subit.sms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subit.sms.dto.Response;
import org.subit.sms.dto.ReturnCode;

@RestController
@RequestMapping("/")
public class ExperimentalController {
    @GetMapping("/hello")
    public Response<String> hello() {
        Response<String> t = new Response<>(ReturnCode.Success, "ok");
        System.out.println(t.getTime_stamp());
        return t;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
