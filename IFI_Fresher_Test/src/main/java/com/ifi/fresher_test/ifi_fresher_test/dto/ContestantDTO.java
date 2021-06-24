package com.ifi.fresher_test.ifi_fresher_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContestantDTO {
    private String username;

    private String fullName;

    public ContestantDTO(String username) {
        this.username = username;
    }
}
