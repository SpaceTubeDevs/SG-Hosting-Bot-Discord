package fr.sghosting.events.counter;

import fr.sghosting.Main;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class memberChangeStatus extends ListenerAdapter {

    @Override
    public void onUserUpdateOnlineStatus(@Nonnull UserUpdateOnlineStatusEvent event) {

        Main.getClassManager().getCounter().createMemberCountCategory();
    }
}
