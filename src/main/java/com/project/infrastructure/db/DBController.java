package com.project.infrastructure.db;

import com.project.infrastructure.db.util.ResultSetMapper;
import com.project.infrastructure.execution.logger.AllureLogger;
import com.project.infrastructure.utils.CustomUtils;
import io.qameta.allure.Step;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class DBController extends BaseDB{
    final static Logger LOG = LoggerFactory.getLogger(DBController.class);

    @Step(value = "Run Select Query [ {queryString} ]")
    public static <T> T runSelectQuery(String queryString, Class<T> type) {
        ResultSet resultSet = getResultSet(queryString);
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        List<T> pojoList = resultSetMapper.mapResultSetToObject(resultSet, type);
        if (pojoList == null) {
            Assert.fail("Result is empty. Please check if database table is empty [" + queryString + "]");
        }
        if (pojoList.size() > 1) {
            AllureLogger.logSQLResponseAsXML(CustomUtils.jaxbObjectListToXML(pojoList));
            Assert.fail("ResultSet has more than one record.[" + queryString + "]");
        }
        AllureLogger.logSQLResponseAsXML(CustomUtils.jaxbObjectToXML(pojoList.get(0)));
        return pojoList.get(0);
    }

    @Step(value = "Run Insert Query [ {queryString} ]")
    public static void runInsertQuery(String queryString) {
        try {
            Statement statement = getBasicDataSourceSQLServerDriver().getConnection().createStatement();
            statement.execute(queryString);
        } catch (Throwable e) {
            throw new RuntimeException("DBController ERROR: " + e.getMessage(), e);
        }
    }

    @Step(value = "Run Select Query [ {queryString} ]")
    public static <T> List<T> runSelectQueryGetAllRecords(String queryString, Class<T> type) {
        ResultSet resultSet = getResultSet(queryString);
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        List<T> pojoList = resultSetMapper.mapResultSetToObject(resultSet, type);
        if (pojoList == null) {
            AllureLogger.logSQLResponseAsXML("Result is empty [" + queryString + "]");
        } else {
            AllureLogger.logSQLResponseAsXML(CustomUtils.jaxbObjectListToXML(pojoList));
        }
        return pojoList;

    }

    @Step(value = "Run Remove Update Query [ {queryString} ]")
    public static int runRemoveUpdateQuery(String queryString) {
        String result;
        BasicDataSource dataSource = getBasicDataSourceSQLServerDriver();
        int resultSet;
        try (Statement statement = dataSource.getConnection().createStatement();) {
            resultSet = statement.executeUpdate(queryString);
            result = "Run Query{" + queryString + "} Affected Records [" + resultSet + "]";
            LOG.info(result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        AllureLogger.logInfo(result);
        return resultSet;
    }

}
