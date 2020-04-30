package fr.sghosting.utils;

import fr.sghosting.Main;
import fr.sghosting.events.counter.memberChangeStatus;
import fr.sghosting.events.rules.rulesReaction;

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

        Main.getJDA().addEventListener(new memberChangeStatus());
        Main.getJDA().addEventListener(new rulesReaction());
    }

    private void setCommands() {


    }

    private void others() {

        Main.getClassManager().getCounter().createMemberCountCategory();
    }
}
