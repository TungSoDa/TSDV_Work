package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.AccountDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.AccountMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Account;
import com.ifi.fresher_test.ifi_fresher_test.model.Contestant;
import com.ifi.fresher_test.ifi_fresher_test.model.Contributor;
import com.ifi.fresher_test.ifi_fresher_test.repository.AccountRepository;
import com.ifi.fresher_test.ifi_fresher_test.repository.ContestantRepository;
import com.ifi.fresher_test.ifi_fresher_test.repository.ContributorRepository;
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

    public ResponseEntity<Account> addAccount(Account account) {
        if (!accountRepository.existsById(account.getUsername())) {
            return new ResponseEntity<>(accountRepository.save(account), HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<Account> updateAccount(String username, AccountDTO accountDTO) {
        Optional<Account> userOptional = accountRepository.findById(username);
        return userOptional.map(account -> {
            account.setUsername(accountDTO.getUsername());
            account.setRole(accountDTO.getRole());
            return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Account> deleteAccount(String username) {
        Optional<Account> accountOptional = accountRepository.findById(username);
        return accountOptional.map(account -> {
            accountRepository.delete(account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
