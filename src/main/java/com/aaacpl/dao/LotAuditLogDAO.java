package com.aaacpl.dao;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.liveBidLog.LiveBidLogDTO;
import com.aaacpl.dto.lotAuditLog.LotAuditLogDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LotAuditLogDAO {
    public List<LotAuditLogDTO> getAuditLog(int lotId, Boolean isTender) throws SQLException {
        List<LotAuditLogDTO> lotDTOs = new ArrayList<LotAuditLogDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM lot_audit_log as ll, auction as a WHERE ll.lot_id = ").append(lotId);
            String dateCondition = "";
            if (isTender) {
                dateCondition = " AND a.tender_start_date <= ll.localSystemTime AND a.tender_end_date >= ll.localSystemTime";
            } else {
                dateCondition = " AND a.startdate <= ll.localSystemTime AND a.enddate >= ll.localSystemTime";
            }
            query.append(dateCondition);
            query.append(" ORDER BY ll.id DESC");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                LotAuditLogDTO lotDTO = new LotAuditLogDTO(resultSet.getInt("id"), resultSet.getInt("lot_id"),
                        resultSet.getInt("user_id"), resultSet.getInt("bid_amt"),
                        resultSet.getString("ipAddress"), resultSet.getString("localSystemTime"), resultSet.getBoolean("isAccepted"));
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

    public int getMaxAuditLogAmt(int lotId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        int amount = 0;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT MAX(bid_amt) FROM lot_audit_log as ll, auction as a where ll.lot_id = ").append(lotId);
            query.append(" AND a.tender_start_date <= ll.localSystemTime AND a.tender_end_date >= ll.localSystemTime");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                amount = resultSet.getInt(1);
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
        return amount;
    }

    public LiveBidLogDTO getMaxAuditLogForLot(int lotId, int amt) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        LiveBidLogDTO liveBidLogDTO = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM lot_audit_log where lot_id = ").append(lotId).append(" AND bid_amt = ").append(amt);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                liveBidLogDTO = new LiveBidLogDTO(resultSet.getInt("id"), resultSet.getInt("user_id"),
                        resultSet.getInt("lot_id"), resultSet.getInt("bid_amt"),
                        resultSet.getString("ipAddress"), resultSet.getString("localSystemTime"));
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
        return liveBidLogDTO;
    }
}
