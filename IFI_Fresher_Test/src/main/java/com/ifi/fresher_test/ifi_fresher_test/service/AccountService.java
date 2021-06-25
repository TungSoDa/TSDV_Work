package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.AccountDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.AccountMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Account;
import com.ifi.fresher_test.ifi_fresher_test.repository.AccountRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> findAll() {
        return AccountMapper.arrayEntityToDTO(accountRepository.findAll());
    }

    public ResponseEntity<AccountDTO> findAccountByUsername(String username) {
        Optional<Account> optionalAccount = accountRepository.findById(username);
        return optionalAccount.map(account -> new ResponseEntity<>(
                AccountMapper.entityToDTO(account), HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> addAccount(Account account) {
        if (!accountRepository.existsById(account.getUsername())) {
            return new ResponseEntity<Account>(accountRepository.save(account), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>(MessageResource.ACCOUNT_ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        }
    }

    public ResponseEntity<?> updateAccount(String username, AccountDTO accountDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(username);
        if (optionalAccount.isPresent()) {
            System.out.println("Run here" + accountDTO.getRole());
            return optionalAccount.map(account -> {
                account.setRole(accountDTO.getRole());
                accountRepository.save(account);
                return new ResponseEntity<AccountDTO>(new AccountDTO(
                        account.getUsername(),
                        account.getRole()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.ACCOUNT_NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteAccount(String username) {
        Optional<Account> optionalAccount = accountRepository.findById(username);
        if (optionalAccount.isPresent()) {
            return optionalAccount.map(account -> {
                accountRepository.delete(account);
                return new ResponseEntity<Account>(account, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.ACCOUNT_NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }
}
