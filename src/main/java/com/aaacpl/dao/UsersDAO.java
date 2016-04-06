package com.aaacpl.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aaacpl.bo.request.user.ChangePasswordBO;
import com.aaacpl.bo.request.user.UpdaterUserBO;
import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.user.LoginResponseDTO;
import com.aaacpl.dto.user.UsersDTO;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;
import com.aaacpl.rest.response.user.UserLoggedInResponse;
import com.aaacpl.rest.response.user.UserResponseList;

public class UsersDAO {

	public Integer insertUser(UsersDTO usersDTO) throws SQLException,
			IOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			int parameterIndex = 1;
			connection = new ConnectionPool().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("INSERT INTO users(type_id, name, company_name, email, password,  material, address, city, state, country,pin, phone, mobile, pan_number, vat_number, reg_date, status, isVerified) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
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
			preparedStatement.setString(parameterIndex++, usersDTO.getPhone());
			preparedStatement.setString(parameterIndex++, usersDTO.getMobile());
			preparedStatement.setString(parameterIndex++,
					usersDTO.getPanNumber());
			preparedStatement.setString(parameterIndex++,
					usersDTO.getVatNumber());

			java.util.Date today = new java.util.Date();
			preparedStatement.setDate(parameterIndex++,
					new java.sql.Date(today.getTime()));
			preparedStatement.setString(parameterIndex++, "I");
			preparedStatement.setString(parameterIndex++, "false");
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				connection.commit();
			} else {
				connection.rollback();
			}

			try {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				} else {
					throw new SQLException(
							"Creating user failed, no ID obtained.");
				}
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
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
		return null;
	}

	public Boolean updateUser(UpdaterUserBO updateUserBO) throws SQLException,
			IOException {
		boolean isCreated = false;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			int parameterIndex = 1;
			connection = new ConnectionPool().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("UPDATE users SET type_id =?, name =?, company_name =?, email =? , "
							+ " material =?, address =?, city =?, state =?, country =?, pin =?, phone =?, mobile =?, pan_number =?,"
							+ " vat_number =?, status =? WHERE id =?;");
			
			preparedStatement.setInt(parameterIndex++, updateUserBO.getTypeId());
			
			preparedStatement.setString(parameterIndex++, updateUserBO.getName());
			
			preparedStatement.setString(parameterIndex++,
					updateUserBO.getCompanyName());
			
			preparedStatement.setString(parameterIndex++, updateUserBO.getEmail());
			
			preparedStatement.setString(parameterIndex++,
					updateUserBO.getMaterial());
			
			preparedStatement
					.setString(parameterIndex++, updateUserBO.getAddress());
			
			preparedStatement.setString(parameterIndex++, updateUserBO.getCity());
			
			preparedStatement.setString(parameterIndex++, updateUserBO.getState());
			
			preparedStatement
					.setString(parameterIndex++, updateUserBO.getCountry());
			
			preparedStatement.setInt(parameterIndex++, updateUserBO.getPin());
			
			preparedStatement.setString(parameterIndex++, updateUserBO.getPhone());
			
			preparedStatement.setString(parameterIndex++, updateUserBO.getMobile());
			
			preparedStatement.setString(parameterIndex++,
					updateUserBO.getPanNumber());

			preparedStatement.setString(parameterIndex++,
					updateUserBO.getVatNumber());
			
			preparedStatement.setString(parameterIndex++, updateUserBO.getStatus());
			
			preparedStatement.setInt(parameterIndex++, updateUserBO.getId());
			
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				connection.commit();
				isCreated = Boolean.TRUE;
			} else {
				connection.rollback();
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

	public Boolean updateSessionId(int userId, Long sessionId)
			throws SQLException, IOException {
		boolean isUpdated = false;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = new ConnectionPool().getConnection();
			connection.setAutoCommit(false);
			StringBuffer query = new StringBuffer(
					"UPDATE users SET sessionId = ").append(sessionId)
					.append(" WHERE id = ").append(userId);
			preparedStatement = connection.prepareStatement(query.toString());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				connection.commit();
				isUpdated = Boolean.TRUE;
			} else {
				connection.rollback();
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
		return isUpdated;
	}

	public Boolean updateVerifiedUser(int userId)
			throws SQLException, IOException {
		boolean isUpdated = false;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = new ConnectionPool().getConnection();
			connection.setAutoCommit(false);
			StringBuffer query = new StringBuffer(
					"UPDATE users SET isVerified = ").append("'true'")
					.append(" WHERE id = ").append(userId);
			preparedStatement = connection.prepareStatement(query.toString());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				connection.commit();
				isUpdated = Boolean.TRUE;
			} else {
				connection.rollback();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUpdated;
	}

	public LoginResponseDTO getNamePasswordForLoginValidationForEmailAndStatus(
			String email) throws SQLException, IOException,
			UserNotFoundException {
		Connection connection = null;
		Statement statement = null;
		LoginResponseDTO loginResponseDTO = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder(
					"SELECT id, password, status FROM users where email = \"")
					.append(email).append("\"");
			ResultSet resultSet = statement.executeQuery(query.toString());
			int rowCount = 0;
			loginResponseDTO = new LoginResponseDTO();
			while (resultSet.next()) {
				loginResponseDTO.setEmail(email);
				loginResponseDTO.setId(resultSet.getInt("id"));
				loginResponseDTO.setPassword(resultSet.getString("password"));
				loginResponseDTO.setStatus(resultSet.getString("status"));
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
				usersDTO.setId(resultSet.getInt("id"));
				usersDTO.setTypeId(resultSet.getInt("type_id"));
				usersDTO.setName(resultSet.getString("name"));
				usersDTO.setCompanyName(resultSet.getString("company_name"));
				usersDTO.setEmail(resultSet.getString("email"));
				usersDTO.setPassword(resultSet.getString("password"));
				usersDTO.setMaterial(resultSet.getString("material"));
				usersDTO.setAddress(resultSet.getString("address"));
				usersDTO.setCity(resultSet.getString("city"));
				usersDTO.setState(resultSet.getString("state"));
				usersDTO.setCountry(resultSet.getString("country"));
				usersDTO.setPin(resultSet.getInt("pin"));
				usersDTO.setPhone(resultSet.getString("phone"));
				usersDTO.setMobile(resultSet.getString("mobile"));
				usersDTO.setPanNumber(resultSet.getString("pan_number"));
				usersDTO.setVatNumber(resultSet.getString("vat_number"));
				index++;
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

	public List<UserResponseList> getUsersList() throws SQLException,
			IOException {
		Connection connection = null;
		Statement statement = null;
		List<UserResponseList> userResponseList = new ArrayList<UserResponseList>();
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			String query = "SELECT * FROM users ";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				UserResponseList userResponse = new UserResponseList(
						resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("company_name"),
						resultSet.getInt("type_id"),
						resultSet.getString("email"),
						resultSet.getString("material"),
						resultSet.getString("address"),
						resultSet.getString("city"),
						resultSet.getString("country"),
						resultSet.getString("state"), resultSet.getInt("pin"),
						resultSet.getString("pan_number"),
						resultSet.getString("vat_number"),
						resultSet.getString("mobile"),
						resultSet.getString("phone"),
						resultSet.getString("status"),
						resultSet.getString("isVerified"));
				userResponseList.add(userResponse);
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

		return userResponseList;
	}

	public List<UserLoggedInResponse> getUserLoggedIn() throws SQLException,
			IOException {
		Connection connection = null;
		Statement statement = null;
		List<UserLoggedInResponse> userLoggedinList = new ArrayList<UserLoggedInResponse>();
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			String query = "SELECT * FROM users WHERE sessionId IS NOT NULL";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				UserLoggedInResponse userResponse = new UserLoggedInResponse(
						resultSet.getInt("id"),
						resultSet.getString("company_name"));
				userLoggedinList.add(userResponse);
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

		return userLoggedinList;
	}

	public Boolean changePassword(ChangePasswordBO changePwdBO) {
		Boolean isProcessed = Boolean.FALSE;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			String query = "SELECT password FROM users WHERE id="
					+ changePwdBO.getUserId();

			ResultSet resultSet = statement.executeQuery(query);
			String oldDBpassword = null;
			while (resultSet.next()) {
				oldDBpassword = resultSet.getString("password");
			}

			if (oldDBpassword != null && changePwdBO.getOldPassword() != null
					&& oldDBpassword.equals(changePwdBO.getOldPassword())) {
				if (updatePassword(changePwdBO.getNewPassword(),
						changePwdBO.getUserId(), connection)) {
					isProcessed = Boolean.TRUE;
				}
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}catch (IOException io){
			io.printStackTrace();
		} finally{
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isProcessed;
	}

	private boolean updatePassword(String newPassword, int userId,
			Connection connection) throws SQLException {
		boolean isUpdated = false;
		connection.setAutoCommit(false);
		String query = "UPDATE users SET password=\"" + newPassword
				+ "\" WHERE id=" + userId;

		PreparedStatement preparedStatement = connection
				.prepareStatement(query);
		int i = preparedStatement.executeUpdate();
		if (i > 0) {
			connection.commit();
			isUpdated = Boolean.TRUE;
		} else {
			connection.rollback();
		}
		return isUpdated;
	}

	public String getSessionIdForUserId(
			int userId) throws SQLException, IOException,
			UserNotFoundException {
		Connection connection = null;
		Statement statement = null;
        String sessionId = "";
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder(
					"SELECT sessionId FROM users where id = ")
					.append(userId);
			ResultSet resultSet = statement.executeQuery(query.toString());
			int rowCount = 0;
			while (resultSet.next()) {
				sessionId = resultSet.getBigDecimal("sessionId")+"";
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
        return sessionId;
    }
}
