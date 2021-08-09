package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.ContributorDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.ContributorMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Contributor;
import com.ifi.fresher_test.ifi_fresher_test.repository.ContributorRepository;
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
public class ContributorService {
    ContributorRepository contributorRepository;

    @Autowired
    public void setContributorRepository(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }

    AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public static final Logger logger = LoggerFactory.getLogger(ContributorService.class);

    public List<ContributorDTO> findAll() {
        logger.info(MessageResource.SHOW_ALL_CONTRIBUTOR);
        return ContributorMapper.arrayEntityToDTO(contributorRepository.findAll());
    }

    public ResponseEntity<?> findContributorByUsername(String username) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(username);
        if (optionalContributor.isPresent()) {
            logger.info(MessageResource.SHOW_CONTRIBUTOR_BY_USERNAME + " " + username);
            return optionalContributor.map(contributor -> new ResponseEntity<ContributorDTO>(
                    ContributorMapper.entityToDTO(contributor), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " OR " + MessageResource.CONTRIBUTOR + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " OR " + MessageResource.CONTRIBUTOR + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addContributor(ContributorDTO contributorDTO) {
        try {
            if (!contributorRepository.findById(contributorDTO.getUsername()).isPresent()) {
                if (accountService.accountRepository.findById(contributorDTO.getUsername()).get().getRole().equals(MessageResource.CONTRIBUTOR)) {
                    Contributor contributor = ContributorMapper.dtoToEntity(contributorDTO);
                    contributorRepository.save(contributor);
                    logger.info(MessageResource.ADD_CONTRIBUTOR_SUCCESSFUL);
                    return new ResponseEntity<ContributorDTO>(
                            new ContributorDTO(
                                    contributor.getUsername(),
                                    contributor.getFullName()
                            ), HttpStatus.CREATED
                    );
                } else {
                    logger.error(MessageResource.CONTESTANT + " " + MessageResource.ALREADY_EXISTS);
                    return new ResponseEntity<String>(MessageResource.CONTESTANT + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
                }
            } else {
                logger.error(MessageResource.CONTRIBUTOR + " " + MessageResource.ALREADY_EXISTS);
                return new ResponseEntity<String>(MessageResource.CONTRIBUTOR + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateContributor(String username, ContributorDTO contributorDTO) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(username);
        if (optionalContributor.isPresent()) {
            return optionalContributor.map(contributor -> {
                contributor.setFullName(contributorDTO.getFullName());
                contributorRepository.save(contributor);
                logger.info(MessageResource.EDIT_CONTRIBUTOR_SUCCESSFUL);
                return new ResponseEntity<ContributorDTO>(new ContributorDTO(
                        contributor.getUsername(),
                        contributor.getFullName()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " OR " + MessageResource.CONTRIBUTOR + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " OR " + MessageResource.CONTRIBUTOR + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteContributor(String username) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(username);
        if (optionalContributor.isPresent()) {
            return optionalContributor.map(contributor -> {
                contributorRepository.delete(contributor);
                logger.info(MessageResource.DELETE_CONTRIBUTOR_SUCCESSFUL);
                accountService.deleteAccount(contributor.getUsername());
                return new ResponseEntity<ContributorDTO>(new ContributorDTO(
                        contributor.getUsername(),
                        contributor.getFullName()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " OR " + MessageResource.CONTRIBUTOR + " " + MessageResource.NOT_CREATED_YET);
            return new ResponseEntity<String>(MessageResource.ACCOUNT + " " + MessageResource.NOT_CREATED_YET + " OR " + MessageResource.CONTRIBUTOR + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }

    }
}
