package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.ContributorDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.ContributorMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Contributor;
import com.ifi.fresher_test.ifi_fresher_test.repository.ContributorRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
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

    public List<ContributorDTO> findAll() {
        return ContributorMapper.arrayEntityToDTO(contributorRepository.findAll());
    }

    public ResponseEntity<ContributorDTO> findContributorByUsername(String username) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(username);
        return optionalContributor.map(contributor -> new ResponseEntity<>(
                ContributorMapper.entityToDTO(contributor), HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> addContributor(ContributorDTO contributorDTO) {
        try {
            if (!contributorRepository.findById(contributorDTO.getUsername()).isPresent()) {
                if (accountService.accountRepository.findById(contributorDTO.getUsername()).get().getRole().equals(MessageResource.CONTRIBUTOR)) {
                    Contributor contributor = ContributorMapper.dtoToEntity(contributorDTO);
                    contributorRepository.save(contributor);
                    return new ResponseEntity<ContributorDTO>(
                            new ContributorDTO(
                                    contributor.getUsername(),
                                    contributor.getFullName()
                            ), HttpStatus.CREATED
                    );
                } else {
                    return new ResponseEntity<String>(MessageResource.CONTESTANT_ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
                }
            } else {
                return new ResponseEntity<String>(MessageResource.CONTRIBUTOR_ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>(MessageResource.ACCOUNT_NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateContributor(String username, ContributorDTO contributorDTO) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(username);
        if (optionalContributor.isPresent()) {
            return optionalContributor.map(contributor -> {
                contributor.setFullName(contributorDTO.getFullName());
                contributorRepository.save(contributor);
                return new ResponseEntity<ContributorDTO>(new ContributorDTO(
                        contributor.getUsername(),
                        contributor.getFullName()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.ACCOUNT_NOT_CREATED_YET + " or " + MessageResource.CONTRIBUTOR_NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteContributor(String username) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(username);
        if (optionalContributor.isPresent()) {
            return optionalContributor.map(contributor -> {
                contributorRepository.delete(contributor);
                accountService.deleteAccount(contributor.getUsername());
                return new ResponseEntity<ContributorDTO>(new ContributorDTO(
                        contributor.getUsername(),
                        contributor.getFullName()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.ACCOUNT_NOT_CREATED_YET + " or " + MessageResource.CONTRIBUTOR_NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }

    }
}
