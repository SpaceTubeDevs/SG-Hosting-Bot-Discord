package fr.sghosting.sql;

import fr.sghosting.Main;
import net.dv8tion.jda.api.JDA;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLInsert {

    public void createTicket(String channelName, String channelID, String msg) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

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

    //----------------------------------

    public void createCategoryCounter(String categoryDisplay, String categoryID) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

            System.out.println("A");
        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("INSERT INTO category(categoryDisplay, categoryID) VALUES (?,?)");
            q.setString(1, categoryDisplay);
            q.setString(2, categoryID);

            q.execute();
            q.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------

    public void createVoiceChannelCounter(String type, String canalDisplay, String canalID) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

            System.out.println("A");
        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("INSERT INTO canal(type, canalDisplay, canalID) VALUES (?,?, ?)");
            q.setString(1, type);
            q.setString(2, canalDisplay);
            q.setString(3, canalID);

            q.execute();
            q.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------

    public void createTicket(JDA jda, String ticketID, String userID, String channelID, String msgCloseID, String msgServiceID, String ticketStatusID, String ticketStatus) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

            System.out.println("A");
        }
        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("INSERT INTO ticket(ticketID, userID, channelID, msgCloseID, msgServiceID, ticketStatusID, ticketStatus) VALUES (?,?,?,?,?,?,?)");
            q.setString(1, ticketID);
            q.setString(2, userID);
            q.setString(3, channelID);
            q.setString(4, msgCloseID);
            q.setString(5, msgServiceID);
            q.setString(6, ticketStatusID);
            q.setString(7, ticketStatus);

            q.execute();
            q.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------
}
