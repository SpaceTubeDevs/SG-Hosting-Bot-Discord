package fr.sghosting.utils;

import fr.sghosting.Main;
import fr.sghosting.commands.CommandManager;
import fr.sghosting.commands.ticket.TicketGenerationCommands;
import fr.sghosting.events.counter.memberChangeStatus;
import fr.sghosting.events.guildMessageRecieved;
import fr.sghosting.events.joinLeaveUser;
import fr.sghosting.events.rules.rulesReaction;
import fr.sghosting.events.ticket.TicketGenerator;

import java.sql.SQLException;

public class BotSetup {

    public void setup() throws SQLException {


        SQL();
        setEvents();
        setCommands();
        others();
    }

    private void SQL() {

        Main.getClassManager().getSqlCredentials().setCredentials();
        Main.getClassManager().getSqlConnection().createConnection();
    }

    private void setEvents() {

        Main.getJDA().addEventListener(new memberChangeStatus());
        Main.getJDA().addEventListener(new rulesReaction());
        Main.getJDA().addEventListener(new joinLeaveUser());
        Main.getJDA().addEventListener(new TicketGenerator());
    }

    private void setCommands() {

        Main.getJDA().addEventListener(new guildMessageRecieved(new CommandManager(Main.getJDA())
                .registerCommands(new TicketGenerationCommands())));
    }

    private void others() throws SQLException {

        Main.getClassManager().getCounter().createMemberCountCategory();
    }
}
