package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerID;

    private String content;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "question_id")
    private Integer questionID;

    public Answer(String content, Boolean isCorrect, Boolean isDeleted, Integer questionID) {
        this.content = content;
        this.isCorrect = isCorrect;
        this.isDeleted = isDeleted;
        this.questionID = questionID;
    }
}
