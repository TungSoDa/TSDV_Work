package com.ifi.fresher_test.ifi_fresher_test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    @JsonIgnore
    private Integer answerID;

    private String content;

    private Boolean isCorrect;

    @JsonIgnore
    private Boolean isDeleted;

    private Integer questionID;

    public AnswerDTO(Integer answerID, String content, Boolean isCorrect, Boolean isDeleted) {
        this.answerID = answerID;
        this.content = content;
        this.isCorrect = isCorrect;
        this.isDeleted = isDeleted;
    }
}
