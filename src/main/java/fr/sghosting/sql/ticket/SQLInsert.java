package fr.sghosting.sql.ticket;

import fr.sghosting.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLInsert {

    public void createTicket(String channelName, String channelID, String msg) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBot();

            System.out.println("A");
        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("INSERT INTO message_channel(channelName, channelID, msg) VALUES (?,?,?)");
            q.setString(1, channelName);
            q.setString(2, channelID);
            q.setString(3, msg);

            q.execute();
            q.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
