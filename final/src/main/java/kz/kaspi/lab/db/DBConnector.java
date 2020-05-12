package kz.kaspi.lab.db;

import kz.kaspi.lab.error.Error;
import java.sql.Connection;
// import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnector {
    private static final String ERROR_1 = "Код ошибки не может быть пустым";

    private Connection connection = null;

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (Exception e) {
                Error.printError(e);
            }
        }
    }

    public Connection getConnection() { return connection; }

}
