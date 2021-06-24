package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.AccountDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {
    public static AccountDTO entityToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(account.getUsername());
        accountDTO.setRole(account.getRole());
        return accountDTO;
    }

    public static List<AccountDTO> arrayEntityToDTO(List<Account> accounts) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            accountDTOS.add(new AccountDTO(accounts.get(i).getUsername(), accounts.get(i).getRole()));
        }
        return accountDTOS;
    }
}
