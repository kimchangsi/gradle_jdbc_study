package gradle_jdbc_study_dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_study_dto.Title;

public interface TitleDao {
	List<Title> selectTitleByAll() throws SQLException;

	int insertTitle(Title tit) throws SQLException;

	int deleteTitle(Title tit) throws SQLException;

	int updateTitle(Title tit) throws SQLException;
}
