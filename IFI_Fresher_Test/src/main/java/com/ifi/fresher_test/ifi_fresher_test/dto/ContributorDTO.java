package com.ifi.fresher_test.ifi_fresher_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContributorDTO {
    private String username;

    private String fullName;

    public ContributorDTO(String username) {
        this.username = username;
    }
}
