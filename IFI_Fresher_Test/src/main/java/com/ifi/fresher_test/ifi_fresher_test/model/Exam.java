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
    @GeneratedValue
    @Column(name = "exam_id")
    private String examID;

    @Column(name = "contestant_id")
    private String contestantID;

    @Column(name = "test_mark")
    private Double testMark;

    @ManyToOne
    @JoinColumn
    private Contestant contestant;
}
