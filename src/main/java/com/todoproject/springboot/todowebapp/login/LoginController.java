package com.todoproject.springboot.todowebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
    public AuthorizationService authorizationService;

    public LoginController(AuthorizationService authorizationService) {
        super();
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value= "log-in", method= RequestMethod.GET)
    public String gotoLoginPage() {
        return "login";
    }
    @RequestMapping(value="log-in", method=RequestMethod.POST)
    public String redirectOSubmit(@RequestParam String name, @RequestParam String password, ModelMap model){
        if(authorizationService.authenticate(name, password)){
            model.put("name", name);
            return "welcome";
        }

        String error = "Invalid credential! Please try again.";
        model.put("error", error);
        return "login";
    }
}
