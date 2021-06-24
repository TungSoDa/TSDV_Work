package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.ContestantDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Contestant;

import java.util.ArrayList;
import java.util.List;

public class ContestantMapper {
    public static ContestantDTO entityToDTO(Contestant contestant) {
        ContestantDTO contestantDTO = new ContestantDTO();
        contestantDTO.setUsername(contestant.getUsername());
        contestantDTO.setFullName(contestant.getFullName());
        return contestantDTO;
    }

    public static List<ContestantDTO> arrayEntityToDTO(List<Contestant> contestants) {
        List<ContestantDTO> contestantDTOS = new ArrayList<>();
        for (int i = 0; i < contestants.size(); i++) {
            contestantDTOS.add(new ContestantDTO(contestants.get(i).getUsername(), contestants.get(i).getFullName()));
        }
        return contestantDTOS;
    }

    public static Contestant dtoToEntity(ContestantDTO contestantDTO) {
        Contestant contestant = new Contestant();
        contestant.setUsername(contestantDTO.getUsername());
        contestant.setFullName(contestantDTO.getFullName());
        return contestant;
    }
}
