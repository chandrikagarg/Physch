package com.physch.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="stats")
public class Stat extends Auditable{

    @Getter
    @Setter
    private long gotPhyschedCount=0;
    @Getter
    @Setter
    private long physchedOthersCount=0;
    @Getter
    @Setter
    private long correctAnswerCount=0;



}
