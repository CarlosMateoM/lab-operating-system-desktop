package lab.operating.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import lab.operating.system.dao.CatalogDao;
import lab.operating.system.model.Catalog;
import lab.operating.system.model.Process;
import lab.operating.system.utils.DatabaseUtil;

/**
 *
 * @author C.Mateo
 */
public class CatalogJdbcDao implements CatalogDao {

    private final DataSource dataSource;

    public CatalogJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveCatalog(Catalog catalog) {
        String catalogInsertSql = "INSERT INTO catalog(name, time, date) VALUES (?, ?, ?)";
        String processInsertsql = "INSERT INTO process(pid, name, description, cpu, memory, priority, catalog_id, user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement catalogPreparedStatement = connection.prepareStatement(catalogInsertSql, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);

            catalogPreparedStatement.setString(1, catalog.getName());
            catalogPreparedStatement.setTime(2, DatabaseUtil.convertToLocalTimeToSqlTime(catalog.getTime()));
            catalogPreparedStatement.setDate(3, DatabaseUtil.convertToLocalDateToSqlDate(catalog.getDate()));

            if (catalogPreparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = catalogPreparedStatement.getGeneratedKeys();
                int catalogId = DatabaseUtil.getLastInsertId(resultSet);
                catalog.setId(catalogId);
            } else {
                throw new RuntimeException("error when trying to insert the catalog");
            }

            for (Process process : catalog.getProcessesList()) {

                try (PreparedStatement processPreparedStatement = connection.prepareStatement(processInsertsql)) {

                    processPreparedStatement.setInt(1, process.getpId());
                    processPreparedStatement.setString(2, process.getpName());
                    processPreparedStatement.setString(3, process.getDescription());
                    processPreparedStatement.setInt(4, process.getCpuUsage());
                    processPreparedStatement.setInt(5, process.getPhysicalMemory());
                    processPreparedStatement.setString(6, process.getPriority());
                    processPreparedStatement.setInt(7, catalog.getId());
                    processPreparedStatement.setString(8, process.getUser());

                    processPreparedStatement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
