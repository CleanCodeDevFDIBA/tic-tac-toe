package com.fdibaProject.tictactoe.controllers;


import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.entities.Player;
import com.fdibaProject.tictactoe.entities.Scoreboard;
import com.fdibaProject.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    @Autowired
    private GameService gameService;

    // Create a new game
    @PostMapping("/createGame")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        String player1 = game.getPlayer1();
        String player2 = game.getPlayer2();
        String winner = game.getWinner();

        if (player1 == null || player2 == null){
            return ResponseEntity.badRequest().build();
        }

        System.out.println("Players: " + player1+ " " + player2 +"\nWinner: " + winner);
        game = gameService.createGame(player1, player2, winner);
        System.out.println("Game ID: " + game.getId());
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @GetMapping("/scoreboard")
    public ResponseEntity<Scoreboard> getScoreboard(){
        List<Player> scoreboard = gameService.getScoreboard();
        Scoreboard sb = new Scoreboard();
        sb.setScoreboard(scoreboard);
        System.out.println(scoreboard);
        return new ResponseEntity<>(sb, HttpStatus.OK);
    }

}
