package com.aaacpl.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.aaacpl.dto.user.LoginResponseDTO;
import com.aaacpl.dto.user.UsersDTO;

public interface IUsersDAO {
	Boolean insertUser(UsersDTO usersDTO) throws SQLException;

	LoginResponseDTO getNamePasswordForLoginValidationForName(String email)
			throws SQLException;
}
