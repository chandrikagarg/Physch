package com.physch.game.controller;

import com.physch.game.repositories.*;
import com.physch.game.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dev-test")
public class DevTestController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private ContentWriterRepository contentWriterRepository;

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/")
    public String hello() {
        return "Hello, World!";

    }

    @GetMapping("/populate")
    public String populateDB()
    {
        for(Player player:playerRepository.findAll())
        {
            player.getGames().clear();
            playerRepository.save(player);
        }
        gamesRepository.deleteAll();
        playerRepository.deleteAll();
        questionRepository.deleteAll();
        Player luffy = new Player.Builder()
                .alias("Dhoni")
                .email("dhoni@gmail.com")
                .saltedHashedPassword("akj")
                .build();
        Set<Game> x=luffy.getGames(); //collection->lazy
        x.iterator();
        playerRepository.save(luffy);
        Player robin = new Player.Builder()
                .alias("Sachin")
                .email("sachin@gmail.com")
                .saltedHashedPassword("ae")
                .build();
        playerRepository.save(robin);

        Game game = new Game();
        game.setGameMode(GameMode.IS_THIS_A_FACT);
        game.setLeader(luffy);
        game.getPlayers().add(luffy);
        gamesRepository.save(game);

        questionRepository.save(new Question(
                "WHat is the name",
                "chandrika",
                GameMode.IS_THIS_A_FACT
        ));

        return "populated";
    }

    @GetMapping("/questions")
    public List<Question>getAllQuestions()
        {

            return questionRepository.findAll();
        }
    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable(name="id") Long id)
    {
        return questionRepository.findById(id).orElseThrow();

    }

    @GetMapping("/players")
    public List<Player>getAllPlayers()
    {

        return playerRepository.findAll();
    }
    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable(name="id") Long id)
    {
        return playerRepository.findById(id).orElseThrow();

    }
    @GetMapping("/admins")
    public List<Admin>getAllAdmins()
    {

        return adminRepository.findAll();
    }
    @GetMapping("/admins/{id}")
    public Admin getAdminById(@PathVariable(name="id") Long id)
    {
        return adminRepository.findById(id).orElseThrow();

    }
    @GetMapping("/games")
    public List<Game>getAllGames()
    {

        return gamesRepository.findAll();
    }
    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable(name="id") Long id)
    {
        return gamesRepository.findById(id).orElseThrow();

    }
    @GetMapping("/rounds")
    public List<Round>getAllRounds()
    {

        return roundRepository.findAll();
    }
    @GetMapping("/rounds/{id}")
    public Round getRoundById(@PathVariable(name="id") Long id)
    {
        return roundRepository.findById(id).orElseThrow();

    }
    @GetMapping("/contentWriters")
    public List<ContentWriter>getAllContentWriters()
    {

        return contentWriterRepository.findAll();
    }
    @GetMapping("/contentWriter/{id}")
    public ContentWriter getContentWriterById(@PathVariable(name="id") Long id)
    {
        return contentWriterRepository.findById(id).orElseThrow();

    }

    @GetMapping("/users")
    public List<User>getAllUsers()
    {

        return userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(name="id") Long id)
    {
        return userRepository.findById(id).orElseThrow();

    }
    }
