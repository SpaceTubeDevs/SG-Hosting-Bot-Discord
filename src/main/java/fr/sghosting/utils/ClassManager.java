package fr.sghosting.utils;

import fr.sghosting.events.counter.Counter;
import fr.sghosting.sql.SQLConnection;
import fr.sghosting.sql.SQLCredentials;
import fr.sghosting.sql.SQLInsert;
import fr.sghosting.sql.SQLSelect;

public class ClassManager {

    private final BotSetup botSetup = new BotSetup();

    private final BotToken token = new BotToken();

    private final SQLCredentials sqlCredentials = new SQLCredentials();

    private final SQLConnection sqlConnection = new SQLConnection();

    private final Counter counter = new Counter();

    private final Fonction fonction = new Fonction();

    private final SQLInsert sqlInsert = new SQLInsert();

    private final SQLSelect sqlSelect = new SQLSelect();


    public BotSetup getBotSetup() {
        return botSetup;
    }

    public BotToken getToken() {
        return token;
    }

    public SQLCredentials getSqlCredentials() {
        return sqlCredentials;
    }

    public SQLConnection getSqlConnection() {
        return sqlConnection;
    }

    public Counter getCounter() {
        return counter;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public SQLInsert getSqlInsert() {
        return sqlInsert;
    }

    public SQLSelect getSqlSelect() {
        return sqlSelect;
    }
}
