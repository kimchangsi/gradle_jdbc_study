package gradle_jdbc_study_daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gradle_jdbc_study.jdbc.ConnectionProvider;
import gradle_jdbc_study_dao.EmployeeDao;
import gradle_jdbc_study_dto.Department;
import gradle_jdbc_study_dto.Employee;
import gradle_jdbc_study_dto.Title;

public class EmployeeDaoimpl implements EmployeeDao {//
	static final Logger log = LogManager.getLogger();

	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		String sql = "select e.empno, e.empname, e.tno titno, t.titname, e.salary,e.gender, e.dno, d.deptname,d.floor,e.hiredate from employee3 e left join title2 t on e.tno=t.titno join department2 d on e.dno=d.deptno;";
		List<Employee> lists = new ArrayList<Employee>();

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			log.trace(pstmt);
			while (rs.next()) {
				lists.add(getEmployeefull(rs));
			}
		}

		return lists;
	}

	private Employee getEmployeefull(ResultSet rs) throws SQLException {
		Title title = new Title(rs.getInt("titno"));
		title.setTitname(rs.getString("titname"));
		Department dept = new Department(rs.getInt("dno"));
		dept.setDeptName(rs.getString("deptname"));
		dept.setFloor(rs.getInt("floor"));
		return new Employee(rs.getInt("empno"), rs.getString("empname"), title, rs.getInt("salary"),
				rs.getString("gender"), dept, rs.getDate("hiredate"));
	}

	@Override
	public int insertEmployee(Employee emp) throws SQLException {
		String sql = "insert into employee3 values(?, ?, ?, ?, ?, ?, ?)";
		int res = -1;
		java.sql.Date date2 = new java.sql.Date(emp.getHiredate().getTime());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setInt(3, emp.getTno().getTitno());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setString(5, emp.getGender());
			pstmt.setInt(6, emp.getDno().getDeptNo());
			pstmt.setDate(7,  date2);
			log.trace(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteEmployee(Employee emp) throws SQLException {
		String sql = "delete from employee3 where empno = ?";
		int res = -1;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, emp.getEmpNo());
			log.trace(pstmt);
			res = pstmt.executeUpdate();

		}
		return res;
	}

	@Override
	public int updateEmployee(Employee emp) throws SQLException {
		String sql = "update employee3 set empname=?, tno=?, salary=?,gender=?,dno=?,hiredate=? where empno=?";
		int res = -1;
		java.util.Date date = emp.getHiredate();
		java.sql.Date date2 = new java.sql.Date(date.getTime());
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, emp.getTno().getTitno());
			pstmt.setInt(3, emp.getSalary());
			pstmt.setString(4, emp.getGender());
			pstmt.setInt(5, emp.getDno().getDeptNo());
			pstmt.setDate(6,  date2);
			pstmt.setInt(7, emp.getEmpNo());
			log.trace(pstmt);
			res = pstmt.executeUpdate();

		}
		return res;
	}

}
