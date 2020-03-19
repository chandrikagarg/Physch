package com.physch.game.repositories;

import com.physch.game.model.GameMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameModeRepository extends JpaRepository<GameMode,Long> {

    Optional<GameMode> findByName(String gameMode);
}
