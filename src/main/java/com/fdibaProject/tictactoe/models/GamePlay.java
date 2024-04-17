package com.fdibaProject.tictactoe.models;

import lombok.Data;

@Data
public class GamePlay {
    private Signs type;
    private Integer coordinateX;
    private Integer coordinateY;
    private String gameId;
}
