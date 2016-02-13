package com.aaacpl.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.user.LoginResponseDTO;
import com.aaacpl.dto.user.UsersDTO;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;

public class UsersDAO implements IUsersDAO {
	public Boolean insertUser(UsersDTO usersDTO) throws SQLException,
			IOException {
		boolean isCreated = false;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			int parameterIndex = 1;
			connection = new ConnectionPool().getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO users(type_id, name, company_name, email, password,  material, address, city, state, country,pin, phone, mobile, pan_number, vat_number, reg_date, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			preparedStatement.setInt(parameterIndex++, usersDTO.getTypeId());
			preparedStatement.setString(parameterIndex++, usersDTO.getName());
			preparedStatement.setString(parameterIndex++,
					usersDTO.getCompanyName());
			preparedStatement.setString(parameterIndex++, usersDTO.getEmail());
			preparedStatement.setString(parameterIndex++,
					usersDTO.getPassword());
			preparedStatement.setString(parameterIndex++,
					usersDTO.getMaterial());
			preparedStatement
					.setString(parameterIndex++, usersDTO.getAddress());
			preparedStatement.setString(parameterIndex++, usersDTO.getCity());
			preparedStatement.setString(parameterIndex++, usersDTO.getState());
			preparedStatement
					.setString(parameterIndex++, usersDTO.getCountry());
			preparedStatement.setInt(parameterIndex++, usersDTO.getPin());
			preparedStatement.setLong(parameterIndex++,
					Long.valueOf(usersDTO.getPhone()));
			preparedStatement.setLong(parameterIndex++,
					Long.valueOf(usersDTO.getMobile()));
			preparedStatement.setString(parameterIndex++,
					usersDTO.getPanNumber());
			preparedStatement.setString(parameterIndex++,
					usersDTO.getVatNumber());

			java.util.Date today = new java.util.Date();
			preparedStatement.setDate(parameterIndex++,
					new java.sql.Date(today.getTime()));
			preparedStatement.setString(parameterIndex++, "A");
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				isCreated = Boolean.TRUE;
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isCreated;
	}

	@Override
	public LoginResponseDTO getNamePasswordForLoginValidationForName(String name)
			throws SQLException, IOException, UserNotFoundException {
		Connection connection = null;
		Statement statement = null;
		LoginResponseDTO loginResponseDTO = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder(
					"SELECT id, password FROM users where email = \"").append(
					name).append("\"");
			ResultSet resultSet = statement.executeQuery(query.toString());
			int rowCount = 0;
			loginResponseDTO = new LoginResponseDTO();
			while (resultSet.next()) {
				loginResponseDTO.setName(name);
				loginResponseDTO.setId(resultSet.getInt(1));
				loginResponseDTO.setPassword(resultSet.getString(2));
				rowCount++;
			}
			if (rowCount == 0) {
				throw new UserNotFoundException("User name invalid");
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginResponseDTO;
	}

	public UsersDTO getUserById(int id) throws SQLException, IOException,
			UserNotFoundException {
		Connection connection = null;
		Statement statement = null;
		UsersDTO usersDTO = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder(
					"SELECT * FROM users where id = ").append(id);
			ResultSet resultSet = statement.executeQuery(query.toString()
					.trim());

			usersDTO = new UsersDTO();
			int index = 1;
			while (resultSet.next()) {
				usersDTO.setId(resultSet.getInt(index++));
				usersDTO.setTypeId(resultSet.getInt(index++));
				usersDTO.setName(resultSet.getString(index++));
				usersDTO.setCompanyName(resultSet.getString(index++));
				usersDTO.setEmail(resultSet.getString(index++));
				usersDTO.setPassword(resultSet.getString(index++));
				usersDTO.setMaterial(resultSet.getString(index++));
				usersDTO.setAddress(resultSet.getString(index++));
				usersDTO.setCity(resultSet.getString(index++));
				usersDTO.setState(resultSet.getString(index++));
				usersDTO.setCountry(resultSet.getString(index++));
				usersDTO.setPin(resultSet.getInt(index++));
				usersDTO.setPhone(resultSet.getInt(index++));
				usersDTO.setMobile(resultSet.getInt(index++));
				usersDTO.setPanNumber(resultSet.getString(index++));
				usersDTO.setVatNumber(resultSet.getString(index++));
			}

			if (index == 1) {
				throw new UserNotFoundException("Invalid user");
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return usersDTO;
	}
}
