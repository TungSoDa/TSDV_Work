package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.repository.ContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributorService {
    ContributorRepository contributorRepository;

    @Autowired
    public void setContributorRepository(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }
}
