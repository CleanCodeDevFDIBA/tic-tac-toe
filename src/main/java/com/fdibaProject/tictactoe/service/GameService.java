package com.fdibaProject.tictactoe.service;


import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import static com.fdibaProject.tictactoe.entities.GameStatus.*;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game createGame(String username1, String username2) {
        Game game = new Game();
        game.setStatus(IN_PROGRESS);
        game.setPlayer1(username1);
        game.setPlayer2(username2);
        return saveGame(game);
    }

    public Game mark_winner(String winner, Long id){
        Game game = getGameById(id);
        if(winner != null) {
            game.setWinner(winner);
        }
        game.setStatus(FINISHED);
        return saveGame(game);
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

}
