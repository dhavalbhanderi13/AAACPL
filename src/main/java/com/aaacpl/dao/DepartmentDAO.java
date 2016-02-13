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
import com.aaacpl.dto.department.CreateDepartmentDTO;
import com.aaacpl.dto.department.DepartmentDTO;
import com.aaacpl.dto.department.UpdateDepartmentDTO;

public class DepartmentDAO {
	public Boolean insertDepartment(CreateDepartmentDTO createDepartmentDTO)
			throws SQLException, IOException {
		boolean isCreated = false;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			int parameterIndex = 1;
			connection = new ConnectionPool().getPoolConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO department(name, logo_path, status) VALUES (?,?,?);");
			preparedStatement.setString(parameterIndex++,
					createDepartmentDTO.getName());
			preparedStatement.setString(parameterIndex++,
					createDepartmentDTO.getLogoPath());
			preparedStatement.setString(parameterIndex++, "A");
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				isCreated = Boolean.TRUE;
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
		return isCreated;
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
