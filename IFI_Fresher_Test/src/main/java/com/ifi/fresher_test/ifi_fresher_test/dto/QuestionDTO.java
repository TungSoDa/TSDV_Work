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
public class QuestionDTO {
    @JsonIgnore
    private Integer questionID;

    private String content;

    private String image;

    private String topic;

    @JsonIgnore
    private Boolean isDeleted;

    private String contributorID;

    private List<AnswerDTO> answerList;

    public QuestionDTO(Integer questionID, String content, String image, String topic, Boolean isDeleted, String contributorID) {
        this.questionID = questionID;
        this.content = content;
        this.image = image;
        this.topic = topic;
        this.isDeleted = isDeleted;
        this.contributorID = contributorID;
    }

    public QuestionDTO(Integer questionID, String content, String topic, Boolean isDeleted, String contributorID) {
        this.questionID = questionID;
        this.content = content;
        this.image = null;
        this.topic = topic;
        this.isDeleted = isDeleted;
        this.contributorID = contributorID;
    }

    public QuestionDTO(Integer questionID, String content, String topic, Boolean isDeleted, String contributorID, List<AnswerDTO> answerList) {
        this.questionID = questionID;
        this.content = content;
        this.image = null;
        this.topic = topic;
        this.isDeleted = isDeleted;
        this.contributorID = contributorID;
        this.answerList = answerList;
    }
}
