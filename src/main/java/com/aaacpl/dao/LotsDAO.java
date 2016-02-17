package com.aaacpl.dao;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.lots.CreateLotRequestDTO;
import com.aaacpl.dto.lots.CreateLotResponseDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.aaacpl.exceptions.lotServiceException.LotNotFoundException;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;

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
            connection = new ConnectionPool().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("INSERT INTO lot(auction_id, name, description, start_bid, difference_factor, startdate,enddate,created_by,updated_by) VALUES (?,?,?,?,?,?,?,?,?);");
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
            preparedStatement.setTimestamp(parameterIndex++,
                    createLotRequestDTO.getStartDate());
            preparedStatement.setTimestamp(parameterIndex++,
                    createLotRequestDTO.getEndDate());
            preparedStatement.setInt(parameterIndex++,
                    createLotRequestDTO.getCreatedBy());
            preparedStatement.setInt(parameterIndex++,
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

    public List<LotDTO> getAllLots(int auctionId) throws SQLException, IOException {
        List<LotDTO> lotDTOs = new ArrayList<LotDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM lot where auction_id = ").append(auctionId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                LotDTO lotDTO = new LotDTO(
                        resultSet.getInt("id"),
                        resultSet.getInt("auction_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("start_bid"),
                        resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"),
                        resultSet.getTimestamp("enddate"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("updated_by"));
                lotDTOs.add(lotDTO);
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

    public List<LotDTO> getLotsByUser(int userId) throws SQLException, IOException {
        List<LotDTO> lotDTOs = new ArrayList<LotDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("select * from lot where id IN(Select DISTINCT lot_id from lot_user_map where user_id =").append(userId).append(")");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                LotDTO lotDTO = new LotDTO(
                        resultSet.getInt("id"),
                        resultSet.getInt("auction_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("start_bid"),
                        resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"),
                        resultSet.getTimestamp("enddate"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("updated_by"));
                lotDTOs.add(lotDTO);
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


    public LotDTO getLotById(int id) throws SQLException, IOException,
            UserNotFoundException {
        Connection connection = null;
        Statement statement = null;
        LotDTO lotDTO = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM lot where id = ").append(id);
            ResultSet resultSet = statement.executeQuery(query.toString());
            int index = 0;
            while (resultSet.next()) {
                index++;
                lotDTO = new LotDTO(
                        resultSet.getInt("id"),
                        resultSet.getInt("auction_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("start_bid"),
                        resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"),
                        resultSet.getTimestamp("enddate"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("updated_by"));
            }

            if(index == 0){
                throw new LotNotFoundException("Lot not found");
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
        return lotDTO;
    }
}

