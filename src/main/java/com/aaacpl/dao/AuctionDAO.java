package com.aaacpl.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.auction.AuctionResponseDTO;
import com.aaacpl.dto.auction.CreateAuctionDTO;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;
import com.aaacpl.rest.exceptions.ResourceNotFoundException;

public class AuctionDAO {

	public AuctionResponseDTO insertAuction(CreateAuctionDTO createAuctionDTO)
			throws SQLException, IOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		AuctionResponseDTO auctionResponseDTO = new AuctionResponseDTO();
		try {
			int parameterIndex = 1;
			connection = new ConnectionPool().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("INSERT INTO auction(dept_id, auction_type_id, auction_name, auction_des, startdate, enddate, catalog, status, createdby, updatedby) VALUES (?,?,?,?,?,?,?,?,?,?);");
			System.out.println("ACTION DTO : " + createAuctionDTO);
			preparedStatement.setInt(parameterIndex++,
					createAuctionDTO.getDeptId());

			preparedStatement.setInt(parameterIndex++,
					createAuctionDTO.getAuctionTypeId());

			preparedStatement.setString(parameterIndex++,
					createAuctionDTO.getName());

			preparedStatement.setString(parameterIndex++,
					createAuctionDTO.getDescription());

			preparedStatement.setTimestamp(parameterIndex++,
					createAuctionDTO.getStartDate());

			preparedStatement.setTimestamp(parameterIndex++,
					createAuctionDTO.getEndDate());

			preparedStatement.setString(parameterIndex++,
					createAuctionDTO.getCatalog());

			preparedStatement.setString(parameterIndex++, "A");

			preparedStatement.setInt(parameterIndex++,
					createAuctionDTO.getCreatedBy());

			preparedStatement.setInt(parameterIndex++,
					createAuctionDTO.getUpdatedBy());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				connection.commit();
			} else {
				connection.rollback();
			}

			try {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					auctionResponseDTO.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException(
							"Creating auction failed, no ID obtained.");
				}
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
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
		return auctionResponseDTO;
	}

	public List<AuctionDTO> getAllAuctions(int departmentId)
			throws SQLException, IOException {
		List<AuctionDTO> auctionDTOs = new ArrayList<AuctionDTO>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder(
					"SELECT * FROM auction where dept_id = ")
					.append(departmentId);
			ResultSet resultSet = statement.executeQuery(query.toString());
			while (resultSet.next()) {
				AuctionDTO auctionDTO = new AuctionDTO(resultSet.getInt("id"),
						resultSet.getString("auction_name"),
						resultSet.getInt("auction_type_id"),
						resultSet.getString("auction_des"),
						resultSet.getInt("dept_id"),
						resultSet.getTimestamp("startdate"),
						resultSet.getTimestamp("enddate"),
						resultSet.getString("catalog"),
						resultSet.getInt("createdBy"),
						resultSet.getInt("updatedBy"));
				auctionDTOs.add(auctionDTO);
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
		return auctionDTOs;
	}

	public AuctionDTO getLotById(int id) throws SQLException, IOException,
			UserNotFoundException {
		Connection connection = null;
		Statement statement = null;
		AuctionDTO auctionDTO = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder(
					"SELECT * FROM auction where id = ").append(id);
			ResultSet resultSet = statement.executeQuery(query.toString());
			int index = 0;
			while (resultSet.next()) {
				index++;
				auctionDTO = new AuctionDTO(resultSet.getInt("id"),
						resultSet.getString("auction_name"),
						resultSet.getInt("auction_type_id"),
						resultSet.getString("auction_des"),
						resultSet.getInt("dept_id"),
						resultSet.getTimestamp("startdate"),
						resultSet.getTimestamp("enddate"),
						resultSet.getString("catalog"),
						resultSet.getInt("createdBy"),
						resultSet.getInt("updatedBy"));
			}

			if (index == 0) {
				throw new ResourceNotFoundException("Auction ID " + id
						+ "not found");
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
		return auctionDTO;
	}

}
