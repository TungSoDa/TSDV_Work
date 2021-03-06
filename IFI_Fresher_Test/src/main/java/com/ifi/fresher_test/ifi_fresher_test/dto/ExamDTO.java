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
public class ExamDTO {
    private Integer examID;

    private String name;

    private String topic;

    private String listQuestionID;

    @JsonIgnore
    private Boolean isDeleted;

    private List<QuestionDTO> questionList;
}
