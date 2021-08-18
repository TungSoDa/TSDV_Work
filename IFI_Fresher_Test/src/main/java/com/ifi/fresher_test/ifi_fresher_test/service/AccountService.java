package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.AccountDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.AccountMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Account;
import com.ifi.fresher_test.ifi_fresher_test.repository.AccountRepository;
import com.ifi.fresher_test.ifi_fresher_test.model.AccountDetails;
import com.ifi.fresher_test.ifi_fresher_test.util.JwtUtils;
import com.ifi.fresher_test.ifi_fresher_test.jwt.JwtResponse;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    JwtUtils jwtUtils;

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    public List<AccountDTO> findAll() {
        logger.info(MessageResource.SHOW_ALL_ACCOUNT);
        return AccountMapper.arrayEntityToDTO(accountRepository.findAll());
    }

    public ResponseEntity<?> findAccountByUsername(String username) {
        Optional<Account> optionalAccount = accountRepository.findById(username);
        if (optionalAccount.isPresent()) {
            logger.info(MessageResource.SHOW_ACCOUNT_BY_USERNAME + " " + username);
            return optionalAccount.map(account -> new ResponseEntity<AccountDTO>(
                    AccountMapper.entityToDTO(account), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> login(Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(account.getUsername());
        if (optionalAccount.isPresent()) {
            if (passwordEncoder.matches(account.getPassword(), optionalAccount.get().getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword())
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
                List<String> roles = accountDetails.getAuthorities()
                        .stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                logger.info(MessageResource.LOGIN_SUCCESS);
                return ResponseEntity.ok(new JwtResponse(jwt, accountDetails.getUsername(), roles));
            } else {
                logger.error(MessageResource.WRONG_PASSWORD);
                return new ResponseEntity<String>(MessageResource.WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
            }
        } else {
            logger.error(MessageResource.WRONG_USERNAME);
            return new ResponseEntity<String>(MessageResource.WRONG_USERNAME, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> addAccount(Account account) {
        if (!accountRepository.existsById(account.getUsername())) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
            logger.info(MessageResource.ADD_ACCOUNT_SUCCESSFUL);
            return new ResponseEntity<AccountDTO>(AccountMapper.entityToDTO(account), HttpStatus.CREATED);
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.ALREADY_EXISTS);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        }
    }

    public ResponseEntity<?> updateAccount(String username, AccountDTO accountDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(username);
        if (optionalAccount.isPresent()) {
            return optionalAccount.map(account -> {
                account.setRole(accountDTO.getRole());
                accountRepository.save(account);
                logger.info(MessageResource.EDIT_ACCOUNT_SUCCESSFUL);
                return new ResponseEntity<AccountDTO>(AccountMapper.entityToDTO(account), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteAccount(String username) {
        Optional<Account> optionalAccount = accountRepository.findById(username);
        if (optionalAccount.isPresent()) {
            return optionalAccount.map(account -> {
                accountRepository.delete(account);
                logger.info(MessageResource.DELETE_ACCOUNT_SUCCESSFUL);
                return new ResponseEntity<AccountDTO>(AccountMapper.entityToDTO(account), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }
}
