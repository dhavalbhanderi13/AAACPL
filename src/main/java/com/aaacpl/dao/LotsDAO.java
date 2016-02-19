package com.aaacpl.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aaacpl.bo.request.lots.BidRequestBO;
import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.lots.CreateLotRequestDTO;
import com.aaacpl.dto.lots.CreateLotResponseDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.aaacpl.dto.lots.LotStatusDTO;
import com.aaacpl.exceptions.lotServiceException.LotNotFoundException;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;
import com.aaacpl.rest.request.lots.StatusRequest;
import com.aaacpl.util.DateUtil;

public class LotsDAO {
    public CreateLotResponseDTO createLot(CreateLotRequestDTO createLotRequestDTO) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        CreateLotResponseDTO createLotResponseDTO = new CreateLotResponseDTO(0);
        try {
            int parameterIndex = 1;
            connection = new ConnectionPool().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO lot(auction_id, name, description, start_bid, difference_factor, "
                    + "startdate, enddate, createdby, updatedby) VALUES (?,?,?,?,?,?,?,?,?);");

            preparedStatement.setInt(parameterIndex++, createLotRequestDTO.getAuctionId());

            preparedStatement.setString(parameterIndex++, createLotRequestDTO.getName());

            preparedStatement.setString(parameterIndex++, createLotRequestDTO.getDescription());

            preparedStatement.setString(parameterIndex++, createLotRequestDTO.getStartBid());

            preparedStatement.setInt(parameterIndex++, createLotRequestDTO.getDifferenceFactor());

            // Example : String date = "2000-11-21"; YYYY-MM-DD
            preparedStatement.setTimestamp(parameterIndex++, createLotRequestDTO.getStartDate());

            preparedStatement.setTimestamp(parameterIndex++, createLotRequestDTO.getEndDate());

            preparedStatement.setInt(parameterIndex++, createLotRequestDTO.getCreatedBy());

            preparedStatement.setInt(parameterIndex++, createLotRequestDTO.getCreatedBy());

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }

            try {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    createLotResponseDTO = new CreateLotResponseDTO(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
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
                LotDTO lotDTO = new LotDTO(resultSet.getInt("id"), resultSet.getInt("auction_id"), resultSet.getString("name"),
                        resultSet.getString("description"), resultSet.getString("start_bid"), resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"), resultSet.getTimestamp("enddate"), resultSet.getInt("createdby"),
                        resultSet.getInt("updatedby"));
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

    public List<LotDTO> getLotsByUser(int userId, int auctionId) throws SQLException, IOException {
        List<LotDTO> lotDTOs = new ArrayList<LotDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("select * from lot where id IN(Select DISTINCT lot_id from lot_user_map where user_id =")
                    .append(userId).append(") AND lot.auction_id = ").append(auctionId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                LotDTO lotDTO = new LotDTO(resultSet.getInt("id"), resultSet.getInt("auction_id"), resultSet.getString("name"),
                        resultSet.getString("description"), resultSet.getString("start_bid"), resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"), resultSet.getTimestamp("enddate"), resultSet.getInt("createdby"),
                        resultSet.getInt("updatedby"));
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

    public LotDTO getLotById(int id) throws SQLException, IOException, UserNotFoundException {
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
                lotDTO = new LotDTO(resultSet.getInt("id"), resultSet.getInt("auction_id"), resultSet.getString("name"),
                        resultSet.getString("description"), resultSet.getString("start_bid"), resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"), resultSet.getTimestamp("enddate"), resultSet.getInt("createdby"),
                        resultSet.getInt("updatedby"));
            }

            if (index == 0) {
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

    public Boolean insertBid(BidRequestBO bidRequestBO) throws SQLException, IOException {
        boolean isProcessed = false;
        Connection connection = null;
        try {
            connection = new ConnectionPool().getConnection();
            connection.setAutoCommit(false);

            // First Log the bid request in lot_audit_log
            if (insertIntoLotAuditLog(connection, bidRequestBO)) {

                // returning true because we are not sure if the bid is max
                isProcessed = Boolean.TRUE;

                // Check if the current bid Amount is greater than Max
                if (isBidAmtMax(connection, bidRequestBO)) {

                    // If true Insert/Update the Max value in live_audit_log
                    if (insertIntoLiveBidLog(connection, bidRequestBO)) {
                        isProcessed = Boolean.TRUE;
                    } else {
                        isProcessed = Boolean.FALSE;
                    }
                } else {

                }
            }

        } catch (SQLException sqlException) {
            isProcessed = false;
            connection.rollback();
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isProcessed;
    }

    private boolean insertIntoLotAuditLog(Connection connection, BidRequestBO bidRequestBO) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        int parameterIndex = 1;
        boolean isProcessed = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO lot_audit_log(user_id, lot_id, bid_amt, ipAddress, localSystemTime) "
                    + " VALUES (?,?,?,?,?);");

            preparedStatement.setInt(parameterIndex++, bidRequestBO.getUserId());

            preparedStatement.setInt(parameterIndex++, bidRequestBO.getLotId());

            preparedStatement.setLong(parameterIndex++, bidRequestBO.getBidAmount());

            preparedStatement.setString(parameterIndex++, bidRequestBO.getIpAddress());

            preparedStatement.setString(parameterIndex++, bidRequestBO.getLocalSystemTime());

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                isProcessed = true;
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
        } finally {
            try {
                // Do Not close connection over here
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isProcessed;
    }

    private Boolean isBidAmtMax(Connection connection, BidRequestBO bidRequestBO) throws SQLException, IOException {
        Statement statement = null;
        Long maxValue = 0L;
        Boolean isMax = Boolean.FALSE;
        String query = "SELECT MAX(max_value) from live_bid_log WHERE lot_id=" + bidRequestBO.getLotId();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                maxValue = rs.getLong("MAX(max_value)");
            }
            if (bidRequestBO.getBidAmount() > maxValue) {
                return Boolean.TRUE;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                // Do Not close connection over here
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isMax;
    }

    private boolean insertIntoLiveBidLog(Connection connection, BidRequestBO bidRequestBO) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        int parameterIndex = 1;
        boolean isProcessed = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO live_bid_log(user_id, lot_id, max_value, ipAddress, localSystemTime) "
                    + " VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE max_value=" + bidRequestBO.getBidAmount() + ";");

            preparedStatement.setInt(parameterIndex++, bidRequestBO.getUserId());

            preparedStatement.setInt(parameterIndex++, bidRequestBO.getLotId());

            preparedStatement.setLong(parameterIndex++, bidRequestBO.getBidAmount());

            preparedStatement.setString(parameterIndex++, bidRequestBO.getIpAddress());

            preparedStatement.setString(parameterIndex++, bidRequestBO.getLocalSystemTime());

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                isProcessed = true;
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
        } finally {
            try {
                // Do Not close connection over here
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isProcessed;
    }

    public LotStatusDTO getLotStatus(StatusRequest statusRequest) throws SQLException, IOException {
        Statement statement = null;
        Connection connection = null;
        LotStatusDTO lotStatusResponse = null;
        String query = "SELECT * from live_bid_log WHERE lot_id=" + statusRequest.getLotid();
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int maxValue = rs.getInt("max_value");
                int userId = rs.getInt("user_id");
                String currentServerTime = DateUtil.getCurrentServerTime();
                boolean hasHighestBidChanged = (maxValue > statusRequest.getCurrentBidMax());
                lotStatusResponse = new LotStatusDTO(maxValue, userId, currentServerTime, hasHighestBidChanged);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                // Do Not close connection over here
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lotStatusResponse;
    }
}
