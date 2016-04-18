package com.aaacpl.requestHandlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aaacpl.bo.request.department.CreateDepartmentRequestBO;
import com.aaacpl.bo.request.department.UpdateDepartmentRequestBO;
import com.aaacpl.bo.response.CreateDepartmentResponseBO;
import com.aaacpl.dao.DepartmentDAO;
import com.aaacpl.dto.department.CreateDepartmentRequestDTO;
import com.aaacpl.dto.department.DepartmentDTO;
import com.aaacpl.dto.department.UpdateDepartmentDTO;
import com.aaacpl.rest.response.department.DepartmentResponse;

public class DepartmentRequestHandler {
	public CreateDepartmentResponseBO createDepartment(
			CreateDepartmentRequestBO createDepartmentRequestBO) {
		DepartmentDAO departmentDAO = new DepartmentDAO();
		CreateDepartmentRequestDTO createDepartmentRequestDTO = new CreateDepartmentRequestDTO();
		createDepartmentRequestDTO.setName(createDepartmentRequestBO.getName());
		createDepartmentRequestDTO.setLogoPath(createDepartmentRequestBO
				.getLogoPath());
		CreateDepartmentResponseBO createDepartmentResponseBO = new CreateDepartmentResponseBO();
		try {
			createDepartmentResponseBO.setId(departmentDAO.insertDepartment(
					createDepartmentRequestDTO).getId());
		} catch (SQLException sq) {
		}

		return createDepartmentResponseBO;
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
			departmentResponse.setStatus(departmentDTO.getStatus());
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
		updateDepartmentDTO.setStatus(updateDepartmentRequestBO.getStatus());
		updateDepartmentDTO.setDeptId(updateDepartmentRequestBO.getDeptId());
		try {
			isProcessed = departmentDAO.updateDepartment(updateDepartmentDTO);
		} catch (SQLException sq) {
			isProcessed = false;
		}

		return isProcessed;
	}

	public DepartmentResponse getDepartmentById(int id) {
		DepartmentResponse departmentResponse = null;
		DepartmentDAO departmentDAO = new DepartmentDAO();
		try {
			DepartmentDTO departmentDTO = departmentDAO.getDepartmentById(id);
			departmentResponse = new DepartmentResponse();
			departmentResponse.setId(departmentDTO.getId());
			departmentResponse.setName(departmentDTO.getName());
			departmentResponse.setLogoPath(departmentDTO.getLogoPath());
			departmentResponse.setStatus(departmentDTO.getStatus());
		} catch (SQLException sqlException){
			sqlException.printStackTrace();
		}


		return departmentResponse;
	}
}
