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
public class ExamDTO {
    private String examID;

    private String topic;

    private String listQuestionID;

    private Boolean isDeleted;

    private List<QuestionDTO> questionList;
}
