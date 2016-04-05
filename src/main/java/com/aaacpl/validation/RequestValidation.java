package com.aaacpl.validation;

import com.aaacpl.dao.UsersDAO;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Sumedh on 31-03-2016.
 */
public class RequestValidation {
    public static Boolean isRequestValid(String sessionId){
        String[] sessionIdParts = sessionId.split("@");
        Boolean isValidRequest = Boolean.FALSE;
        try {
            String sessionIdForUser = new UsersDAO().getSessionIdForUserId(Integer.parseInt(sessionIdParts[1]));
            if(sessionIdForUser.equals(sessionIdParts[0])){
                isValidRequest = Boolean.TRUE;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return Boolean.FALSE;
        }catch (IOException e){
            e.printStackTrace();
            return Boolean.FALSE;
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return Boolean.FALSE;
        }

        return isValidRequest;
    }
}
