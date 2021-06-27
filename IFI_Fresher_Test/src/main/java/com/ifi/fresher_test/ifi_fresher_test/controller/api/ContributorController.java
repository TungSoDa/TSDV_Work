package com.ifi.fresher_test.ifi_fresher_test.controller.api;

import com.ifi.fresher_test.ifi_fresher_test.dto.ContributorDTO;
import com.ifi.fresher_test.ifi_fresher_test.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contributor")
public class ContributorController {
    ContributorService contributorService;

    @Autowired
    public void setContributorService(ContributorService contributorService) {
        this.contributorService = contributorService;
    }

    @GetMapping("/all")
    public List<ContributorDTO> findAll() {
        return contributorService.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findContributorByUsername(@PathVariable String username) {
        return contributorService.findContributorByUsername(username);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addContributor(@RequestBody ContributorDTO contributorDTO) {
        return contributorService.addContributor(contributorDTO);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<?> updateContributor(@PathVariable String username, @RequestBody ContributorDTO contributorDTO) {
        return contributorService.updateContributor(username, contributorDTO);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteContributor(@PathVariable String username) {
        return contributorService.deleteContributor(username);
    }
}
