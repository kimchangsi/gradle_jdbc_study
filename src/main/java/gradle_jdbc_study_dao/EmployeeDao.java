package gradle_jdbc_study_dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_study_dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll() throws SQLException;
	int insertEmployee(Employee emp) throws SQLException;
	int deleteEmployee(Employee emp) throws SQLException;
	int updateEmployee(Employee emp) throws SQLException;
}
