package fr.sghosting.events;

import fr.sghosting.Main;
import fr.sghosting.utils.Consts;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Objects;

public class joinLeaveUser extends ListenerAdapter {


    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {

        User user = event.getUser();

        Main.getClassManager().getCounter().createMemberCountCategory();

        if (!user.hasPrivateChannel()) user.openPrivateChannel().complete();
        user.openPrivateChannel().flatMap(msg -> msg.sendMessage(Main.getClassManager().getFonction().ClientJoin(event.getUser()).build())).queue();

    }

    @Override
    public void onGuildMemberLeave(@Nonnull GuildMemberLeaveEvent event) {
        if (event.getUser().isBot()) return;

        Main.getClassManager().getCounter().createMemberCountCategory();

        Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getTextChannelById(Consts.rulesChannel)).removeReactionById(Consts.msgRulesChannel, Consts.emojiRules, event.getUser()).complete();

        System.out.println("A");
    }
}
