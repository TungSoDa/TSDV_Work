package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.ContributorDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Contributor;

import java.util.ArrayList;
import java.util.List;

public class ContributorMapper {
    public static ContributorDTO entityToDTO(Contributor contestant) {
        ContributorDTO contributorDTO = new ContributorDTO();
        contributorDTO.setUsername(contestant.getUsername());
        contributorDTO.setFullName(contestant.getFullName());
        return contributorDTO;
    }

    public static List<ContributorDTO> arrayEntityToDTO(List<Contributor> contestants) {
        List<ContributorDTO> contributorDTOS = new ArrayList<>();
        for (Contributor contestant : contestants) {
            contributorDTOS.add(new ContributorDTO(contestant.getUsername(), contestant.getFullName()));
        }
        return contributorDTOS;
    }

    public static Contributor dtoToEntity(ContributorDTO contributorDTO) {
        Contributor contributor = new Contributor();
        contributor.setUsername(contributorDTO.getUsername());
        contributor.setFullName(contributorDTO.getFullName());
        return contributor;
    }
}
