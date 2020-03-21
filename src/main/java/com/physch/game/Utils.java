package com.physch.game;

import com.physch.game.config.ApplicationContextProvider;
import com.physch.game.model.EllenAnswer;
import com.physch.game.model.GameMode;
import com.physch.game.model.Question;
import com.physch.game.repositories.EllenAnswerRepository;
import com.physch.game.repositories.QuestionRepository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    private static QuestionRepository questionRepository;
    private static EllenAnswerRepository ellenAnswerRepository;
    private static List<String> wordsList;
    private static Map<String,Integer> wordsIndices;

    static {
        questionRepository=(QuestionRepository) ApplicationContextProvider
                .getApplicationContext()
                .getBean("questionRepository");
//        SpringConfiguration
//                .contextProvider();
        ellenAnswerRepository= (EllenAnswerRepository) ApplicationContextProvider
                .getApplicationContext()
                .getBean("ellenAnswerRepository");

        try {
            BufferedReader br = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:data/words.txt")));
            String word;
            wordsList = new ArrayList<>();
            wordsIndices = new HashMap<>();
            int index=0;
            do {
                word = br.readLine();
                if(word==null) break;
                word = word.strip();
                wordsList.add(word);
                wordsIndices.put(word,index);
                index+=1;
            }while(word!=null);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Question getRandomQuestion(GameMode gameMode)
    {
       return  questionRepository.getRandomQuestions(gameMode.getId());

    }

    public static EllenAnswer getRandomEllenAnswer(Question question)
    {
        return ellenAnswerRepository.getRandomAnswer(question.getId());
    }

    public static String getSecretCodeFromGameId(Long id)
    {
        String code = "";
        int base = wordsList.size();
        while(id>0)
        {
            code = code + wordsList.get((int)(id%base));
            id /=base;

        }
        return code.substring(0,code.length()-1);
    }

    public static Long getGameIdFromSecretCode(String code)
    {
        String words[] = code.split(" ");
        long gameId =0;
        int base= wordsList.size();
        for(String word:words){
            gameId = gameId*base + wordsIndices.get(word);
        }
        return gameId;
    }

    public static  List<Pair<String,String>> readQAFile(String filename)
    {
        List<Pair<String,String>> questions= new ArrayList<>();
        int count =0;
        try {
                BufferedReader br = new BufferedReader(new FileReader(ResourceUtils.getFile(filename)));
                String questionText, correctAnswer;
                do {
                    questionText = br.readLine();
                    correctAnswer = br.readLine();
                    if(questionText == null || correctAnswer == null) break;
                    questions.add(new Pair<>(questionText.strip(), correctAnswer.strip()));
                    count++;
                } while (questionText != null && count <Constants.MAX_QUESTIONS_TO_READ);
            } catch(IOException ignored){}

        return questions;
        //return question answer pair
    }
}
