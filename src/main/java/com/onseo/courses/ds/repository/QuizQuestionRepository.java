package com.onseo.courses.ds.repository;

import com.onseo.courses.ds.quiz.QuizQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends CrudRepository<QuizQuestion, String> {
    List<QuizQuestion> findAllByQuestionIdIn(List<String> ids);
}
