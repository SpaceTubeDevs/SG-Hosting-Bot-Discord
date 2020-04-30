import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    private static JDA jda = null;

    public static void main(String[] args) throws LoginException, InterruptedException {

        jda = (new JDABuilder()).setToken("").build();
        jda.awaitReady();
        Runtime.getRuntime().addShutdownHook(new Thread(jda::shutdown));

        jda.getPresence().setActivity(Activity.playing("*help"));

    }

    public static JDA getJDA() {
        return jda;
    }

}
