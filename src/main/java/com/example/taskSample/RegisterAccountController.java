package com.example.taskSample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterAccountController {
    record Account(String accountID, String password, String email) {
    }

    private List<Account> accounts = new ArrayList<>();
    private final AccountDao dao;

    @Autowired
    RegisterAccountController(AccountDao dao) {
        this.dao = dao;
    }

    @GetMapping("/registerAccount")
    public String registerAccount() {
        return "registerAccount";
    }

    // htmlから入力したデータを引数としてdaoに渡す
    @PostMapping("/register")
    public String register(@RequestParam("accountID") String accountID,
            @RequestParam("password") String password, @RequestParam("email") String email) {
        Account account = new Account(accountID, password, email);
        dao.register(account);
        return "redirect:/login";
    }

}
