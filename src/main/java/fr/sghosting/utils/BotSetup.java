package fr.sghosting.utils;

import fr.sghosting.Main;

public class BotSetup {

    public void setup() {

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

    }

    private void setCommands() {

    }

    private void others() {

    }
}
