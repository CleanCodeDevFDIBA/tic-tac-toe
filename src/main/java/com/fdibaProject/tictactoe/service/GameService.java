package com.fdibaProject.tictactoe.service;


import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.entities.Player;
import com.fdibaProject.tictactoe.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game createGame(String username1, String username2, String winner) {
        Game game = new Game();
        game.setPlayer1(username1);
        game.setPlayer2(username2);
        game.setWinner(winner);
        return saveGame(game);
    }

    private void addRankings(List<Player> scoreboard){
        int rank = 1;
        scoreboard.get(0).ranking = rank;

        for (int i = 1; i < scoreboard.size(); i++) {
            if(scoreboard.get(i).getWins() != scoreboard.get(i-1).getWins()){
                rank = i + 1;
            }
            scoreboard.get(i).ranking = rank;
        }
    }

    private List<Player> sortScoreboard(Map<String, Integer> winsPerPlayerMap){
        List<Player> sortedScoreboard = new ArrayList<>();


        for (Map.Entry<String, Integer> entry : winsPerPlayerMap.entrySet()){
            Player player = new Player(entry.getValue(), entry.getKey() );
            sortedScoreboard.add(player);
        }

        sortedScoreboard.sort((p1, p2) -> Integer.compare(p2.wins, p1.wins));

        return sortedScoreboard;
    }


    public List<Player> getScoreboard(){
        List<Game> games = getAllGames();
        Map<String, Integer> winsPerPlayer = new HashMap<>();
        List<Player> scoreboard;


        for (Game game:
             games) {
            String player1 = game.getPlayer1();
            String player2 = game.getPlayer2();
            String winner = game.getWinner();


            if  (!winsPerPlayer.containsKey(player1)){
                winsPerPlayer.put(player1, 0);
            }

            if  (!winsPerPlayer.containsKey(player2)){
                winsPerPlayer.put(player2, 0);
            }

            if (winner == null){
                continue;
            }

            if (winner.equals(player1)) {
                int newValue = winsPerPlayer.get(player1) + 1;
                winsPerPlayer.put(player1, newValue);
            }
            else {
                int newValue = winsPerPlayer.get(player2) + 1;
                winsPerPlayer.put(player2, newValue);
            }

        }

        scoreboard = sortScoreboard(winsPerPlayer);
        addRankings(scoreboard);

        return scoreboard;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }


}
