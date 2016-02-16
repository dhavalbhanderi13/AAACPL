package com.aaacpl.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aaacpl.dao.UtilClasses.ConnectionPool;
import com.aaacpl.dto.auction.CreateAuctionDTO;
import com.aaacpl.dto.department.CreateDepartmentRequestDTO;
import com.aaacpl.dto.department.CreateDepartmentResponseDTO;
import com.aaacpl.dto.department.DepartmentDTO;
import com.aaacpl.dto.department.UpdateDepartmentDTO;

public class DepartmentDAO {
	public CreateDepartmentResponseDTO insertDepartment(CreateDepartmentRequestDTO createDepartmentRequestDTO)
			throws SQLException, IOException {
		boolean isCreated = false;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
        CreateDepartmentResponseDTO createDepartmentResponseDTO = new CreateDepartmentResponseDTO();
		try {
			int parameterIndex = 1;
			connection = new ConnectionPool().getPoolConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("INSERT INTO department(name, logo_path, status) VALUES (?,?,?);");
			preparedStatement.setString(parameterIndex++,
					createDepartmentRequestDTO.getName());
			preparedStatement.setString(parameterIndex++,
					createDepartmentRequestDTO.getLogoPath());
			preparedStatement.setString(parameterIndex++, "A");
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
                isCreated = Boolean.TRUE;
            } else {
                connection.rollback();
            }

            try{
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    createDepartmentResponseDTO.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating department failed, no ID obtained.");
                }
            }catch (SQLException e){
                connection.rollback();
                e.printStackTrace();
            }
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return createDepartmentResponseDTO;
	}

	public List<DepartmentDTO> getAllDepartments() throws SQLException,
			IOException {
		List<DepartmentDTO> departmentDTOs = new ArrayList<DepartmentDTO>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = new ConnectionPool().getConnection();
			statement = connection.createStatement();
			StringBuilder query = new StringBuilder("SELECT * FROM department");
			ResultSet resultSet = statement.executeQuery(query.toString());
			// int row = resultSet.getRow();
			while (resultSet.next()) {
				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setId(resultSet.getInt(1));
				departmentDTO.setName(resultSet.getString(2));
				departmentDTO.setLogoPath(resultSet.getString(3));
				departmentDTO.setLogoPath(resultSet.getString(4));
				departmentDTOs.add(departmentDTO);
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
		return departmentDTOs;
	}

	public Boolean updateDepartment(UpdateDepartmentDTO updateDepartmentDTO)
			throws SQLException, IOException {
		return null; // TODO
	}

}
