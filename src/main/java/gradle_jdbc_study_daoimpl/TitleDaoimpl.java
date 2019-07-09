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
import gradle_jdbc_study_dao.TitleDao;
import gradle_jdbc_study_dto.Title;

public class TitleDaoimpl implements TitleDao {
	static final Logger log = LogManager.getLogger();

	@Override
	public List<Title> selectTitleByAll() throws SQLException {
		List<Title> lists = new ArrayList<Title>();
		String sql = "select titno, titname from title2";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			log.trace(pstmt);
			while (rs.next()) {
				lists.add(getTitle(rs));
			}
		}

		return lists;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		return new Title(rs.getInt("titno"), rs.getString("titname"));
	}

	@Override
	public int insertTitle(Title tit) throws SQLException {
		String sql = "insert into title2(titno,titname) values (?,?)";
		int res = -1;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, tit.getTitno());
			pstmt.setString(2, tit.getTitname());
			log.trace(pstmt);
			res = pstmt.executeUpdate();

		}

		return res;
	}

	@Override
	public int deleteTitle(Title tit) throws SQLException {
		String sql = "delete from title2 where titno = ?";
		int res = -1;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, tit.getTitno());
			log.trace(pstmt);
			res = pstmt.executeUpdate();

		}

		return res;
	}

	@Override
	public int updateTitle(Title tit) throws SQLException {
		String sql = "update title2 set titname=? where titno=?";
		int res = -1;
		
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, tit.getTitname());
			pstmt.setInt(2, tit.getTitno());
			log.trace(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

}
