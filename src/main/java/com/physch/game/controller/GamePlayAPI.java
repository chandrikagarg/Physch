package com.physch.game.controller;
import com.physch.game.model.GameMode;
import com.physch.game.model.Player;
import com.physch.game.repositories.GameModeRepository;
import com.physch.game.repositories.PlayerRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
public class GamePlayAPI {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameModeRepository gameModeRepository;

    private Player getCurrentPlayer(Authentication authentication)
    {
        return playerRepository.findByEmail(authentication.getName()).orElseThrow();
    }
    @GetMapping("")
    public JSONObject play(Authentication authentication)
    {
        Player player = getCurrentPlayer(authentication);
        JSONObject response = new JSONObject();
//        obj.put("hello","world");
//        obj.put("name", "chandrika");
        response.put("playerAlias", player.getAlias());

        JSONArray gameModes = new JSONArray();
        for(GameMode mode:gameModeRepository.findAll())
        {
            JSONObject gameMode = new JSONObject();
            gameMode.put("title",mode.getName());
            gameMode.put("image",mode.getPicture());
            gameMode.put("description",mode.getDescription());
            gameModes.add(gameMode);
        }
        response.put("gameModes",gameModes);
        return response;

    }


}