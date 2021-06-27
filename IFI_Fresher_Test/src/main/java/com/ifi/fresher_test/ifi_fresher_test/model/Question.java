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
    private Integer questionID;

    private String content;

    private String image;

    private String topic;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "contributor_id")
    private String contributorID;

//    @ManyToOne
//    @JoinColumn
//    private Contributor contributor;
//
//    @OneToMany
//    @JoinColumn
//    private List<Answer> answerList = new ArrayList<>();


    public Question(Integer questionID, String content, String topic, Boolean isDeleted, String contributorID) {
        this.questionID = questionID;
        this.content = content;
        this.image = null;
        this.topic = topic;
        this.isDeleted = isDeleted;
        this.contributorID = contributorID;
    }
}
