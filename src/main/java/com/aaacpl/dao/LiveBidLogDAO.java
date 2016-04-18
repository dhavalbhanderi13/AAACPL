package com.aaacpl.dao;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.liveBidLog.LiveBidLogDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LiveBidLogDAO {
    public LiveBidLogDTO getAuditLog(int lotId) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        LiveBidLogDTO liveBidLogDTO = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM live_bid_log where lot_id = ").append(lotId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                liveBidLogDTO = new LiveBidLogDTO(resultSet.getInt("id"), resultSet.getInt("user_id"),
                        resultSet.getInt("lot_id"),resultSet.getInt("max_value"),
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
