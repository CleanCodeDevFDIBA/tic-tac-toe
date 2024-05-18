package com.fdibaProject.tictactoe.controllers;


import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("Players: " + player1+ " " + player2 +"\nWinner: " + winner);
        game = gameService.createGame(player1, player2, winner);
        System.out.println("Game ID: " + game.getId());
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }
//
//    @PostMapping("/announceWinner")
//    public ResponseEntity<Game> announceWinner(@RequestBody Game game) {
//        System.out.println(game);
//        String winner_id = game.getWinner();
//        Long game_id = game.getId();
//        System.out.println(winner_id + " " + game_id);
//        game = gameService.mark_winner(winner_id, game_id);
//        return new ResponseEntity<>(game, HttpStatus.OK);
//    }
//
}
