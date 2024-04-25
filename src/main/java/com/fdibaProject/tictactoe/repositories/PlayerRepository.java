package com.fdibaProject.tictactoe.repositories;


import com.fdibaProject.tictactoe.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
