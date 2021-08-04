package com.ifi.fresher_test.ifi_fresher_test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Integer questionID;

    private String content;

    private String image;

    private String topic;

    @JsonIgnore
    private Boolean isDeleted;

    private List<AnswerDTO> answerList;

    public QuestionDTO(Integer questionID, String content, String image, String topic, Boolean isDeleted) {
        this.questionID = questionID;
        this.content = content;
        this.image = image;
        this.topic = topic;
        this.isDeleted = isDeleted;
    }
}
