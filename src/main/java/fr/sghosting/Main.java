package fr.sghosting;

import fr.sghosting.utils.ClassManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class Main {

    private static JDA jda = null;

    private static final ClassManager classManager = new ClassManager();

    public static void main(String[] args) throws LoginException, InterruptedException, SQLException {

        jda = (new JDABuilder()).setToken(classManager.getToken().setToken()).build();
        jda.awaitReady();
        Runtime.getRuntime().addShutdownHook(new Thread(jda::shutdown));

        classManager.getBotSetup().setup();

        jda.getPresence().setActivity(Activity.playing("*help"));


    }

    public static JDA getJDA() {
        return jda;
    }

    public static ClassManager getClassManager() {
        return classManager;
    }
}
