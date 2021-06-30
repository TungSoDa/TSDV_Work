package com.ifi.fresher_test.ifi_fresher_test.repository;

import com.ifi.fresher_test.ifi_fresher_test.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    public Optional<List<Answer>> findAllByIsDeletedFalse();

    public Optional<Answer> findAnswersByAnswerIDAndIsDeletedFalse(Integer id);

    public Optional<Answer> findAnswersByContentAndQuestionIDAndIsDeletedFalse(String content, Integer questionID);

    public Optional<List<Answer>> findAnswersByQuestionIDAndIsDeletedFalse(Integer questionID);

    public Optional<Answer> findAnswersByQuestionIDAndIsCorrectTrueAndIsDeletedFalse(Integer questionID);

    public Optional<List<Answer>> findAnswersByIsDeletedTrue();
}
