package com.ifi.fresher_test.ifi_fresher_test.controller.api;

import com.ifi.fresher_test.ifi_fresher_test.dto.AccountDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.ContestantDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Account;
import com.ifi.fresher_test.ifi_fresher_test.service.ContestantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contestant")
public class ContestantController {
    ContestantService contestantService;

    @Autowired
    public void setContestantService(ContestantService contestantService) {
        this.contestantService = contestantService;
    }

    @GetMapping("/all")
    public List<ContestantDTO> findAll() {
        return contestantService.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<ContestantDTO> findContestantByUsername(@PathVariable String username) {
        return contestantService.findContestantByUsername(username);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addContestant(@RequestBody ContestantDTO contestantDTO) {
        return contestantService.addContestant(contestantDTO);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<ContestantDTO> updateAccount(@PathVariable String username, @RequestBody ContestantDTO contestantDTO) {
        return contestantService.updateContestant(username, contestantDTO);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ContestantDTO> deleteAccount(@PathVariable String username) {
        return contestantService.deleteContestant(username);
    }
}
