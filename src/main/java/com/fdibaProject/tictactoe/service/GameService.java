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

    public Game updateGame(Long id, int index, Player player) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));

        if (game.getBoard().charAt(index) != '-') {
            throw new InvalidMoveException("Invalid move");
        }

        char[] boardArray = game.getBoard().toCharArray();
        boardArray[index] = player.getSymbol();
        game.setBoard(String.valueOf(boardArray));

        if (checkWinner(boardArray, player)) {
            game.setStatus(FINISHED);
            game.setWinner(player);
        } else if (checkDraw(boardArray)) {
            game.setStatus(FINISHED);
        } else {
            if(game.getCurrentPlayer().getSymbol()=='X'){
                game.getCurrentPlayer().setSymbol('O');
            }
            else{
                game.getCurrentPlayer().setSymbol('O');
            }
        }

        return gameRepository.save(game);
    }


    private boolean checkWinner(char[] board, Player player) {
        char symbol = player.getSymbol();
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] condition : winConditions) {
            if (board[condition[0]] == symbol &&
                    board[condition[1]] == symbol &&
                    board[condition[2]] == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDraw(char[] board) {
        for (char c : board) {
            if (c == '-') {
                return false;
            }
        }
        return true;
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
