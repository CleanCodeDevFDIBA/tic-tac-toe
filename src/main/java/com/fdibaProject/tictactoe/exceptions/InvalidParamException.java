package com.fdibaProject.tictactoe.exceptions;

public class InvalidParamException extends Exception{

    private final String message;

    public InvalidParamException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
