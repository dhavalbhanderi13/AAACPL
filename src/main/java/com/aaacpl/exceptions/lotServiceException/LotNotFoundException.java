package com.aaacpl.exceptions.lotServiceException;

/**
 * Created by Hp on 16-02-2016.
 */
public class LotNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LotNotFoundException(String message) {
		super(message);
	}
}
