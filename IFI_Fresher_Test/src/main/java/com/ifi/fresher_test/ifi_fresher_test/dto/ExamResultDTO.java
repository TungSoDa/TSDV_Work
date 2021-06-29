package com.ifi.fresher_test.ifi_fresher_test.dto;

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
    private String examResultID;

    private String examID;

    private String examName;

    private String contestantID;

    private String testMark;

    private String topic;

    private Boolean isDeleted;

    private List<QuestionDTO> questionList;
}
