package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.AccountDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.ContestantDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.ContestantMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Account;
import com.ifi.fresher_test.ifi_fresher_test.model.Contestant;
import com.ifi.fresher_test.ifi_fresher_test.repository.AccountRepository;
import com.ifi.fresher_test.ifi_fresher_test.repository.ContestantRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class ContestantService {
    ContestantRepository contestantRepository;

    @Autowired
    public void setContestantRepository(ContestantRepository contestantRepository) {
        this.contestantRepository = contestantRepository;
    }

    AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<ContestantDTO> findAll() {
        return ContestantMapper.arrayEntityToDTO(contestantRepository.findAll());
    }

    public ResponseEntity<ContestantDTO> findContestantByUsername(String username) {
        Optional<Contestant> optionalAccount = contestantRepository.findById(username);
        return optionalAccount.map(contestant -> new ResponseEntity<>(
                ContestantMapper.entityToDTO(contestant), HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> addContestant(ContestantDTO contestantDTO) {
        try {
            if (!contestantRepository.findById(contestantDTO.getUsername()).isPresent()) {
                Contestant contestant = ContestantMapper.dtoToEntity(contestantDTO);
                contestantRepository.save(contestant);
                return new ResponseEntity<ContestantDTO>(
                        new ContestantDTO(
                                contestant.getUsername(),
                                contestant.getFullName()
                        ), HttpStatus.CREATED
                );
            } else {
                return new ResponseEntity<String>(MessageResource.CONTESTANT_ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>(MessageResource.ACCOUNT_NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ContestantDTO> updateContestant(String username, ContestantDTO contestantDTO) {
        Optional<Contestant> contestantOptional = contestantRepository.findById(username);
        return contestantOptional.map(contestant -> {
            contestant.setFullName(contestantDTO.getFullName());
            contestantRepository.save(contestant);
            return new ResponseEntity<>(new ContestantDTO(
                    contestant.getUsername(),
                    contestant.getFullName()
            ), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<ContestantDTO> deleteContestant(String username) {
        Optional<Contestant> consultantOptional = contestantRepository.findById(username);
        return consultantOptional.map(contestant -> {
            contestantRepository.delete(contestant);
            accountService.deleteAccount(contestant.getUsername());
            return new ResponseEntity<>(new ContestantDTO(
                    contestant.getUsername(),
                    contestant.getFullName()
            ), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
