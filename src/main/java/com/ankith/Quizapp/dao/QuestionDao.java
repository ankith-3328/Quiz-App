package com.ankith.Quizapp.dao;

import com.ankith.Quizapp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q WHERE q.category = :category")
    List<Question> findByCategory(@Param("category") String category);

    @Query("SELECT q FROM Question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ")
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
