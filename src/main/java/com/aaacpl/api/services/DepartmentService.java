package com.aaacpl.api.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aaacpl.bo.request.department.CreateDepartmentRequestBO;
import com.aaacpl.bo.request.department.UpdateDepartmentRequestBO;
import com.aaacpl.bo.response.CreateDepartmentResponseBO;
import com.aaacpl.requestHandlers.DepartmentRequestHandler;
import com.aaacpl.rest.request.department.CreateDepartmentRequest;
import com.aaacpl.rest.request.department.UpdateDepartmentRequest;
import com.aaacpl.rest.response.department.CreateDepartmentResponse;
import com.aaacpl.rest.response.department.DepartmentListResponse;
import com.aaacpl.rest.response.department.UpdateDepartmentResponse;
import com.aaacpl.rest.util.ResponseGenerator;

@Path("/department")
public class DepartmentService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create(CreateDepartmentRequest createDepartmentRequest) {
		CreateDepartmentRequestBO createDepartmentRequestBO = new CreateDepartmentRequestBO();
		createDepartmentRequestBO.setName(createDepartmentRequest.getName());
		createDepartmentRequestBO.setLogoPath(createDepartmentRequest
				.getLogoPath());
		DepartmentRequestHandler departmentRequestHandler = new DepartmentRequestHandler();
		CreateDepartmentResponse createDepartmentResponse = new CreateDepartmentResponse();
		CreateDepartmentResponseBO createDepartmentResponseBO = departmentRequestHandler
				.createDepartment(createDepartmentRequestBO);
		if (createDepartmentResponseBO.getId() != 0) {
			createDepartmentResponse.setFailureMessage("");
			createDepartmentResponse.setSuccessMessage(String.valueOf(createDepartmentResponseBO.getId()));
		} else {
			createDepartmentResponse.setFailureMessage("FAILURE");
			createDepartmentResponse.setSuccessMessage("");
		}
		return ResponseGenerator.generateResponse(createDepartmentResponse);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getAllDepartments() {
		DepartmentRequestHandler departmentRequestHandler = new DepartmentRequestHandler();
		DepartmentListResponse departmentListResponse = new DepartmentListResponse();
		departmentListResponse
				.setDepartmentResponseList(departmentRequestHandler
						.getAllDepartments());
		return ResponseGenerator.generateResponse(departmentListResponse);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateDepartment(
			UpdateDepartmentRequest updateDepartmentRequest) {
		UpdateDepartmentRequestBO updateDepartmentRequestBO = new UpdateDepartmentRequestBO();

		updateDepartmentRequestBO.setName(updateDepartmentRequest.getName());
		updateDepartmentRequestBO.setLogoPath(updateDepartmentRequest
				.getLogoPath());

		DepartmentRequestHandler departmentRequestHandler = new DepartmentRequestHandler();
		UpdateDepartmentResponse updateResponse = new UpdateDepartmentResponse();
		if (departmentRequestHandler
				.updateDepartment(updateDepartmentRequestBO)) {
			updateResponse.setFailureMessage("");
			updateResponse.setSuccessMessage("SUCCESS");
		} else {
			updateResponse.setFailureMessage("FAILURE");
			updateResponse.setSuccessMessage("");
		}
		return ResponseGenerator.generateResponse(updateResponse);
	}

}
