package com.aaacpl.api.services;

import javax.ws.rs.*;
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
import com.aaacpl.rest.response.department.DepartmentResponse;
import com.aaacpl.rest.response.department.UpdateDepartmentResponse;
import com.aaacpl.rest.response.requestAuth.RequestAuthenticationResponse;
import com.aaacpl.rest.util.ResponseGenerator;
import com.aaacpl.validation.RequestValidation;

@Path("/department")
public class DepartmentService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(CreateDepartmentRequest createDepartmentRequest, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
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
                createDepartmentResponse.setSuccessMessage(String
                        .valueOf(createDepartmentResponseBO.getId()));
            } else {
                createDepartmentResponse.setFailureMessage("FAILURE");
                createDepartmentResponse.setSuccessMessage("");
            }
            return ResponseGenerator.generateResponse(createDepartmentResponse);
        } else {
            RequestAuthenticationResponse requestAuthenticationResponse = new RequestAuthenticationResponse();
            requestAuthenticationResponse.setFailureMessage("Unauthorized access");
            return ResponseGenerator.generateResponse(requestAuthenticationResponse);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllDepartments(@HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            DepartmentRequestHandler departmentRequestHandler = new DepartmentRequestHandler();
            DepartmentListResponse departmentListResponse = new DepartmentListResponse();
            departmentListResponse
                    .setDepartmentResponseList(departmentRequestHandler
                            .getAllDepartments());
            return ResponseGenerator.generateResponse(departmentListResponse);
        } else {
            RequestAuthenticationResponse requestAuthenticationResponse = new RequestAuthenticationResponse();
            requestAuthenticationResponse.setFailureMessage("Unauthorized access");
            return ResponseGenerator.generateResponse(requestAuthenticationResponse);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deptInfo/{id}")
    public Response getDepartmentById(@PathParam("id") int id, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            DepartmentRequestHandler departmentRequestHandler = new DepartmentRequestHandler();
            DepartmentResponse departmentResponse = departmentRequestHandler.getDepartmentById(id);
            return ResponseGenerator.generateResponse(departmentResponse);
        } else {
            RequestAuthenticationResponse requestAuthenticationResponse = new RequestAuthenticationResponse();
            requestAuthenticationResponse.setFailureMessage("Unauthorized access");
            return ResponseGenerator.generateResponse(requestAuthenticationResponse);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateDepartment(
            UpdateDepartmentRequest updateDepartmentRequest, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            UpdateDepartmentRequestBO updateDepartmentRequestBO = new UpdateDepartmentRequestBO();

            updateDepartmentRequestBO.setName(updateDepartmentRequest.getName());
            updateDepartmentRequestBO.setLogoPath(updateDepartmentRequest
                    .getLogoPath());
            updateDepartmentRequestBO
                    .setStatus(updateDepartmentRequest.getStatus());
            updateDepartmentRequestBO.setDeptId(updateDepartmentRequest.getId());

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
        } else {
            RequestAuthenticationResponse requestAuthenticationResponse = new RequestAuthenticationResponse();
            requestAuthenticationResponse.setFailureMessage("Unauthorized access");
            return ResponseGenerator.generateResponse(requestAuthenticationResponse);
        }
    }
}
