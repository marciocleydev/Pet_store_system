package com.projetopet.Pet_shop_system.exceptions;

public class IntegrityViolationException extends RuntimeException{
    public IntegrityViolationException(String msg){
        super(msg);
    }
}
