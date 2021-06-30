package com.ifi.fresher_test.ifi_fresher_test.repository;

import com.ifi.fresher_test.ifi_fresher_test.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    public Optional<List<Exam>> findAllByIsDeletedFalse();

    public Optional<Exam> findExamByExamIDAndIsDeletedFalse(Integer id);

    public Optional<Exam> findExamByListQuestionIDAndIsDeletedFalse(String listQuestionID);

    public Optional<Exam> findExamByNameAndIsDeletedFalse(String name);

    public Optional<Exam> findExamByName(String name);

    public Optional<List<Exam>> findExamsByIsDeletedTrue();
}
