package com.physch.game.repositories;

import com.physch.game.model.EllenAnswer;
import com.physch.game.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
    public interface EllenAnswerRepository  extends JpaRepository<EllenAnswer, Long> {
    @Query(value = "SELECT * FROM ellenanswers where question = :question ORDER BY RAND() LIMIT 1", nativeQuery = true)
    EllenAnswer getRandomAnswer(Question question);

}

