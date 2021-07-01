package com.ifi.fresher_test.ifi_fresher_test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam_contestant_option")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamOption {
    @Id
    @Column(name = "option_id")
    private String optionID;

    @Column(name = "selected_answer_id")
    private Integer selectedAnswerID;

    @Column(name = "exam_result_id")
    private Integer examResultID;
}
