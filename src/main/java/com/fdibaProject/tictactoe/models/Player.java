package com.fdibaProject.tictactoe.models;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

//@Entity
//@Table(name="PLAYERS")
@Data
public class Player {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name", unique = true)
//    @Nullable
//    private String name;
//
//    @Column(name = "games_played")
//    @Nullable
//    public int games_played;
//
//    @Column(name = "games_won")
//    @Nullable
//    public int games_won;

    private String username;
}
