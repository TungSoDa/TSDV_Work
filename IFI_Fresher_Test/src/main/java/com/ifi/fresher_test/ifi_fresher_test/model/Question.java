package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    private String content;

    private String image;

    private String topic;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Question(Integer questionId, String content, String topic, Boolean isDeleted) {
        this.questionId = questionId;
        this.content = content;
        this.image = null;
        this.topic = topic;
        this.isDeleted = isDeleted;
    }
}
