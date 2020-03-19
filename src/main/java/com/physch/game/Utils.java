package com.physch.game;

import com.physch.game.config.ApplicationContextProvider;
import com.physch.game.config.SpringConfiguration;
import com.physch.game.model.EllenAnswer;
import com.physch.game.model.GameMode;
import com.physch.game.model.Question;
import com.physch.game.repositories.EllenAnswerRepository;
import com.physch.game.repositories.QuestionRepository;
public class Utils {
    private static QuestionRepository questionRepository;
    private static EllenAnswerRepository ellenAnswerRepository;
    static {
        questionRepository=(QuestionRepository) ApplicationContextProvider
                .getApplicationContext()
                .getBean("questionRepository");
//        SpringConfiguration
//                .contextProvider();
        ellenAnswerRepository= (EllenAnswerRepository) ApplicationContextProvider
                .getApplicationContext()
                .getBean("ellenAnswerRepository");

    }
    public static Question getRandomQuestion(GameMode gameMode)
    {
       return  questionRepository.getRandomQuestions(gameMode);

    }

    public static EllenAnswer getRandomEllenAnswer(Question question)
    {
        return ellenAnswerRepository.getRandomAnswer(question);
    }
}
