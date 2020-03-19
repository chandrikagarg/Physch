package com.physch.game.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.physch.game.exceptions.InvalidGameActionException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.text.html.InlineView;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="rounds")
public class Round extends Auditable {



    @ManyToOne
    @JsonBackReference
    @Getter
    @Setter
    @NotNull
    private Game game;

    @ManyToOne
    @JsonIdentityReference
    @NotNull
    @Getter
    @Setter
    private Question question;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @Getter
    @Setter
    private Map<Player,PlayerAnswer> submittedAnswers = new HashMap<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @Getter
    @Setter
    private Map<Player,PlayerAnswer> selectedAnswers = new HashMap<>();

    @ManyToOne
    @JsonIdentityReference
    @Getter
    @Setter
    private EllenAnswer ellenanswer;


    @NotNull
    @Getter
    @Setter
    private int roundNumber;

    public Round() {}

    public Round(@NotNull Game game, @NotNull Question question, @NotNull int roundNumber) {
        this.game = game;
        this.question = question;
        this.roundNumber = roundNumber;
    }

//    public Round(@NotNull Game game, @NotNull Question question, EllenAnswer ellenanswer, @NotNull int roundNumber) {
//        this.game = game;
//        this.question = question;
//        this.ellenanswer = ellenanswer;
//        this.roundNumber = roundNumber;
//    }

    public void submitAnswer(Player player, String answer) throws InvalidGameActionException{
        if(submittedAnswers.containsKey(player))
            throw  new InvalidGameActionException("Player has already submitted the answer for this round");
        for(PlayerAnswer existingaAnswer:submittedAnswers.values())
        {
            if(answer.equals(existingaAnswer.getAnswer()))
                throw new InvalidGameActionException("Duplicate Answer");
            submittedAnswers.put(player,new PlayerAnswer(this,player,answer));
        }
    }
    public boolean allAnswersSubmitted(int numPlayers) {

    return submittedAnswers.size()==numPlayers;
    }
    public void selectAnswer(Player player,PlayerAnswer selectedAnswer) throws InvalidGameActionException
    {
        if(selectedAnswers.containsKey(player))
            throw new InvalidGameActionException("Player has already selected the answer for this round");
        if(selectedAnswer.getPlayer().equals(player))
            throw new InvalidGameActionException("Can't select your own answer");
        if(!selectedAnswer.getRound().equals(this))
            throw new InvalidGameActionException("No such answer was submitted in this round");
        selectedAnswers.put(player,selectedAnswer);

    }
    public boolean allAnswersSelected(int numPlayers)
    {
        return selectedAnswers.size()==numPlayers;
    }

}
