package com.aaacpl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.auction.*;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;
import com.aaacpl.rest.exceptions.ResourceNotFoundException;
import com.aaacpl.util.DateUtil;

public class AuctionDAO {

    public AuctionResponseDTO insertAuction(CreateAuctionDTO createAuctionDTO)
            throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String query = "";
        Boolean isTender = createAuctionDTO.getTender();

        query = "INSERT INTO auction(dept_id, auction_type_id, auction_name, auction_des, startdate, enddate, catalog, status, createdby, updatedby, createdDate, isTender, tender_start_date, tender_end_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        AuctionResponseDTO auctionResponseDTO = new AuctionResponseDTO();
        try {
            int parameterIndex = 1;
            connection = new ConnectionPool().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement(query);
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
                    createAuctionDTO.getCreatedBy());

            preparedStatement.setTimestamp(parameterIndex++, new Timestamp(
                    new java.util.Date().getTime()));

            preparedStatement.setBoolean(parameterIndex++, isTender);

            if (isTender) {
                preparedStatement.setTimestamp(parameterIndex++, createAuctionDTO.getTenderStartDate());
                preparedStatement.setTimestamp(parameterIndex++, createAuctionDTO.getTenderEndDate());
            } else {
                preparedStatement.setTimestamp(parameterIndex++, null);
                preparedStatement.setTimestamp(parameterIndex++, null);
            }

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
            throws SQLException {
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
                        resultSet.getInt("updatedBy"),
                        resultSet.getString("status"),
                        resultSet.getBoolean("isTender") ? 1 : 0,
                        resultSet.getTimestamp("tender_start_date"),
                        resultSet.getTimestamp("tender_end_date"));
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

    public List<AuctionTypeDTO> getAuctionsTypes() throws SQLException {
        List<AuctionTypeDTO> auctionTypeDTOs = new ArrayList<AuctionTypeDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM auction_type");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                AuctionTypeDTO auctionTypeDTO = new AuctionTypeDTO();
                auctionTypeDTO.setId(resultSet.getInt("id"));
                auctionTypeDTO.setType( resultSet.getString("type"));
                auctionTypeDTOs.add(auctionTypeDTO);
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
        return auctionTypeDTOs;
    }

    public AuctionDTO getAuctionById(int id) throws SQLException,
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
                        resultSet.getInt("updatedBy"),
                        resultSet.getString("status"),
                        resultSet.getBoolean("isTender") ? 1 : 0,
                        resultSet.getTimestamp("tender_start_date"),
                        resultSet.getTimestamp("tender_end_date"));
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

    public List<AuctionDTO> getUpcomingAuctions(Boolean isTender) throws SQLException {
        List<AuctionDTO> auctionDTOs = new ArrayList<AuctionDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            String dateCondition = "";
            if (isTender) {
                dateCondition = "where tender_start_date >= CONVERT_TZ(CURDATE(), \"-5:00\", \"+5:30\") + interval 1 day;";
            } else {
                dateCondition = "where startdate >= CONVERT_TZ(CURDATE(), \"-5:00\", \"+5:30\") + interval 1 day;";
            }
            StringBuilder query = new StringBuilder(
                    "select * from auction ");
            query.append(dateCondition);
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
                        resultSet.getInt("updatedBy"),
                        resultSet.getString("status"), resultSet.getBoolean("isTender") ? 1 : 0,
                        resultSet.getTimestamp("tender_start_date"),
                        resultSet.getTimestamp("tender_end_date"));
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

    public List<AuctionDTO> getLiveAuctions(Boolean isTender) throws SQLException {
        List<AuctionDTO> auctionDTOs = new ArrayList<AuctionDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            String serverTimeStamp = DateUtil.getCurrentServerTime();
            String serverDate = DateUtil.getCurrentServerDate();
            String dateCondition = "";
            if (isTender) {
                dateCondition = " isTender = " + isTender + " AND date(tender_start_date) <= '" + serverDate + "' AND tender_end_date >= '" + serverTimeStamp + "'";
            } else {
                dateCondition = " date(startdate) <= '" + serverDate + "' AND enddate >= '" + serverTimeStamp + "'";
            }
            StringBuilder query = new StringBuilder(
                    "select * from auction where ");
            query.append(dateCondition);
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
                        resultSet.getInt("updatedBy"),
                        resultSet.getString("status"),
                        resultSet.getBoolean("isTender") ? 1 : 0,
                        resultSet.getTimestamp("tender_start_date"),
                        resultSet.getTimestamp("tender_end_date"));
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

    public AuctionResponseDTO updateAuction(UpdateAuctionDTO auctionDTO)
            throws SQLException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        AuctionResponseDTO auctionResponseDTO = new AuctionResponseDTO();
        String query = "";
        query = "UPDATE auction SET dept_id = ?, auction_type_id= ?, "
                + "auction_name= ?, auction_des= ?, startdate= ?, enddate= ?, catalog= ?,"
                + " status= ?, updatedby= ?, isTender=?, tender_start_date=?, tender_end_date=?  WHERE id= ?";

        try {
            int parameterIndex = 1;

            connection = new ConnectionPool().getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection
                    .prepareStatement(query);

            preparedStatement.setInt(parameterIndex++, auctionDTO.getDeptId());

            preparedStatement.setInt(parameterIndex++,
                    auctionDTO.getAuctionTypeId());

            preparedStatement.setString(parameterIndex++, auctionDTO.getName());

            preparedStatement.setString(parameterIndex++,
                    auctionDTO.getDescription());

            preparedStatement.setTimestamp(parameterIndex++,
                    auctionDTO.getStartDate());

            preparedStatement.setTimestamp(parameterIndex++,
                    auctionDTO.getEndDate());

            preparedStatement.setString(parameterIndex++,
                    auctionDTO.getCatalog());

            preparedStatement.setString(parameterIndex++,
                    auctionDTO.getStatus());

            preparedStatement.setInt(parameterIndex++,
                    auctionDTO.getUpdatedBy())

            ;
            preparedStatement.setBoolean(parameterIndex++,
                    auctionDTO.getTender());

            if (auctionDTO.getTender()) {
                preparedStatement.setTimestamp(parameterIndex++,
                        auctionDTO.getTenderStartDate());
                preparedStatement.setTimestamp(parameterIndex++,
                        auctionDTO.getTenderEndDate());
            } else {
                preparedStatement.setTimestamp(parameterIndex++, null);
                preparedStatement.setTimestamp(parameterIndex++, null);
            }

            preparedStatement.setInt(parameterIndex++, auctionDTO.getId());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }

            auctionResponseDTO.setId(auctionDTO.getId());

        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return auctionResponseDTO;

    }
}
