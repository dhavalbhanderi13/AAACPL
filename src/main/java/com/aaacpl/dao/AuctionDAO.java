package com.aaacpl.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.auction.CreateAuctionDTO;

public class AuctionDAO {

	public Boolean insertAuction(CreateAuctionDTO createAuctionDTO)
			throws SQLException, IOException {
		boolean isCreated = false;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			int parameterIndex = 1;
			connection = new ConnectionPool().getPoolConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("INSERT INTO auction(dept_id,auction_name,auction_des,initial_bid,startdate,enddate,catalog,status,updatedby) VALUES (?,?,?,?,?,?,?,?,?);");
			preparedStatement.setInt(parameterIndex++,
					createAuctionDTO.getDeptId());
			preparedStatement.setString(parameterIndex++,
					createAuctionDTO.getName());
			preparedStatement.setString(parameterIndex++,
					createAuctionDTO.getDescription());
			preparedStatement.setInt(parameterIndex++,
					createAuctionDTO.getInitialBid());

			// Example : String date = "2000-11-21"; YYYY-MM-DD
			preparedStatement.setDate(parameterIndex++,
					Date.valueOf(createAuctionDTO.getStartDate()));
			preparedStatement.setDate(parameterIndex++,
					Date.valueOf(createAuctionDTO.getEndDate()));

			preparedStatement.setString(parameterIndex++,
					createAuctionDTO.getCatalog());
			preparedStatement.setString(parameterIndex++, "A");
			preparedStatement.setString(parameterIndex++,
					createAuctionDTO.getCreatedBy());
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				connection.commit();
				isCreated = Boolean.TRUE;
			} else {
				connection.rollback();
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
		return isCreated;
	}

	public List<AuctionDTO> getAllAuctions() throws SQLException, IOException {
		List<AuctionDTO> auctionDTOs = new ArrayList<AuctionDTO>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder("SELECT * FROM auction");
			ResultSet resultSet = statement.executeQuery(query.toString());
			while (resultSet.next()) {
				AuctionDTO auctionDTO = new AuctionDTO(
						resultSet.getString("auction_name"),
						resultSet.getString("auction_des"),
						resultSet.getInt("dept_id"),
						resultSet.getInt("initial_bid"),
						resultSet.getString("startdate"),
						resultSet.getString("enddate"),
						resultSet.getString("catalog"),
						resultSet.getString("updatedBy"));
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

}
