package gradle_jdbc_study;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_jdbc_study_dao.EmployeeDao;
import gradle_jdbc_study_daoimpl.EmployeeDaoimpl;
import gradle_jdbc_study_dto.Employee;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static final Logger log = LogManager.getLogger();
	static EmployeeDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.trace("setUpBeforeClass()");
		dao = new EmployeeDaoimpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test01SelectEmployeeByAll() throws SQLException {
		log.trace("testSelectEmployeeByAll()");
		List<Employee> list = dao.selectEmployeeByAll();
		for (Employee e : list) {
			log.trace(e);
		}
		Assert.assertNotEquals(0, list.size());
	}
}
