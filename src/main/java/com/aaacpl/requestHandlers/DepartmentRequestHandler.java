package com.aaacpl.requestHandlers;

import com.aaacpl.bo.request.department.CreateDepartmentRequestBO;
import com.aaacpl.bo.request.department.UpdateDepartmentRequestBO;
import com.aaacpl.dao.DepartmentDAO;
import com.aaacpl.dto.department.CreateDepartmentDTO;
import com.aaacpl.dto.department.DepartmentDTO;
import com.aaacpl.dto.department.UpdateDepartmentDTO;
import com.aaacpl.rest.response.department.DepartmentResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DepartmentRequestHandler {
	public Boolean createDepartment(
			CreateDepartmentRequestBO createDepartmentRequestBO) {
		Boolean isProcessed = Boolean.FALSE;
		DepartmentDAO departmentDAO = new DepartmentDAO();
		CreateDepartmentDTO createDepartmentDTO = new CreateDepartmentDTO();
		createDepartmentDTO.setName(createDepartmentRequestBO.getName());
		createDepartmentDTO
				.setLogoPath(createDepartmentRequestBO.getLogoPath());
		try {
			isProcessed = departmentDAO.insertDepartment(createDepartmentDTO);
		} catch (SQLException sq) {
			isProcessed = false;
		} catch (IOException sqlException) {
			isProcessed = false;
		}

		return isProcessed;
	}

	public List<DepartmentResponse> getAllDepartments() {
		List<DepartmentResponse> departmentResponseList = new ArrayList<DepartmentResponse>();
		try {
			DepartmentDAO departmentDAO = new DepartmentDAO();
			List<DepartmentDTO> departmentDTOs = departmentDAO
					.getAllDepartments();
			departmentResponseList = buildListOfDepartmentResponseFromDTOs(departmentDTOs);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return departmentResponseList;
	}

	private List<DepartmentResponse> buildListOfDepartmentResponseFromDTOs(
			List<DepartmentDTO> departmentDTOs) {
		List<DepartmentResponse> departmentResponseList = new ArrayList<DepartmentResponse>();
		Iterator<DepartmentDTO> iterator = departmentDTOs.iterator();
		while (iterator.hasNext()) {
			DepartmentDTO departmentDTO = iterator.next();
			DepartmentResponse departmentResponse = new DepartmentResponse();
			departmentResponse.setId(departmentDTO.getId());
			departmentResponse.setName(departmentDTO.getName());
			departmentResponse.setLogoPath(departmentDTO.getLogoPath());
			departmentResponseList.add(departmentResponse);
		}
		return departmentResponseList;
	}

	public Boolean updateDepartment(
			UpdateDepartmentRequestBO updateDepartmentRequestBO) {
		Boolean isProcessed = Boolean.FALSE;
		DepartmentDAO departmentDAO = new DepartmentDAO();
		UpdateDepartmentDTO updateDepartmentDTO = new UpdateDepartmentDTO();
		updateDepartmentDTO.setName(updateDepartmentRequestBO.getName());
		updateDepartmentDTO
				.setLogoPath(updateDepartmentRequestBO.getLogoPath());
		try {
			isProcessed = departmentDAO.updateDepartment(updateDepartmentDTO);
		} catch (SQLException sq) {
			isProcessed = false;
		} catch (IOException sqlException) {
			isProcessed = false;
		}

		return isProcessed;
	}
}
