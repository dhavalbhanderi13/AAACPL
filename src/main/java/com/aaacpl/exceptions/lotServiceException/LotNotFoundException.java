package com.aaacpl.exceptions.lotServiceException;

public class LotNotFoundException extends RuntimeException {
    public LotNotFoundException(String message){
        super(message);
    }
}
