package com.fdibaProject.tictactoe.exceptions;

public class NotFoundException extends Exception{

    private final String message;

    public NotFoundException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
