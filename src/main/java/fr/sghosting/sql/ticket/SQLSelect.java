package fr.sghosting.sql.ticket;

import fr.sghosting.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSelect {

    public String getChannel(String channel) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBot();

            System.out.println("A");
        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("SELECT ChannelID FROM message_channel WHERE ChannelID = ?");

            q.setString(1, channel);

            String c = "";
            ResultSet rs = q.executeQuery();

            while (rs.next()) c = rs.getString("ChannelID");

            q.close();

            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean hasMSGTicketByChannel(String channel) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBot();

            System.out.println("A");
        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("SELECT msg FROM message_channel WHERE ChannelID = ?");

            q.setString(1, channel);

            ResultSet rs = q.executeQuery();
            boolean hasMSG = rs.next();
            q.close();
            return hasMSG;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
