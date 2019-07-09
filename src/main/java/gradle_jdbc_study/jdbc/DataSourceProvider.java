package gradle_jdbc_study.jdbc;

import javax.sql.DataSource;

public class DataSourceProvider {
	public static DataSource getDataSource() {
			return MyDataSource.getInstance().getDataSource();
	}
}
