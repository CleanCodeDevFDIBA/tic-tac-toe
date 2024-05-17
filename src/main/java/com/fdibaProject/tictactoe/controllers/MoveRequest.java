package com.fdibaProject.tictactoe.controllers;


import com.fdibaProject.tictactoe.entities.Player;

public class MoveRequest {
    private int index; // The index of the move
    private Player player; // The player making the move (X or O)

    // Default constructor
    public MoveRequest() {
    }

    // Parameterized constructor
    public MoveRequest(int index, Player player) {
        this.index = index;
        this.player = player;
    }

    // Getters and Setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
