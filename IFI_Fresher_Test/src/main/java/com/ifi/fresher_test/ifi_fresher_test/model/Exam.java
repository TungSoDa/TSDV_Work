package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private String examID;

    @Column(name = "contestant_id")
    private String contestantID;

    @Column(name = "test_mark")
    private Double testMark;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

//    @ManyToOne
//    @JoinColumn
//    private Contestant contestant;


    public Exam(String examID, String contestantID, Boolean isDeleted) {
        this.examID = examID;
        this.contestantID = contestantID;
        this.testMark = 0.0;
        this.isDeleted = isDeleted;
    }
}
