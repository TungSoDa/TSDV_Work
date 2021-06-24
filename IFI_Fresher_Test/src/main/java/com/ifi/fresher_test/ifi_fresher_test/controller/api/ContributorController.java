package com.ifi.fresher_test.ifi_fresher_test.controller.api;

import com.ifi.fresher_test.ifi_fresher_test.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contributor")
public class ContributorController {
    ContributorService contributorService;

    @Autowired
    public void setContributorService(ContributorService contributorService) {
        this.contributorService = contributorService;
    }
}
