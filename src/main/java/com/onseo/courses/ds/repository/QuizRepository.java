package com.onseo.courses.ds.repository;

import com.onseo.courses.ds.quiz.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, String> {
    Quiz findByQuizID(String id);
}
