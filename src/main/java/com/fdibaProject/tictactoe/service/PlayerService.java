package com.fdibaProject.tictactoe.service;


import com.fdibaProject.tictactoe.entities.Player;
import com.fdibaProject.tictactoe.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
        @Autowired
        private PlayerRepository playerRepository;

        // CRUD methods for Player entity
        public List<Player> getAllPlayers() {
            return playerRepository.findAll();
        }

        public Player getPlayerById(Long id) {
            return playerRepository.findById(id).orElse(null);
        }

        public Player savePlayer(Player player) {
            return playerRepository.save(player);
        }

        public void deletePlayer(Long id) {
            playerRepository.deleteById(id);
        }

    }

