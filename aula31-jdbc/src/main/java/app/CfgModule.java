package app;

import javax.sql.DataSource;

import orm.DbPasswd;
import orm.DbUser;

import com.google.inject.AbstractModule;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class CfgModule extends AbstractModule{
	@Override
	protected void configure() {
		
		bind(DataSource.class).to(SQLServerDataSource.class);
		bind(String.class).annotatedWith(DbUser.class).toInstance("myAppUser");
		bind(String.class).annotatedWith(DbPasswd.class).toInstance("fcp");
	}
}
