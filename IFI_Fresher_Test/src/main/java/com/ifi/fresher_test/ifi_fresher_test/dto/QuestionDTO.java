package com.ifi.fresher_test.ifi_fresher_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Integer questionID;

    private String content;

    private String image;

    private String topic;

    private Integer contributorID;

    public QuestionDTO(Integer questionID, String content, String topic, Integer contributorID) {
        this.questionID = questionID;
        this.content = content;
        this.image = null;
        this.topic = topic;
        this.contributorID = contributorID;
    }
}
