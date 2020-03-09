package com.physch.game.controller;

import com.physch.game.exceptions.InvalidGameActionException;
import com.physch.game.model.Game;
import com.physch.game.model.Player;
import com.physch.game.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
public class GamePlayController {
    @Autowired
    private PlayerRepository playerRepository;
    @GetMapping("/")
    public String play(Authentication authentication) {
        return authentication.getName();
    }

    //POST param:request body
    //GET PARAM: something/something?param=value
    //URL param: /something/value

    @GetMapping("/submit-answer/{answer}")
    public void submitAnswer(Authentication authentication,@PathVariable(name = "answer") String answer) throws InvalidGameActionException{
        Player player=playerRepository.findByEmail(authentication.getName()).orElseThrow();
        player.getCurrentGame().submitAnswer(player,answer);


    }

}