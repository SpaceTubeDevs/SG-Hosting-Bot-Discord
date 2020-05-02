package fr.sghosting.sql;

import fr.sghosting.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSelect {

    public String getChannel(String channel) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

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

    //----------------------------------

    public boolean hasMSGTicketByChannel(String channel) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

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

    //----------------------------------

    public boolean hasCategoryCounter(String categoryDisplay) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("SELECT categoryID FROM category WHERE categoryDisplay = ?");

            q.setString(1, categoryDisplay);

            ResultSet rs = q.executeQuery();
            boolean hasMSG = rs.next();
            q.close();
            return hasMSG;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //----------------------------------

    public String getVoiceCounter(String canalDisplay) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();
        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("SELECT canalID FROM canal WHERE canalDisplay = ?");

            q.setString(1, canalDisplay);

            String c = "";
            ResultSet rs = q.executeQuery();

            while (rs.next()) c = rs.getString("canalID");

            q.close();

            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //----------------------------------

    public String getMessageIDByChannelID(String canalDisplay) throws SQLException {

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("SELECT msg FROM message_channel WHERE channelID = ?");

            q.setString(1, canalDisplay);

            String c = "";
            ResultSet rs = q.executeQuery();

            while (rs.next()) c = rs.getString("msg");

            q.close();

            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //----------------------------------

    public int getticketByUserID(String userID) throws SQLException {

        int nbrTicket = 0;

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

        }

        try {
            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("SELECT COUNT(*) FROM ticket WHERE userID = ?");
            q.setString(1, userID);
            ResultSet rs = q.executeQuery();

            if (rs.next()) {
                nbrTicket = rs.getInt("COUNT(*)");
            }

            q.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nbrTicket;
    }

    //----------------------------------

    public String getTicketIDByChannelID(String channelID) throws SQLException {

        String a = "";

        if (Main.getClassManager().getSqlConnection().getConnection().isClosed()) {

            Main.getClassManager().getFonction().stopBotSQL();

        }

        try {

            PreparedStatement q = Main.getClassManager().getSqlConnection().getConnection().prepareStatement("SELECT ticketID FROM ticket WHERE channelID = ?");
            q.setString(1, channelID);

            ResultSet rs = q.executeQuery();
            if (rs.next()) {
                a = rs.getString("ticketID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
    }

}
