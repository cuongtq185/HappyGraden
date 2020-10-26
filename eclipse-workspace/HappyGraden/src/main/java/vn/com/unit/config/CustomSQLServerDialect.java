package vn.com.unit.config;

import jp.sf.amateras.mirage.dialect.StandardDialect;
//import jp.sf.amateras.mirage.dialect.SQLServerDialect;

public class CustomSQLServerDialect extends StandardDialect {

	@Override
    public String getName() {
        return "sqlserver";
    }
	
	@Override
	public String getCountSql(String sql) {
		return "SELECT COUNT(*) FROM (" + sql + ") temp_table";
	}
	
}
