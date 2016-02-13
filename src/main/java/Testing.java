import java.io.IOException;
import java.sql.SQLException;

import com.aaacpl.dao.UsersDAO;
import com.aaacpl.rest.user.UsersService;

/**
 * Created by Hp on 07-02-2016.
 */
public class Testing {
	public static void main(String args[]) {
		try {

			UsersService usersService = new UsersService();
			System.out.println(usersService.getUserById(1));
			UsersDAO usersDAO = new UsersDAO();
			System.out.println(usersDAO.getUserById(1));
			/*
			 * DepartmentRequestHandler departmentDAO = new
			 * DepartmentRequestHandler(); List depatments =
			 * departmentDAO.getAllDepartments(); depatments.size();
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException d) {
			d.printStackTrace();
		}
	}
}
