package com.aaacpl.exceptions.userServiceExceptions;

import com.aaacpl.errormodels.ErrorMessage;

import javax.ws.rs.core.Response;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Hp on 07-02-2016.
 */

@Provider
public class UserNotFoundExceptionMapper implements
		ExceptionMapper<UserNotFoundException> {
	@Override
	public Response toResponse(UserNotFoundException e) {
		ErrorMessage errorMessage = new ErrorMessage("User is invalid");
		return Response.status(Response.Status.NOT_FOUND).entity(errorMessage)
				.build();
	}
}
