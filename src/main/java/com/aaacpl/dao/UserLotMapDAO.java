package com.aaacpl.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.participator.CreateParticipatorDTO;

public class UserLotMapDAO {
	
	public Boolean insertUserLotMapping(
			CreateParticipatorDTO createParticipatorDTO) throws SQLException,
			IOException {
		Boolean isInserted = Boolean.FALSE;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			Iterator<Integer> userIdsIterator = createParticipatorDTO
					.getUserIds().iterator();
			int counter = 0;
			while (userIdsIterator.hasNext()) {
				int parameterIndex = 1;
				connection = new ConnectionPool().getConnection();
				connection.setAutoCommit(false);
				preparedStatement = connection
						.prepareStatement("INSERT INTO lot_user_map(user_id, lot_id) VALUES (?,?);");
				preparedStatement.setInt(parameterIndex++,
						userIdsIterator.next());
				preparedStatement.setInt(parameterIndex++,
						createParticipatorDTO.getLotId());
				int i = preparedStatement.executeUpdate();
				if (i > 0) {
					connection.commit();
				} else {
					connection.rollback();
				}
				counter++;
			}

			if (counter == createParticipatorDTO.getUserIds().size()) {
				isInserted = Boolean.TRUE;
			}
		} catch (SQLException sqlException) {
			connection.rollback();
			sqlException.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isInserted;
	}

	public List<Integer> getListOfUsers(int lotId) throws SQLException,
			IOException {
		List<Integer> userIds = new ArrayList<Integer>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder(
					"SELECT DISTINCT user_id FROM lot_user_map where lot_id = ")
					.append(lotId);
			ResultSet resultSet = statement.executeQuery(query.toString());
			while (resultSet.next()) {
				userIds.add(resultSet.getInt("user_id"));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userIds;
	}

	public Map<Integer, String> getUserNameIdMap(int lotId) throws SQLException,
			IOException {
		Map<Integer, String> userIdNameMap = new HashMap<Integer, String>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder(
					"select id, name from users where id in(select user_id from lot_audit_log where lot_id = ")
					.append(lotId).append(")");
			ResultSet resultSet = statement.executeQuery(query.toString());
			while (resultSet.next()) {
                userIdNameMap.put(resultSet.getInt("id"), resultSet.getString("name"));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userIdNameMap;
	}
}
