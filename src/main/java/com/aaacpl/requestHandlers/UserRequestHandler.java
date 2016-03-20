package com.aaacpl.requestHandlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aaacpl.bo.request.user.LoginRequestBO;
import com.aaacpl.bo.request.user.RegistrationRequestBO;
import com.aaacpl.bo.request.user.UpdaterUserBO;
import com.aaacpl.bo.response.LoginResponseBO;
import com.aaacpl.dao.UserTypesDAO;
import com.aaacpl.dao.UsersDAO;
import com.aaacpl.dto.user.LoginResponseDTO;
import com.aaacpl.dto.user.UserTypesDTO;
import com.aaacpl.dto.user.UsersDTO;
import com.aaacpl.exceptions.userServiceExceptions.UserNotFoundException;
import com.aaacpl.rest.response.user.GetTypesResponse;
import com.aaacpl.rest.response.user.GetUserResponse;
import com.aaacpl.rest.response.user.UserLoggedInResponse;
import com.aaacpl.rest.response.user.UserResponseList;
import com.aaacpl.rest.util.EmailService;
import com.aaacpl.validation.UsersValidation;

public class UserRequestHandler {

	public Boolean register(RegistrationRequestBO registrationRequestBO) {

		Boolean isProcessed = Boolean.FALSE;
		UsersDAO usersDAO = new UsersDAO();
		try {
			isProcessed = usersDAO
					.insertUser(buildUsersDTOFromBO(registrationRequestBO));
		} catch (SQLException sq) {
			isProcessed = false;
		} catch (IOException sqlException) {
			isProcessed = false;
		}

		if (isProcessed) {
			EmailService.sendNewUserEmail(registrationRequestBO.getEmail());
		}

		return isProcessed;
	}
	
	public Boolean updateUser(UpdaterUserBO updateRequestBO) {

		Boolean isProcessed = Boolean.FALSE;
		UsersDAO usersDAO = new UsersDAO();
		try {
			isProcessed = usersDAO
					.updateUser(updateRequestBO);
		} catch (SQLException sq) {
			isProcessed = false;
		} catch (IOException sqlException) {
			isProcessed = false;
		}
		return isProcessed;
	}

	public LoginResponseBO login(LoginRequestBO loginRequestBO)
			throws SQLException, IOException {
		UsersValidation usersValidation = new UsersValidation();
		UsersDAO usersDAO = new UsersDAO();
		LoginResponseDTO loginResponseDTO = usersDAO
				.getNamePasswordForLoginValidationForEmailAndStatus(loginRequestBO
						.getEmail());
		LoginResponseBO loginResponseBO = new LoginResponseBO();
		Boolean isValidUser = usersValidation.validateEmailPassword(
				loginRequestBO, loginResponseDTO);
		if (isValidUser) {
			Long sessionId = new Date().getTime();
			usersDAO.updateSessionId(loginResponseDTO.getId(), sessionId);
			loginResponseBO.setSessionId(sessionId);
		}
		loginResponseBO.setValidUser(isValidUser);
		loginResponseBO.setId(loginResponseDTO.getId());
		return loginResponseBO;
	}

	private UsersDTO buildUsersDTOFromBO(RegistrationRequestBO requestBO) {
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setName(requestBO.getName());
		usersDTO.setCompanyName(requestBO.getCompanyName());
		usersDTO.setEmail(requestBO.getEmail());
		usersDTO.setPassword(requestBO.getPassword());
		usersDTO.setMaterial(requestBO.getMaterial());
		usersDTO.setAddress(requestBO.getAddress());
		usersDTO.setCity(requestBO.getCity());
		usersDTO.setCountry(requestBO.getCountry());
		usersDTO.setState(requestBO.getState());
		usersDTO.setPanNumber(requestBO.getPanNumber());
		usersDTO.setVatNumber(requestBO.getVatNumber());
		usersDTO.setPin(requestBO.getPin());
		usersDTO.setRegistrationDate(requestBO.getRegistrationDate());
		usersDTO.setTypeId(requestBO.getTypeId());
		usersDTO.setMobile(requestBO.getMobile());
		usersDTO.setPhone(requestBO.getPhone());
		return usersDTO;
	}

	private GetUserResponse buildUsersResponseFromDTO(UsersDTO usersDTO) {
		GetUserResponse userResponse = new GetUserResponse();
		userResponse.setId(usersDTO.getId());
		userResponse.setTypeId(usersDTO.getTypeId());
		userResponse.setName(usersDTO.getName());
		userResponse.setCompanyName(usersDTO.getCompanyName());
		userResponse.setEmail(usersDTO.getEmail());
		userResponse.setMaterial(usersDTO.getMaterial());
		userResponse.setAddress(usersDTO.getAddress());
		userResponse.setCity(usersDTO.getCity());
		userResponse.setCountry(usersDTO.getCountry());
		userResponse.setState(usersDTO.getState());
		userResponse.setPanNumber(usersDTO.getPanNumber());
		userResponse.setVatNumber(usersDTO.getVatNumber());
		userResponse.setPin(usersDTO.getPin());
		userResponse.setPhone(usersDTO.getPhone());
		userResponse.setMobile(usersDTO.getMobile());
		return userResponse;
	}

	public List<GetTypesResponse> getUserTypes() {
		UserTypesDAO usersTypesDAO = new UserTypesDAO();
		List<GetTypesResponse> getTypesResponses = new ArrayList<GetTypesResponse>();
		try {
			List<UserTypesDTO> userTypesDTOList = usersTypesDAO
					.getAllUserTypes();

			for (UserTypesDTO userTypesDTO : userTypesDTOList) {
				GetTypesResponse getTypesResponse = new GetTypesResponse();
				getTypesResponse.setTypeId(userTypesDTO.getId());
				getTypesResponse.setType(userTypesDTO.getType());
				getTypesResponse.setLabel(userTypesDTO.getLabel());
				getTypesResponses.add(getTypesResponse);
			}
		} catch (SQLException sq) {
			sq.printStackTrace();
		} catch (IOException sqlException) {
			sqlException.printStackTrace();
		}

		return getTypesResponses;

	}

	public GetUserResponse getUserById(int id) throws SQLException,
			IOException, UserNotFoundException {
		UsersDAO usersDAO = new UsersDAO();
		UserTypesDAO userTypesDAO = new UserTypesDAO();
		GetUserResponse userResponse = buildUsersResponseFromDTO(usersDAO
				.getUserById(id));
		userResponse.setUserTypeLabel(userTypesDAO.getLabel(userResponse
				.getTypeId()));
		return userResponse;
	}

	public Boolean logout(int userId) {
		Boolean isLoggedOut = Boolean.FALSE;
		try {
			UsersDAO usersDAO = new UsersDAO();
			isLoggedOut = usersDAO.updateSessionId(userId, null);
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return isLoggedOut;
	}

	public List<UserResponseList> getUsersList() {
		List<UserResponseList> userList = null;
		try {
			UsersDAO usersDAO = new UsersDAO();
			userList = usersDAO.getUsersList();
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (IOException s) {
			s.printStackTrace();
		}
		return userList;
	}

	public Boolean forgotPassword(String emailId) {
		Boolean isProcessed = Boolean.FALSE;
		UsersDAO usersDAO = new UsersDAO();
		try {
			LoginResponseDTO dto = usersDAO
					.getNamePasswordForLoginValidationForEmailAndStatus(emailId);
			if (dto != null && dto.getId() != 0 && dto.getValidUser()
					&& dto.getPassword() != null) {
				isProcessed = EmailService.sendForgotPasswordEmail(emailId,
						dto.getPassword());
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return isProcessed;
	}

	public List<UserLoggedInResponse> getUserLoggedIn() {
		UsersDAO usersDAO = new UsersDAO();
		List<UserLoggedInResponse> userList = null;
		try {
			userList = usersDAO.getUserLoggedIn();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
