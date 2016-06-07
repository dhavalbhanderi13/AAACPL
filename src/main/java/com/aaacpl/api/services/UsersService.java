package com.aaacpl.api.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aaacpl.bo.request.user.*;
import com.aaacpl.bo.response.LoginResponseBO;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;
import com.aaacpl.requestHandlers.UserRequestHandler;
import com.aaacpl.rest.request.user.*;
import com.aaacpl.rest.response.requestAuth.RequestAuthenticationResponse;
import com.aaacpl.rest.response.user.LoginResponse;
import com.aaacpl.rest.response.user.RegistrationResponse;
import com.aaacpl.rest.response.user.UpdateResponse;
import com.aaacpl.rest.response.user.UserLoggedInResponse;
import com.aaacpl.rest.response.user.UserResponseList;
import com.aaacpl.rest.response.user.UserTypesResponse;
import com.aaacpl.rest.util.ResponseGenerator;
import com.aaacpl.validation.RequestValidation;

@Path("/user")
public class UsersService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(LoginRequest loginRequest) {
        LoginRequestBO loginRequestBO = new LoginRequestBO();
        loginRequestBO.setEmail(loginRequest.getEmail());
        loginRequestBO.setPassword(loginRequest.getPassword());
        UserRequestHandler userRequestHandler = new UserRequestHandler();
        LoginResponse loginResponse = new LoginResponse();
        try {
            LoginResponseBO loginResponseBO = userRequestHandler
                    .login(loginRequestBO);
            if (loginResponseBO.getValidUser()) {
                loginResponse.setFailureMessage("");
                loginResponse.setUserId(loginResponseBO.getId());
                loginResponse.setDepartmentId(loginResponseBO.getDepartmentId());
                loginResponse.setSuccessMessage(String.valueOf(loginResponseBO
                        .getSessionId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            loginResponse.setSuccessMessage("");
            loginResponse.setFailureMessage(e.getMessage());
        }
        return ResponseGenerator.generateResponse(loginResponse);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(RegistrationRequest registrationRequest) {
        RegistrationRequestBO registrationRequestBO = new RegistrationRequestBO();
        registrationRequestBO.setTypeId(registrationRequest.getTypeId());
        registrationRequestBO.setName(registrationRequest.getName());
        registrationRequestBO.setCompanyName(registrationRequest
                .getCompanyName());
        registrationRequestBO.setEmail(registrationRequest.getEmail());
        registrationRequestBO.setPassword(registrationRequest.getPassword());
        registrationRequestBO.setVatNumber(registrationRequest.getVatNumber());
        registrationRequestBO.setPanNumber(registrationRequest.getPanNumber());
        registrationRequestBO.setMaterial(registrationRequest.getMaterial());
        registrationRequestBO.setAddress(registrationRequest.getAddress());
        registrationRequestBO.setCity(registrationRequest.getCity());
        registrationRequestBO.setState(registrationRequest.getState());
        registrationRequestBO.setCountry(registrationRequest.getCountry());
        registrationRequestBO.setPin(registrationRequest.getPin());
        registrationRequestBO.setPhone(registrationRequest.getPhone());
        registrationRequestBO.setMobile(registrationRequest.getMobile());

        UserRequestHandler userRequestHandler = new UserRequestHandler();
        RegistrationResponse registrationResponse = new RegistrationResponse();
        if (userRequestHandler.register(registrationRequestBO)) {
            registrationResponse.setFailureMessage("");
            registrationResponse.setSuccessMessage("SUCCESS");
        } else {
            registrationResponse.setFailureMessage("FAILURE");
            registrationResponse.setSuccessMessage("");
        }
        return ResponseGenerator.generateResponse(registrationResponse);
    }

    @GET
    @Path("/confirm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmUser(@QueryParam("id") int id) {
        UserRequestHandler userRequestHandler = new UserRequestHandler();
        RegistrationResponse registrationResponse = new RegistrationResponse();
        if (userRequestHandler.verifyUser(id)) {
            registrationResponse.setFailureMessage("");
            registrationResponse.setSuccessMessage("SUCCESS");
        } else {
            registrationResponse.setFailureMessage("FAILURE");
            registrationResponse.setSuccessMessage("");
        }
        return ResponseGenerator.generateResponse(registrationResponse);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UpdateUserRequest updateUser, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            UpdaterUserBO updateRequestBO = new UpdaterUserBO();
            updateRequestBO.setId(updateUser.getId());
            updateRequestBO.setTypeId(updateUser.getTypeId());
            updateRequestBO.setName(updateUser.getName());
            updateRequestBO.setStatus(updateUser.getStatus());
            updateRequestBO.setCompanyName(updateUser.getCompanyName());
            updateRequestBO.setEmail(updateUser.getEmail());
            updateRequestBO.setVatNumber(updateUser.getVatNumber());
            updateRequestBO.setPanNumber(updateUser.getPanNumber());
            updateRequestBO.setMaterial(updateUser.getMaterial());
            updateRequestBO.setAddress(updateUser.getAddress());
            updateRequestBO.setCity(updateUser.getCity());
            updateRequestBO.setState(updateUser.getState());
            updateRequestBO.setCountry(updateUser.getCountry());
            updateRequestBO.setPin(updateUser.getPin());
            updateRequestBO.setPhone(updateUser.getPhone());
            updateRequestBO.setMobile(updateUser.getMobile());

            UserRequestHandler userRequestHandler = new UserRequestHandler();
            UpdateResponse updateResponse = new UpdateResponse();
            if (userRequestHandler.updateUser(updateRequestBO)) {
                updateResponse.setFailureMessage("");
                updateResponse.setSuccessMessage("SUCCESS");
            } else {
                updateResponse.setFailureMessage("FAILURE");
                updateResponse.setSuccessMessage("");
            }
            return ResponseGenerator.generateResponse(updateResponse);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @POST
    @Path("/assignDept")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignDepartment(UserDepartmentAssignRequest userDepartmentAssignRequest, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            UserDepartmentAssignBO userDepartmentAssignBO = new UserDepartmentAssignBO();
            userDepartmentAssignBO.setDepartmentId(userDepartmentAssignRequest.getDepartmentId());
            userDepartmentAssignBO.setUserId(userDepartmentAssignRequest.getUserId());
            UserRequestHandler userRequestHandler = new UserRequestHandler();
            UpdateResponse updateResponse = new UpdateResponse();
            if (userRequestHandler.updateUserDepartment(userDepartmentAssignBO)) {
                updateResponse.setFailureMessage("");
                updateResponse.setSuccessMessage("SUCCESS");
            } else {
                updateResponse.setFailureMessage("FAILURE");
                updateResponse.setSuccessMessage("");
            }
            return ResponseGenerator.generateResponse(updateResponse);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @GET
    @Path("/userTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserTypes() {
        UserRequestHandler userRequestHandler = new UserRequestHandler();
        UserTypesResponse userTypesResponse = new UserTypesResponse();
        userTypesResponse.setGetTypesResponseList(userRequestHandler
                .getUserTypes());
        return ResponseGenerator.generateResponse(userTypesResponse);
    }

    @GET
    @Path("/userInfo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            UserRequestHandler userRequestHandler = new UserRequestHandler();
            Object response = null;
            try {
                response = userRequestHandler.getUserById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (UserNotFoundException e) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setSuccessMessage("");
                loginResponse.setFailureMessage(e.getMessage());
                return ResponseGenerator.generateResponse(loginResponse);
            }
            return ResponseGenerator.generateResponse(response);
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(LogoutRequest logoutRequest, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            UserRequestHandler userRequestHandler = new UserRequestHandler();
            Boolean isLoggedOut = userRequestHandler.logout(logoutRequest
                    .getUserId());
            LoginResponse loginResponse = new LoginResponse();
            if (isLoggedOut) {
                loginResponse.setSuccessMessage("SUCCESS");
                loginResponse.setFailureMessage("");
                return ResponseGenerator.generateResponse(loginResponse);
            } else {
                loginResponse.setSuccessMessage("");
                loginResponse.setFailureMessage("FAILURE");
                return ResponseGenerator.generateResponse(loginResponse);
            }
        } else {
            return ResponseGenerator.generateResponse(RequestValidation.getUnautheticatedResponse());
        }
    }

    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList() {
        UserRequestHandler userRequestHandler = new UserRequestHandler();
        List<UserResponseList> response = null;
        try {
            response = userRequestHandler.getUsersList();
        } catch (UserNotFoundException e) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccessMessage("");
            loginResponse.setFailureMessage(e.getMessage());
            return ResponseGenerator.generateResponse(loginResponse);
        }
        return ResponseGenerator.generateResponse(response);
    }

    @GET
    @Path("/loggedIn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserLoggedIn() {
        UserRequestHandler userRequestHandler = new UserRequestHandler();
        List<UserLoggedInResponse> response = null;
        try {
            response = userRequestHandler.getUserLoggedIn();
        } catch (UserNotFoundException e) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccessMessage("");
            loginResponse.setFailureMessage(e.getMessage());
            return ResponseGenerator.generateResponse(loginResponse);
        }
        return ResponseGenerator.generateResponse(response);
    }

    @GET
    @Path("/forgot/{emailId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response forgotPassword(@PathParam("emailId") String emailId) {
        UserRequestHandler userRequestHandler = new UserRequestHandler();
        Boolean mailSent = userRequestHandler.forgotPassword(emailId);
        LoginResponse loginResponse = new LoginResponse();
        if (mailSent) {
            loginResponse.setSuccessMessage("SUCCESS");
            loginResponse.setFailureMessage("");
            return ResponseGenerator.generateResponse(loginResponse);
        } else {
            loginResponse.setSuccessMessage("");
            loginResponse.setFailureMessage("FAILURE");
            return ResponseGenerator.generateResponse(loginResponse);
        }
    }

    @POST
    @Path("/changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(ChangePasswordRequest changePwdReq) {
        ChangePasswordBO changePwdBO = new ChangePasswordBO(
                changePwdReq.getUserId(), changePwdReq.getOldPassword(),
                changePwdReq.getNewPassword());

        UserRequestHandler userRequestHandler = new UserRequestHandler();
        UpdateResponse updateResponse = new UpdateResponse();
        if (userRequestHandler.changePassword(changePwdBO)) {
            updateResponse.setFailureMessage("");
            updateResponse.setSuccessMessage("Password Updated");
        } else {
            updateResponse.setFailureMessage("FAILURE");
            updateResponse.setSuccessMessage("");
        }
        return ResponseGenerator.generateResponse(updateResponse);
    }

}
