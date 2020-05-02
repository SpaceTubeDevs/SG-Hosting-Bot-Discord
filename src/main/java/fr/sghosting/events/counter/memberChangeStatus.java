package fr.sghosting.events.counter;

import fr.sghosting.Main;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.sql.SQLException;

public class memberChangeStatus extends ListenerAdapter {

    @Override
    public void onUserUpdateOnlineStatus(@Nonnull UserUpdateOnlineStatusEvent event) {

        try {
            Main.getClassManager().getCounter().createMemberCountCategory();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
