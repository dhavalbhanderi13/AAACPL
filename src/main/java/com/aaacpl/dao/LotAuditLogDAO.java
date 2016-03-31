package com.aaacpl.dao;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.lotAuditLog.LotAuditLogDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LotAuditLogDAO {
    public List<LotAuditLogDTO> getAuditLog(int lotId) throws SQLException, IOException {
        List<LotAuditLogDTO> lotDTOs = new ArrayList<LotAuditLogDTO>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM lot_audit_log where lot_id = ").append(lotId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                LotAuditLogDTO lotDTO = new LotAuditLogDTO(resultSet.getInt("id"), resultSet.getInt("lot_id"),
                        resultSet.getInt("user_id"),resultSet.getInt("bid_amt"),
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
}
