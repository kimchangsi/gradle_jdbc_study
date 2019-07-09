package gradle_jdbc_study;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;



import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import gradle_jdbc_study_dao.TitleDao;
import gradle_jdbc_study_daoimpl.TitleDaoimpl;
import gradle_jdbc_study_dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static final Logger log = LogManager.getLogger();
	static TitleDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.trace("setUpBeforeClass()");
		dao = new TitleDaoimpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test01SelectTitleByAll() throws SQLException {
		log.trace("testSelectTitleByAll()");
		List<Title> list = dao.selectTitleByAll();
		for (Title t : list) {
			log.trace(t);
		}
		Assert.assertNotEquals(0, list.size());
	}
}

