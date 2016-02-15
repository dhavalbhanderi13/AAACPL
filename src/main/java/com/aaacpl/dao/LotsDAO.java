package com.aaacpl.dao;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.lots.CreateLotRequestDTO;
import com.aaacpl.dto.lots.CreateLotResponseDTO;
import com.aaacpl.dto.lots.LotDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LotsDAO {
    public CreateLotResponseDTO createLot(CreateLotRequestDTO createLotRequestDTO) throws SQLException, IOException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        CreateLotResponseDTO createLotResponseDTO = new CreateLotResponseDTO(0);
        try {
            int parameterIndex = 1;
            connection = new ConnectionPool().getPoolConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("INSERT INTO lot(auction_id, name, description, start_bid, difference_factor, startdate,enddate,createdBy,updatedby) VALUES (?,?,?,?,?,?,?,?,?);");
            preparedStatement.setInt(parameterIndex++,
                    createLotRequestDTO.getAuctionId());
            preparedStatement.setString(parameterIndex++,
                    createLotRequestDTO.getName());
            preparedStatement.setString(parameterIndex++,
                    createLotRequestDTO.getDescription());
            preparedStatement.setString(parameterIndex++,
                    createLotRequestDTO.getStartBid());
            preparedStatement.setInt(parameterIndex++,
                    createLotRequestDTO.getDifferenceFactor());

            // Example : String date = "2000-11-21"; YYYY-MM-DD
            preparedStatement.setDate(parameterIndex++,
                    Date.valueOf(createLotRequestDTO.getStartDate()));
            preparedStatement.setDate(parameterIndex++,
                    Date.valueOf(createLotRequestDTO.getEndDate()));
            preparedStatement.setString(parameterIndex++,
                    createLotRequestDTO.getCreatedBy());
            preparedStatement.setString(parameterIndex++,
                    createLotRequestDTO.getCreatedBy());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }

            try{
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    createLotResponseDTO = new CreateLotResponseDTO(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }catch (SQLException e){
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
        return createLotResponseDTO;
    }

    public List<LotDTO> getAllLots() throws SQLException, IOException {
        List<LotDTO> lotDTOs = new ArrayList<LotDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM lot");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                LotDTO auctionDTO = new LotDTO(
                        resultSet.getInt("id"),
                        resultSet.getInt("auction_id"),
                        resultSet.getString("name"),
                        resultSet.getString("auction_des"),
                        resultSet.getString("start_bid"),
                        resultSet.getInt("difference_factor"),
                        resultSet.getString("startdate"),
                        resultSet.getString("enddate"),
                        resultSet.getString("createdBy"),
                        resultSet.getString("updatedBy"));
                lotDTOs.add(auctionDTO);
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
        return lotDTOs;
    }

}
