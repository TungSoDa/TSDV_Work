package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.ContestantDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.ContestantMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Contestant;
import com.ifi.fresher_test.ifi_fresher_test.repository.ContestantRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ContestantDTO> findAll() {
        return ContestantMapper.arrayEntityToDTO(contestantRepository.findAll());
    }

    public ResponseEntity<ContestantDTO> findContestantByUsername(String username) {
        Optional<Contestant> optionalAccount = contestantRepository.findById(username);
        return optionalAccount.map(contestant -> new ResponseEntity<>(
                ContestantMapper.entityToDTO(contestant), HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<ContestantDTO> addContestant(ContestantDTO contestantDTO) {
        if (!contestantRepository.findById(contestantDTO.getUsername()).isPresent()) {
            Contestant contestant = ContestantMapper.dtoToEntity(contestantDTO);
            contestantRepository.save(contestant);
            return new ResponseEntity<>(
                    new ContestantDTO(contestant.getFullName(), contestant.getFullName()), HttpStatus.CREATED
            );
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}
