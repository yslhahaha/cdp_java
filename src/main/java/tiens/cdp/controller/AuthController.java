package tiens.cdp.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String reqContent) {
        System.out.println("reqContent:" + reqContent);

        return "login page!";
    }

    @RequestMapping(value = "/valcode", method = RequestMethod.GET)
    public String valcode() {
        return "val code page!";
    }
}
