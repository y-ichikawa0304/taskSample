package com.example.taskSample;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.example.taskSample.RegisterAccountController.Account;

@Service
public class AccountDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    AccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void register(Account account) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(account);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("accounts")
                .usingGeneratedKeyColumns("ID"); // auto_incrementの時はこれが必要
        insert.execute(param);

    }

    public Map<String, Object> loginWeb(String accountID, String password) {
        String query = "select * from accounts where accountID = ? and password = ?";
        return jdbcTemplate.queryForMap(query, accountID, password);
    }
}
