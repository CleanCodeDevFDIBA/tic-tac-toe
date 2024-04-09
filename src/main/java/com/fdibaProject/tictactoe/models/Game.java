package com.fdibaProject.tictactoe.models;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

//@Entity
//@Table(name="GAMES")
@Data
public class Game {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String gameId;
//
//    @OneToMany
//    @JoinColumn(name = "player1", referencedColumnName = "id")
//    @Nullable
//    @Column(name = "player1")
//    private Player player1;
//
//    @OneToMany
//    @JoinColumn(name = "player2", referencedColumnName = "id")
//    @Nullable
//    @Column(name = "player2")
//    private Player player2;
////    private GameStatus status;
//    @Nullable
//    @Column(name = "date")
//    private String date;
////    private Map<Integer, List<Integer>> moves;
////    private int [][] board;
//    @Nullable
//    @Column(name = "winnerId")
//    private int winnerId;
//
//    @Nullable
//    private String moves;

    private String gameId;
    private Player player1;
    private Player player2;
    private GameStatus status;
    private int [][] board;
    private Signs winner;
}
