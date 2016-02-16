package com.aaacpl.exceptions.lotServiceException;

public class LotNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LotNotFoundException(String message) {
		super(message);
	}
}
