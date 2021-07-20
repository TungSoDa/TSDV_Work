package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exam_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_result_id")
    private Integer examResultID;

    @Column(name = "exam_id")
    private Integer examID;

    @Column(name = "contestant_username")
    private String contestantUsername;

    @Column(name = "test_mark")
    private Double testMark;

    @Column(name = "selected_answers")
    private String selectedAnswers;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
