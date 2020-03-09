package com.physch.game.repositories;

import com.physch.game.model.GameMode;
import com.physch.game.model.Question;
import org.hibernate.hql.internal.ast.tree.FromElement;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.From;

@Repository
public interface QuestionRepository  extends JpaRepository<Question, Long> {
    //jpa provides its own query language- abstracts out the difference
    //JPA- ORM ,so we can code db stuff in java
    // abstracts out the difference between DB backends

    @Query(value = "select * from questions where gameMode=:gameMode order by RAND() LIMIT 1",nativeQuery = true)
    Question getRandomQuestions(GameMode gameMode);

}
