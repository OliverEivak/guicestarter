package com.olivereivak.starter.guice.modules.h2;

import javax.inject.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider implements Provider<Connection> {

    @Override
    public Connection get() {
        try {
            Class.forName("org.h2.Driver");

            // DATABASE_TO_UPPER=false
            // don't uppercase table names

            // DB_CLOSE_DELAY=-1
            // don't close database when last connection is closed
            return DriverManager.getConnection("jdbc:h2:~/test;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1", "sa", "");

//            return DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
//            return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
