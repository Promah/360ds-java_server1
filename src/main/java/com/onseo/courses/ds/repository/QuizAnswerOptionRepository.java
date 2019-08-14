package com.onseo.courses.ds.repository;

import com.onseo.courses.ds.quiz.QuizAnswerOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAnswerOptionRepository extends CrudRepository<QuizAnswerOption, String> {
    List<QuizAnswerOption> findAllByAnswerIdIn(List<String> ids);
    QuizAnswerOption findByAnswerId(String id);
}
