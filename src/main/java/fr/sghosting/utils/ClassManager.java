package fr.sghosting.utils;

public class ClassManager {

    private final BotSetup botSetup = new BotSetup();

    private final BotToken token = new BotToken();


    public BotSetup getBotSetup() {
        return botSetup;
    }

    public BotToken getToken() {
        return token;
    }

}
