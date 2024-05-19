package com.fdibaProject.tictactoe.entities;

public class Player {
    public int ranking;
    public int wins;
    public String name;

    public Player(int wins, String name) {
        this.wins = wins;
        this.name = name;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public String toString() {
        return "{wins=" + wins + ", name=" + name + ", rank=" + ranking + "}";
    }
}

