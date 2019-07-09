package gradle_jdbc_study_daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gradle_jdbc_study.jdbc.ConnectionProvider;
import gradle_jdbc_study_dao.DepartmentDao;
import gradle_jdbc_study_dto.Department;

public class DepartmentDaoimpl implements DepartmentDao {
	static final Logger log = LogManager.getLogger();

	@Override
	public List<Department> selectDepartmentByAll() throws SQLException {
		List<Department> lists = new ArrayList<Department>();
		String sql = "select deptno, deptname, floor from department2";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			log.trace(pstmt);
			while (rs.next()) {
				lists.add(getDepartment(rs));
			}
		}

		return lists;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("deptno"), rs.getString("deptname"), rs.getInt("floor"));
	}

	@Override
	public int insertDepartment(Department dept) throws SQLException {
		String sql = "insert into department2(deptno,deptname,floor) values(?,?,?)";
		int res = -1;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, dept.getDeptNo());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setInt(3, dept.getFloor());
			log.trace(pstmt);
			res = pstmt.executeUpdate();

		}

		return res;
	}

	@Override
	public int deleteDepartment(Department dept) throws SQLException {
		String sql = "delete from department2 where deptno = ?";
		int res = -1;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, dept.getDeptNo());
			log.trace(pstmt);
			res = pstmt.executeUpdate();

		}

		return res;
	}

	@Override
	public int updateDepartment(Department dept) throws SQLException {
		String sql = "update department2 set deptname=?,floor=? where deptno=?";
		int res = -1;

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, dept.getDeptName());
			pstmt.setInt(2, dept.getFloor());
			pstmt.setInt(3, dept.getDeptNo());
			log.trace(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

}
