package com.aaacpl.dao;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.lots.CreateLotRequestDTO;
import com.aaacpl.dto.lots.CreateLotResponseDTO;
import com.aaacpl.dto.participator.CreateParticipatorDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserLotMapDAO {
    public Boolean insertUserLotMapping(CreateParticipatorDTO createParticipatorDTO) throws SQLException, IOException {
        Boolean isInserted = Boolean.FALSE;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        CreateLotResponseDTO createLotResponseDTO = new CreateLotResponseDTO(0);
        try {
            Iterator<Integer> userIdsIterator = createParticipatorDTO.getUserIds().iterator();
            int counter = 0;
            while (userIdsIterator.hasNext()) {
                int parameterIndex = 1;
                connection = new ConnectionPool().getConnection();
                connection.setAutoCommit(false);
                preparedStatement = connection
                        .prepareStatement("INSERT INTO lot_user_map(user_id, lot_id) VALUES (?,?);");
                preparedStatement.setInt(parameterIndex++,
                        userIdsIterator.next());
                preparedStatement.setInt(parameterIndex++,
                        createParticipatorDTO.getLotId());
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                counter++;
            }

            if (counter == createParticipatorDTO.getUserIds().size()) {
                isInserted = Boolean.TRUE;
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
        return isInserted;
    }

    public List<Integer> getLotsForUser(int userId) throws SQLException, IOException {
        List<Integer> lotIdList = new ArrayList<Integer>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT DISTINCT lot_id FROM lot_user_map where user_id = ")
                    .append(userId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                lotIdList.add(resultSet.getInt("lot_id"));
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
        return lotIdList;
    }

}
