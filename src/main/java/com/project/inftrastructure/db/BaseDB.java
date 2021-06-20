package com.project.inftrastructure.db;

import com.project.inftrastructure.utils.property.PropertyLoader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;

public class BaseDB {
    protected static ResultSet getResultSet(String queryString, BasicDataSource dataSource) {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            return rs;
        } catch (Throwable e) {
            throw new RuntimeException("DBController ERROR: " + e.getMessage(), e);
        }
    }

    protected static BasicDataSource getBasicDataSourceSQLServerDriver() {
        Properties props = PropertyLoader.loadProperties("postqresql.properties");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(props.getProperty("datasource.username"));
        dataSource.setPassword(props.getProperty("datasource.password"));
        dataSource.setUrl(props.getProperty("datasource.url"));
        dataSource.setMaxActive(1);
        dataSource.setMaxIdle(1);
        dataSource.setInitialSize(1);
        return dataSource;
    }


}