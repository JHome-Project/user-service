package com.jhome.user.exception;

public class UserException extends RuntimeException{
    public UserException(String message){
        super(message);
    }

    public UserException(){
        super();
    }
}
