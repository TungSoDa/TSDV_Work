package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    private String content;

    private String image;

    private String topic;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

//    @ManyToOne
//    @JoinColumn
//    private Contributor contributor;

//    @OneToMany
//    @JoinColumn
//    private List<Answer> answerList = new ArrayList<>();


    public Question(Integer questionId, String content, String topic, Boolean isDeleted) {
        this.questionId = questionId;
        this.content = content;
        this.image = null;
        this.topic = topic;
        this.isDeleted = isDeleted;
    }
}
