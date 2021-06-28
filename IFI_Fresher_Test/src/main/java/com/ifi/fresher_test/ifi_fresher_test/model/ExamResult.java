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
    private String examResultID;

    @Column(name = "exam_id")
    private String examID;

    @Column(name = "contestant_id")
    private String contestantID;

    @Column(name = "test_mark")
    private String testMark;

    private String topic;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
