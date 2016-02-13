package com.aaacpl.dao;

import com.aaacpl.dto.user.UserTypesDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hp on 07-02-2016.
 */
public interface IUserTypesDAO {
	List<UserTypesDTO> getAllUserTypes() throws SQLException, IOException;
}
