package com.ifi.fresher_test.ifi_fresher_test.controller.api;

import com.ifi.fresher_test.ifi_fresher_test.dto.AccountDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Account;
import com.ifi.fresher_test.ifi_fresher_test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public List<AccountDTO> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDTO> findAccountByUsername(@PathVariable String username) {
        return accountService.findAccountByUsername(username);
    }

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Account> updateAccount(@PathVariable String username, @RequestBody AccountDTO userDTO) {
        return accountService.updateAccount(username, userDTO);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Account> deleteAccount(@PathVariable String username) {
        return accountService.deleteAccount(username);
    }
}
