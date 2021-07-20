package com.ifi.fresher_test.ifi_fresher_test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultDTO {
    private Integer examResultID;

    private Integer examID;

    private String contestantUsername;

    private Double testMark;

    private String selectedAnswers;

    @JsonIgnore
    private Boolean isDeleted;

    private List<QuestionDTO> questionList;

//    private List<ExamOptionDTO> optionList;
}
