package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Integer questionID;

    private String content;

    private String image;

    private String topic;

    @Column(name = "contributor_id")
    private Integer contributorID;

    @ManyToOne
    @JoinColumn
    private Contributor contributor;

    @OneToMany
    @JoinColumn
    private List<Answer> answerList = new ArrayList<>();

    public Question(Integer questionID, String content, String topic, Integer contributorID) {
        this.questionID = questionID;
        this.content = content;
        this.image = null;
        this.topic = topic;
        this.contributorID = contributorID;
    }
}
