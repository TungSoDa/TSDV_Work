package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.ContestantDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.ContestantMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Contestant;
import com.ifi.fresher_test.ifi_fresher_test.repository.ContestantRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<?> findContestantByUsername(String username) {
        Optional<Contestant> optionalContestant = contestantRepository.findById(username);
        if (optionalContestant.isPresent()) {
            return optionalContestant.map(contestant -> new ResponseEntity<ContestantDTO>(
                    ContestantMapper.entityToDTO(contestant), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + username + " " + MessageResource.NOT_CREATED_YET + " or " + MessageResource.CONTESTANT + " " + username + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addContestant(ContestantDTO contestantDTO) {
        try {
            if (!contestantRepository.findById(contestantDTO.getUsername()).isPresent()) {
                if (accountService.accountRepository.findById(contestantDTO.getUsername()).get().getRole().equals(MessageResource.CONTESTANT)) {
                    Contestant contestant = ContestantMapper.dtoToEntity(contestantDTO);
                    contestantRepository.save(contestant);
                    return new ResponseEntity<ContestantDTO>(
                            new ContestantDTO(
                                    contestant.getUsername(),
                                    contestant.getFullName()
                            ), HttpStatus.CREATED
                    );
                } else {
                    return new ResponseEntity<String>(MessageResource.CONTRIBUTOR + " " + contestantDTO.getUsername() + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
                }
            } else {
                return new ResponseEntity<String>(MessageResource.CONTESTANT + " " + contestantDTO.getUsername() + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + contestantDTO.getUsername() + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateContestant(String username, ContestantDTO contestantDTO) {
        Optional<Contestant> optionalContestant = contestantRepository.findById(username);
        if (optionalContestant.isPresent()) {
            return optionalContestant.map(contestant -> {
                contestant.setFullName(contestantDTO.getFullName());
                contestantRepository.save(contestant);
                return new ResponseEntity<ContestantDTO>(new ContestantDTO(
                        contestant.getUsername(),
                        contestant.getFullName()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + username + " " + MessageResource.NOT_CREATED_YET + " or " + MessageResource.CONTESTANT + " " + username + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteContestant(String username) {
        Optional<Contestant> optionalContestant = contestantRepository.findById(username);
        if (optionalContestant.isPresent()) {
            return optionalContestant.map(contestant -> {
                contestantRepository.delete(contestant);
                accountService.deleteAccount(contestant.getUsername());
                return new ResponseEntity<ContestantDTO>(new ContestantDTO(
                        contestant.getUsername(),
                        contestant.getFullName()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + username + " " + MessageResource.NOT_CREATED_YET + " or " + MessageResource.CONTESTANT + " " + username + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }
}
