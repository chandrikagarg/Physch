package com.physch.game.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="playeranswers")
public class PlayerAnswer extends Auditable {

    @NotNull
    @ManyToOne
    @JsonBackReference
    @Getter
    @Setter
    private Round round;

    @NotNull
    @JsonIdentityReference
    @ManyToOne
    @Getter
    @Setter
    private Player player;

    @NotBlank
    @Getter
    @Setter
    private String answer;

    public PlayerAnswer() {
    }
    public PlayerAnswer(@NotNull Round round, @NotNull Player player, @NotBlank String answer) {
        this.round = round;
        this.player = player;
        this.answer = answer;
    }

}
