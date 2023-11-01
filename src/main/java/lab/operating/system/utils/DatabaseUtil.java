package lab.operating.system.utils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author C.Mateo
 */
public class DatabaseUtil {
    
    private final static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(PropertiesUtil.getProperty("db.url"));
        dataSource.setUsername(PropertiesUtil.getProperty("db.user"));
        dataSource.setPassword(PropertiesUtil.getProperty("db.password"));
        dataSource.setInitialSize(1);
        dataSource.setMaxTotal(3);
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
    
    public static int getLastInsertId(ResultSet generatedKeys) throws SQLException {
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        throw new RuntimeException("the generated primary key could not be obtained");
    }
    
    public static Date convertToLocalDateToSqlDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.valueOf(localDate);
    }
    
    public static Time convertToLocalTimeToSqlTime(LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        return Time.valueOf(localTime);
    }

}
