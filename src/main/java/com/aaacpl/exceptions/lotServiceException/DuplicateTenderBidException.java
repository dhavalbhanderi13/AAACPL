package com.aaacpl.exceptions.lotServiceException;

public class DuplicateTenderBidException extends RuntimeException {
    public DuplicateTenderBidException(String message){
        super(message);
    }
}
