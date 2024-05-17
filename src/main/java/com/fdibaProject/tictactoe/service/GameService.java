package com.fdibaProject.tictactoe.service;


import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.entities.Player;
import com.fdibaProject.tictactoe.exceptions.InvalidMoveException;
import com.fdibaProject.tictactoe.exceptions.ResourceNotFoundException;
import com.fdibaProject.tictactoe.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import static com.fdibaProject.tictactoe.entities.GameStatus.FINISHED;
import static com.fdibaProject.tictactoe.entities.GameStatus.IN_PROGRESS;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game createGame() {
        Game game = new Game();
        game.setBoard("---------");
        game.setStatus(IN_PROGRESS);
        game.setPlayer1(new Player());
        game.getPlayer1().setSymbol('X');
        game.setPlayer2(new Player());
        game.getPlayer2().setSymbol('O');
        game.setCurrentPlayer(game.getPlayer1());
        return gameRepository.save(game);
    }


    public List<Game> getAllGames() {
        return gameRepository.findAll();

    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
