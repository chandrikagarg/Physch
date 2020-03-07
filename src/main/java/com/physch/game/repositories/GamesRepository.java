package com.physch.game.repositories;

import com.physch.game.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface GamesRepository  extends JpaRepository<Game, Long> {

    }
