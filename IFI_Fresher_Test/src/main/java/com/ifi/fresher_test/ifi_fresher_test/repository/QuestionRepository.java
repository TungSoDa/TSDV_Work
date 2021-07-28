package com.ifi.fresher_test.ifi_fresher_test.repository;

import com.ifi.fresher_test.ifi_fresher_test.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public Optional<List<Question>> findAllByIsDeletedFalse();

    public Optional<Question> findQuestionByQuestionIdAndIsDeletedFalse(Integer id);

    public Optional<List<Question>> findQuestionByTopicAndIsDeletedFalse(String topic);

    public Optional<Question> findQuestionByContentAndTopicAndIsDeletedFalse(String content, String topic);

    public Optional<List<Question>> findQuestionsByIsDeletedTrue();

    public Optional<Question> findQuestionByQuestionIdAndIsDeletedTrue(Integer id);
}
