package com.example.taskSample;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taskSample.RegisterAccountController.Account;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class loginController {

    record Account(String accountID, String password) {
    }

    private final AccountDao dao;

    loginController(AccountDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/login")
    String login() {
        return "login";
    }

    @PostMapping("/loginAccount")
    public String loginAccount(@RequestParam("accountID") String accountID,
            @RequestParam("password") String password,
            Model model) {
        try {
            Map<String, Object> account = dao.loginWeb(accountID, password);
            return "loginFin";

        } catch (Exception e) {
            model.addAttribute("error", "アカウントが間違っています");
            return "login";

        }
    }

}
