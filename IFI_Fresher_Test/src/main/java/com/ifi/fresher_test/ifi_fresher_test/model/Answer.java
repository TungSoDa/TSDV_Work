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
    @GeneratedValue
    @Column(name = "answer_id")
    private Integer answerID;

    private String content;

    private Boolean isCorrect;

    private Boolean isDeleted;

    @Column(name = "question_id")
    private Integer questionID;

    @ManyToOne
    @JoinColumn
    private Question question;
}
