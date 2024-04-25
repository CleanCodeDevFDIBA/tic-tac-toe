package com.fdibaProject.tictactoe.repositories;


import com.fdibaProject.tictactoe.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
