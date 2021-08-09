package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.ContestantDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.ContestantMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Contestant;
import com.ifi.fresher_test.ifi_fresher_test.repository.ContestantRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(ContestantService.class);

    public List<ContestantDTO> findAll() {
        logger.info(MessageResource.SHOW_ALL_CONTESTANT);
        return ContestantMapper.arrayEntityToDTO(contestantRepository.findAll());
    }

    public ResponseEntity<?> findContestantByUsername(String username) {
        Optional<Contestant> optionalContestant = contestantRepository.findById(username);
        if (optionalContestant.isPresent()) {
            logger.info(MessageResource.SHOW_CONTESTANT_BY_USERNAME + " " + username);
            return optionalContestant.map(contestant -> new ResponseEntity<ContestantDTO>(
                    ContestantMapper.entityToDTO(contestant), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " hoặc " + MessageResource.CONTESTANT + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " hoặc " + MessageResource.CONTESTANT + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addContestant(ContestantDTO contestantDTO) {
        try {
            if (!contestantRepository.findById(contestantDTO.getUsername()).isPresent()) {
                if (accountService.accountRepository.findById(contestantDTO.getUsername()).get().getRole().equals(MessageResource.CONTESTANT)) {
                    Contestant contestant = ContestantMapper.dtoToEntity(contestantDTO);
                    contestantRepository.save(contestant);
                    logger.info(MessageResource.ADD_CONTESTANT_SUCCESSFUL);
                    return new ResponseEntity<ContestantDTO>(
                            new ContestantDTO(
                                    contestant.getUsername(),
                                    contestant.getFullName()
                            ), HttpStatus.CREATED
                    );
                } else {
                    logger.error(MessageResource.CONTRIBUTOR + " " + MessageResource.ALREADY_EXISTS);
                    return new ResponseEntity<String>(MessageResource.CONTRIBUTOR + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
                }
            } else {
                logger.error(MessageResource.CONTESTANT + " " + MessageResource.ALREADY_EXISTS);
                return new ResponseEntity<String>(MessageResource.CONTESTANT + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateContestant(String username, ContestantDTO contestantDTO) {
        Optional<Contestant> optionalContestant = contestantRepository.findById(username);
        if (optionalContestant.isPresent()) {
            return optionalContestant.map(contestant -> {
                contestant.setFullName(contestantDTO.getFullName());
                contestantRepository.save(contestant);
                logger.info(MessageResource.EDIT_CONTESTANT_SUCCESSFUL);
                return new ResponseEntity<ContestantDTO>(new ContestantDTO(
                        contestant.getUsername(),
                        contestant.getFullName()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " hoặc " + MessageResource.CONTESTANT + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " hoặc " + MessageResource.CONTESTANT + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteContestant(String username) {
        Optional<Contestant> optionalContestant = contestantRepository.findById(username);
        if (optionalContestant.isPresent()) {
            return optionalContestant.map(contestant -> {
                contestantRepository.delete(contestant);
                logger.info(MessageResource.DELETE_CONTESTANT_SUCCESSFUL);
                accountService.deleteAccount(contestant.getUsername());
                return new ResponseEntity<ContestantDTO>(new ContestantDTO(
                        contestant.getUsername(),
                        contestant.getFullName()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " hoặc " + MessageResource.CONTESTANT + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " hoặc " + MessageResource.CONTESTANT + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }
}
