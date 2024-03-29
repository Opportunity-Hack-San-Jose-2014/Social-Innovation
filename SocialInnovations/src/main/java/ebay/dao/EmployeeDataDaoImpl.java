package ebay.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import ebay.data.Employee;
import ebay.utilities.ApplicationUtility;
import ebay.utilities.DatabaseConnection;

@Repository
public class EmployeeDataDaoImpl implements EmployeeDataDao {

	@Override
	public Employee getEmployee() throws IOException, SQLException {
		// TODO Auto-generated method stub

		
		Employee emp= new Employee();
		
		Connection dbCon = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			dbCon = DatabaseConnection.getConnection();
			// getting PreparedStatment to execute query
			stmt = dbCon.prepareStatement(ApplicationUtility
					.getPropertyValue("getEmployee"));

			// Resultset returned by query
			rs = stmt.executeQuery(ApplicationUtility
					.getPropertyValue("getEmployee"));

			while (rs.next()) {
				emp.setFirstName(rs.getString(4));
				emp.setEmail(rs.getString(3));

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			// close connection ,stmt and resultset here
			/*rs.close();
			stmt.close();*/
			
			DatabaseConnection.closeConnection(dbCon);
			
		}

		return emp;
	}

}
