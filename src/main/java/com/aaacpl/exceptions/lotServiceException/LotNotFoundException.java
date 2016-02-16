package com.aaacpl.exceptions.lotServiceException;

/**
 * Created by Hp on 16-02-2016.
 */
public class LotNotFoundException extends RuntimeException {
    public LotNotFoundException(String message){
        super(message);
    }
}
