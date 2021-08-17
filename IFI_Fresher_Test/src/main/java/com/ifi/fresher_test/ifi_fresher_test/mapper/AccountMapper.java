package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.AccountDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {
    public static AccountDTO entityToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(account.getUsername());
        accountDTO.setActive(account.isActive());
        accountDTO.setRole(account.getRole());
        return accountDTO;
    }

    public static List<AccountDTO> arrayEntityToDTO(List<Account> accounts) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            accountDTOS.add(new AccountDTO(account.getUsername(), account.isActive(), account.getRole()));
        }
        return accountDTOS;
    }
}
