package com.fdibaProject.tictactoe.exceptions;

public class InvalidGameException extends Exception{
    private final String message;

    public InvalidGameException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
