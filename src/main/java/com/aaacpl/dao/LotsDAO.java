package com.aaacpl.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.aaacpl.bo.request.lots.BidRequestBO;
import com.aaacpl.bo.request.lots.UpdateLotBO;
import com.aaacpl.bo.response.UpdateLotResponseBO;
import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.lotAuditLog.LotAuditLogDTO;
import com.aaacpl.dto.lots.*;
import com.aaacpl.exceptions.lotServiceException.DuplicateTenderBidException;
import com.aaacpl.exceptions.lotServiceException.LotNotFoundException;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;
import com.aaacpl.rest.request.lots.StatusRequest;
import com.aaacpl.rest.response.lots.BidHistoryResponse;
import com.aaacpl.util.DateUtil;

public class LotsDAO {
    public CreateLotResponseDTO createLot(
            CreateLotRequestDTO createLotRequestDTO) throws SQLException,
            IOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        CreateLotResponseDTO createLotResponseDTO = new CreateLotResponseDTO(0);
        try {
            int parameterIndex = 1;
            connection = new ConnectionPool().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("INSERT INTO lot(auction_id, name, description, start_bid, difference_factor, "
                            + "startdate, enddate, createdby, updatedby, status) VALUES (?,?,?,?,?,?,?,?,?,?);");

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
            preparedStatement.setString(parameterIndex++, "A");

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }

            try {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    createLotResponseDTO = new CreateLotResponseDTO(
                            generatedKeys.getInt(1));
                } else {
                    throw new SQLException(
                            "Creating user failed, no ID obtained.");
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

    public List<LotDTO> getAllLots(int auctionId, String status) throws SQLException,
            IOException {
        List<LotDTO> lotDTOs = new ArrayList<LotDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM lot where auction_id = ").append(auctionId);
            if (status != null) {
                query.append(" AND status = '").append(status).append("'");
            }
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                LotDTO lotDTO = new LotDTO(resultSet.getInt("id"),
                        resultSet.getInt("auction_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("start_bid"),
                        resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"),
                        resultSet.getTimestamp("enddate"),
                        resultSet.getInt("createdby"),
                        resultSet.getInt("updatedby"),
                        resultSet.getString("status"));
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

    public List<LotDTO> getLotsByUser(int userId, int auctionId)
            throws SQLException, IOException {
        List<LotDTO> lotDTOs = new ArrayList<LotDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();

            String serverTimeStamp = DateUtil.getCurrentServerTime();
            StringBuilder query = new StringBuilder(
                    "select * from lot where id IN(Select DISTINCT lot_id from lot_user_map where user_id =")
                    .append(userId).append(") AND lot.auction_id = ")
                    .append(auctionId).append(" AND enddate > '" + serverTimeStamp + "' AND lot.status = 'A' ORDER BY startdate ASC");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                LotDTO lotDTO = new LotDTO(resultSet.getInt("id"),
                        resultSet.getInt("auction_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("start_bid"),
                        resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"),
                        resultSet.getTimestamp("enddate"),
                        resultSet.getInt("createdby"),
                        resultSet.getInt("updatedby"),
                        resultSet.getString("status"));
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

    public List<LotByAccessInTenderDTO> getLotsByUser(int userId, int auctionId, Integer isTender)
            throws SQLException, IOException {
        List<LotByAccessInTenderDTO> lotDTOs = new ArrayList<LotByAccessInTenderDTO>();
        Connection connection = null;
        Statement statement = null;
        AuctionDAO auctionDAO = new AuctionDAO();
        AuctionDTO auctionDTO = auctionDAO.getAuctionById(auctionId);
        if (auctionDTO.getIsTender() == 1) {
            try {

                connection = new ConnectionPool().getConnection();
                statement = connection.createStatement();

                StringBuilder query = new StringBuilder(
                        "select lot.id, lot.auction_id, lot.name," +
                                "lot.description, lot.createdby, lot.updatedby, lot.status" +
                                " from lot where lot.id IN(Select DISTINCT lot_id from lot_user_map where user_id =")
                        .append(userId).append(") AND lot.auction_id = ")
                        .append(auctionId).append(" AND lot.status = 'A' ORDER BY lot.startdate ASC");
                ResultSet resultSet = statement.executeQuery(query.toString());

                while (resultSet.next()) {
                    LotByAccessInTenderDTO lotDTO = new LotByAccessInTenderDTO(
                            resultSet.getInt("id"),
                            resultSet.getInt("auction_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("createdby"),
                            resultSet.getInt("updatedby"),
                            resultSet.getString("status"));
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
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM lot where id = ").append(id);
            ResultSet resultSet = statement.executeQuery(query.toString());
            int index = 0;
            while (resultSet.next()) {
                index++;
                lotDTO = new LotDTO(resultSet.getInt("id"),
                        resultSet.getInt("auction_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("start_bid"),
                        resultSet.getInt("difference_factor"),
                        resultSet.getTimestamp("startdate"),
                        resultSet.getTimestamp("enddate"),
                        resultSet.getInt("createdby"),
                        resultSet.getInt("updatedby"),
                        resultSet.getString("status"));
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

    public Boolean insertBid(BidRequestBO bidRequestBO, Boolean isTender) throws SQLException,
            IOException {
        boolean isProcessed = false;
        Connection connection = null;
        try {
            connection = new ConnectionPool().getConnection();
            connection.setAutoCommit(false);
            LotDTO lotDTO = getLotById(bidRequestBO.getLotId());
            AuctionDTO auctionDTO = new AuctionDAO().getAuctionById(lotDTO.getAuctionId());
            if (isTender) {
                Boolean isTenderTimeEnded = DateUtil.compareTimestampWithCurrentDate(auctionDTO.getTenderEndDate()) < 0;
                List<LotAuditLogDTO> auditLogDTOs = new LotAuditLogDAO().getAuditLog(lotDTO.getId(), true);
                Boolean isBidAccepted = auditLogDTOs.isEmpty() && !isTenderTimeEnded;
                if (insertIntoLotAuditLog(connection, bidRequestBO, isBidAccepted)) {
                    isProcessed = Boolean.TRUE;
                }
            } else {
                switch (auctionDTO.getAuctionTypeId()) {
                    case 1: {
                        Boolean isMaxBid = isBidAmtMax(connection, bidRequestBO.getLotId(), bidRequestBO.getBidAmount());
                        Boolean isLotTimeEnded = DateUtil.compareTimestampWithCurrentDate(lotDTO.getEndDate()) < 0;
                        Boolean isBidAccepted = isMaxBid && !isLotTimeEnded;

                        // First Log the bid request in lot_audit_log
                        if (insertIntoLotAuditLog(connection, bidRequestBO, isBidAccepted)) {

                            // returning true because we are not sure if the bid is max
                            isProcessed = Boolean.TRUE;

                            // Check if the current bid Amount is greater than Max
                            if (isBidAccepted) {

                                // If true Insert/Update the Max value in live_audit_log
                                if (insertIntoLiveBidLog(connection, bidRequestBO)) {
                                    isProcessed = Boolean.TRUE;
                                } else {
                                    isProcessed = Boolean.FALSE;
                                }
                            }
                        }
                    }

                    case 2:{
                        Boolean isMinBid = isBidAmtMin(connection, bidRequestBO.getLotId(), bidRequestBO.getBidAmount());
                        Boolean isLotTimeEnded = DateUtil.compareTimestampWithCurrentDate(lotDTO.getEndDate()) < 0;
                        Boolean isBidAccepted = isMinBid && bidRequestBO.getBidAmount() > 0 && !isLotTimeEnded;

                        // First Log the bid request in lot_audit_log
                        if (insertIntoLotAuditLog(connection, bidRequestBO, isBidAccepted)) {

                            // returning true because we are not sure if the bid is max
                            isProcessed = Boolean.TRUE;

                            // Check if the current bid Amount is greater than Max
                            if (isBidAccepted) {

                                // If true Insert/Update the Max value in live_audit_log
                                if (insertIntoLiveBidLog(connection, bidRequestBO)) {
                                    isProcessed = Boolean.TRUE;
                                } else {
                                    isProcessed = Boolean.FALSE;
                                }
                            }
                        }
                    }

                    default:{
                        isProcessed = Boolean.FALSE;
                    }
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

    public Boolean insertTenderBid(BidRequestBO tenderBidRequestBO) throws SQLException,
            IOException {
        boolean isProcessed = false;
        Connection connection = null;
        StatusRequest statusRequest = new StatusRequest();
        statusRequest.setLotid(tenderBidRequestBO.getLotId());
        statusRequest.setCurrentBidMax(0);
        LotStatusDTO lotStatus = getLotStatus(statusRequest);
        Boolean isBidAllowed = lotStatus == null || !lotStatus.getHigestBidUser().equals(tenderBidRequestBO.getUserId());
        if (isBidAllowed) {
            try {
                connection = new ConnectionPool().getConnection();
                connection.setAutoCommit(false);
                Boolean isMaxBid = isBidAmtMax(connection, tenderBidRequestBO.getLotId(), tenderBidRequestBO.getBidAmount());

                // First Log the bid request in lot_audit_log
                if (insertIntoLotAuditLogForTender(connection, tenderBidRequestBO, isMaxBid)) {

                    // returning true because we are not sure if the bid is max
                    isProcessed = Boolean.TRUE;

                    // Check if the current bid Amount is greater than Max

                    // If true Insert/Update the Max value in live_audit_log
                    if (insertIntoLiveBidLogTender(connection, tenderBidRequestBO)) {
                        isProcessed = Boolean.TRUE;
                    } else {
                        isProcessed = Boolean.FALSE;
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
        } else {
            throw new DuplicateTenderBidException("Bid already performed");
        }
        return isProcessed;
    }

    private boolean insertIntoLotAuditLog(Connection connection,
                                          BidRequestBO bidRequestBO, Boolean isMax) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        int parameterIndex = 1;
        boolean isProcessed = false;
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO lot_audit_log(user_id, lot_id, bid_amt, ipAddress, clientSystemTime, localSystemTime, isAccepted) VALUES (?,?,?,?,?,?,?);");

            preparedStatement
                    .setInt(parameterIndex++, bidRequestBO.getUserId());

            preparedStatement.setInt(parameterIndex++, bidRequestBO.getLotId());

            preparedStatement.setLong(parameterIndex++,
                    bidRequestBO.getBidAmount());

            preparedStatement.setString(parameterIndex++,
                    bidRequestBO.getIpAddress());


            preparedStatement.setString(parameterIndex++,
                    bidRequestBO.getLocalSystemTime());

            preparedStatement.setString(parameterIndex++,
                    DateUtil.getCurrentServerTime());
            preparedStatement.setBoolean(parameterIndex++,
                    isMax);

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

    private boolean insertIntoLotAuditLogForTender(Connection connection,
                                                   BidRequestBO bidRequestBO, Boolean isMax) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        int parameterIndex = 1;
        boolean isProcessed = false;
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO lot_audit_log(user_id, lot_id, bid_amt, ipAddress, clientSystemTime, localSystemTime, isAccepted) VALUES (?,?,?,?,?,?,?);");

            preparedStatement
                    .setInt(parameterIndex++, bidRequestBO.getUserId());

            preparedStatement.setInt(parameterIndex++, bidRequestBO.getLotId());

            preparedStatement.setLong(parameterIndex++,
                    bidRequestBO.getBidAmount());

            preparedStatement.setString(parameterIndex++,
                    bidRequestBO.getIpAddress());


            preparedStatement.setString(parameterIndex++,
                    bidRequestBO.getLocalSystemTime());

            preparedStatement.setString(parameterIndex++,
                    DateUtil.getCurrentServerTime());
            preparedStatement.setBoolean(parameterIndex++,
                    isMax);

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

    private Boolean isBidAmtMax(Connection connection, Integer lotId, Long bidAmount)
            throws SQLException, IOException {
        Statement statement = null;
        Long maxValue = 0L;
        Boolean isMax = Boolean.FALSE;
        String query = "SELECT MAX(max_value) from live_bid_log WHERE lot_id="
                + lotId;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                maxValue = rs.getLong("MAX(max_value)");
            }
            if (bidAmount > maxValue) {
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

    private Boolean isBidAmtMin(Connection connection, Integer lotId, Long bidAmount)
            throws SQLException, IOException {
        Statement statement = null;
        Long minValue = 0L;
        Boolean isMax = Boolean.FALSE;
        String query = "SELECT MIN(max_value) from live_bid_log WHERE lot_id="
                + lotId;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                minValue = rs.getLong("MIN(max_value)");
            }
            if (bidAmount < minValue) {
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

    private boolean insertIntoLiveBidLog(Connection connection,
                                         BidRequestBO bidRequestBO) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        int parameterIndex = 1;
        boolean isProcessed = false;
        String query = "INSERT INTO live_bid_log(user_id, lot_id, max_value, ipAddress, localSystemTime, clientSystemTime) "
                + " VALUES (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE max_value="
                + bidRequestBO.getBidAmount() + ", user_id=" + bidRequestBO.getUserId()
                + ", clientSystemTime='" + bidRequestBO.getLocalSystemTime() + "', localSystemTime='" + DateUtil.getCurrentServerTime() + "'";
        if (!bidRequestBO.getIpAddress().equals(null) || !bidRequestBO.getIpAddress().equals("")) {
            query = query + ", ipAddress='" + bidRequestBO.getIpAddress() + "'";
        }
        try {
            preparedStatement = connection
                    .prepareStatement(query);


            preparedStatement
                    .setInt(parameterIndex++, bidRequestBO.getUserId());

            preparedStatement.setInt(parameterIndex++, bidRequestBO.getLotId());

            preparedStatement.setLong(parameterIndex++,
                    bidRequestBO.getBidAmount());

            preparedStatement.setString(parameterIndex++,
                    bidRequestBO.getIpAddress());

            preparedStatement.setString(parameterIndex++,
                    DateUtil.getCurrentServerTime());

            preparedStatement.setString(parameterIndex++,
                    bidRequestBO.getLocalSystemTime());

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

    private boolean insertIntoLiveBidLogTender(Connection connection,
                                               BidRequestBO bidRequestBO) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        int parameterIndex = 1;
        boolean isProcessed = false;
        String query = "INSERT INTO live_bid_log(user_id, lot_id, max_value, ipAddress, localSystemTime, clientSystemTime) "
                + " VALUES (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE max_value="
                + bidRequestBO.getBidAmount() + ", user_id=" + bidRequestBO.getUserId()
                + ", clientSystemTime='" + bidRequestBO.getLocalSystemTime() + "', localSystemTime='" + DateUtil.getCurrentServerTime() + "'";
        if (!bidRequestBO.getIpAddress().equals(null) || !bidRequestBO.getIpAddress().equals("")) {
            query = query + ", ipAddress='" + bidRequestBO.getIpAddress() + "'";
        }
        try {
            preparedStatement = connection
                    .prepareStatement(query);


            preparedStatement
                    .setInt(parameterIndex++, bidRequestBO.getUserId());

            preparedStatement.setInt(parameterIndex++, bidRequestBO.getLotId());

            preparedStatement.setLong(parameterIndex++,
                    bidRequestBO.getBidAmount());

            preparedStatement.setString(parameterIndex++,
                    bidRequestBO.getIpAddress());

            preparedStatement.setString(parameterIndex++,
                    DateUtil.getCurrentServerTime());

            preparedStatement.setString(parameterIndex++,
                    bidRequestBO.getLocalSystemTime());

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

    public LotStatusDTO getLotStatus(StatusRequest statusRequest)
            throws SQLException, IOException {
        Statement statement = null;
        Connection connection = null;
        LotStatusDTO lotStatusResponse = null;
        boolean hasHighestBidChanged = false;
        String query = "select l.startdate, l.enddate, lb.user_id, lb.max_value from lot as l, live_bid_log as lb, auction as a where lb.lot_id = l.id and lb.lot_id ="
                + statusRequest.getLotid() + " AND a.startdate <= lb.localSystemTime AND a.enddate >= lb.localSystemTime";
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int maxValue = rs.getInt("lb.max_value");
                int userId = rs.getInt("lb.user_id");
                Timestamp startDate = rs.getTimestamp("l.startdate");
                Timestamp endDate = rs.getTimestamp("l.enddate");
                String currentServerTime = DateUtil.getCurrentServerTime();
                if (statusRequest.getCurrentBidMax() != null) {
                    hasHighestBidChanged = (maxValue > statusRequest
                            .getCurrentBidMax());
                }
                lotStatusResponse = new LotStatusDTO(maxValue, userId,
                        currentServerTime, hasHighestBidChanged, startDate, endDate);
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

    public UpdateLotResponseBO updateLot(UpdateLotBO updateLotRequestBO)
            throws SQLException, IOException {
        UpdateLotResponseBO updateLotResponseBO = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            int parameterIndex = 1;
            connection = new ConnectionPool().getConnection();
            String query = "UPDATE lot SET auction_id = ? , name = ? , description = ? ,"
                    + " start_bid = ? , difference_factor = ? , startdate = ? , enddate = ? ,"
                    + " updatedby = ?, status=? WHERE id = ? ;";
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement(query);

            preparedStatement.setInt(parameterIndex++,
                    updateLotRequestBO.getAuctionId());

            preparedStatement.setString(parameterIndex++,
                    updateLotRequestBO.getName());

            preparedStatement.setString(parameterIndex++,
                    updateLotRequestBO.getDescription());

            preparedStatement.setString(parameterIndex++,
                    updateLotRequestBO.getStartBid());

            preparedStatement.setInt(parameterIndex++,
                    updateLotRequestBO.getDifferenceFactor());

            // Example : String date = "2000-11-21"; YYYY-MM-DD
            preparedStatement.setTimestamp(parameterIndex++,
                    updateLotRequestBO.getStartDate());

            preparedStatement.setTimestamp(parameterIndex++,
                    updateLotRequestBO.getEndDate());

            preparedStatement.setInt(parameterIndex++,
                    updateLotRequestBO.getUpdatedBy());


            preparedStatement.setString(parameterIndex++,
                    updateLotRequestBO.getStatus());

            preparedStatement.setInt(parameterIndex++,
                    updateLotRequestBO.getId());


            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }

            updateLotResponseBO = new UpdateLotResponseBO(
                    updateLotRequestBO.getId());

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
        return updateLotResponseBO;
    }

    public List<BidHistoryResponse> getBidHistoryList(int lotId, Boolean isTender)
            throws SQLException, IOException {
        Statement statement = null;
        Connection connection = null;
        List<BidHistoryResponse> listResponse = new ArrayList<BidHistoryResponse>();
        String query = "SELECT u.name , company_name , user_id , bid_amt , auction_id , localSystemTime, ll.isAccepted "
                + "FROM users as u , lot as l , lot_audit_log as ll, auction as a WHERE "
                + "ll.user_id=u.id AND ll.lot_id=l.id AND ll.lot_id=" + lotId;
        String dateCondition = "";
        if (isTender) {
            dateCondition = " AND a.tender_start_date <= ll.localSystemTime AND a.tender_end_date >= ll.localSystemTime";
        } else {
            dateCondition = " AND a.startdate <= ll.localSystemTime AND a.enddate >= ll.localSystemTime";
        }

        query = query + dateCondition + " ORDER BY ll.id DESC";
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                BidHistoryResponse bid = new BidHistoryResponse(
                        rs.getInt("auction_id"), rs.getInt("user_id"),
                        rs.getString("name"), rs.getString("company_name"),
                        rs.getInt("bid_amt"), rs.getString("localSystemTime"), ((rs.getBoolean("ll.isAccepted")) ? "ACCEPTED"
                        : "REJECTED"));
                listResponse.add(bid);
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
        return listResponse;
    }
}
