package com.ifi.fresher_test.ifi_fresher_test.repository;

import com.ifi.fresher_test.ifi_fresher_test.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public Optional<List<Question>> findQuestionByTopic(String topic);

    public Optional<Question> findQuestionByContentAndTopic(String content, String topic);
}
