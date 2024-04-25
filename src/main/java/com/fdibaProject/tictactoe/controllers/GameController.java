package com.fdibaProject.tictactoe.controllers;

import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.exceptions.InvalidMoveException;
import com.fdibaProject.tictactoe.exceptions.ResourceNotFoundException;
import com.fdibaProject.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    @Autowired
    private GameService gameService;

    // Get all games
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    // Get game by ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    // Create a new game
    @PostMapping("/createGame")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        game = gameService.createGame();
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<Game> makeMove(@PathVariable Long id, @RequestBody MoveRequest moveRequest) {
        try {
            Game updatedGame = gameService.updateGame(id, moveRequest.getIndex(), moveRequest.getPlayer());
            return new ResponseEntity<>(updatedGame, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidMoveException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // Delete a game
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
