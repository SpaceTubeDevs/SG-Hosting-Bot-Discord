package fr.sghosting.sql;

import fr.sghosting.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private Connection connection;

    public void createConnection() {

        if (!isConnected()) {

            try {

                this.connection = DriverManager.getConnection(
                        "jdbc:mysql://" +
                                Main.getClassManager().getSqlCredentials().getHostname() +
                                ":" +
                                Main.getClassManager().getSqlCredentials().getPort() +
                                "/" +
                                Main.getClassManager().getSqlCredentials().getDatabaseName(),
                        Main.getClassManager().getSqlCredentials().getUserName(),
                        Main.getClassManager().getSqlCredentials().getPassword()
                );

                System.out.println("Connexion SQL Etablie");
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public void destroyConnection() {

        if (isConnected()) {

            try {

                this.connection.close();

                System.out.println("Connexion SQL Arretée");
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {

        return this.connection != null;
    }

    public Connection getConnection() {
        return connection;
    }
}
