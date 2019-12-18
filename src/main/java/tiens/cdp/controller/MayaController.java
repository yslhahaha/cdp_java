package tiens.cdp.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MayaController {

    @RequestMapping(value = "/hq", method = RequestMethod.POST)
    public String login(@RequestBody String reqContent) {
        System.out.println("reqContent:" + reqContent);

        return "hq page!";
    }

    @RequestMapping(value = "/sj", method = RequestMethod.POST)
    public String query(@RequestBody String reqContent) {
        System.out.println("reqContent:" + reqContent);

        return "sj page!";
    }
}
