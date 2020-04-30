package fr.sghosting.events.counter;

import fr.sghosting.Main;
import fr.sghosting.utils.Consts;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Counter {

    // -> Création du système de count sur le serveur
    public void createMemberCountCategory() {
        Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getCategoryById(Consts.counterCategoryID)).getManager().setName("\uD83D\uDCCA Serveur \uD83D\uDCCA").queue();
        Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getVoiceChannelById(Consts.memberCount)).getManager().setName("\uD83D\uDCC8 Membres: " + this.getAllMember()).queue();
        Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getVoiceChannelById(Consts.onlineMember)).getManager().setName("✅ En ligne: " + this.getOnlineMember()).queue();

    }

    public int getOnlineMember() {
        List<Member> user = Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getMembers().stream().filter((member) -> member.getOnlineStatus() != OnlineStatus.OFFLINE && member.getOnlineStatus() != OnlineStatus.IDLE
        ).collect(Collectors.toList());
        List<Member> userCount = user.stream().filter((member -> !member.getUser().isBot())).collect(Collectors.toList());

        return userCount.size();
    }

    public int getOfflineMember() {
        List<Member> user = Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getMembers().stream().filter((member) -> member.getOnlineStatus() != OnlineStatus.ONLINE).collect(Collectors.toList());
        List<Member> userAll = user.stream().filter((member) -> !member.getUser().isBot()).collect(Collectors.toList());

        return userAll.size();
    }

    public int getAllMember() {
        List<Member> userAll = Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getMembers().stream().filter((member) -> !member.getUser().isBot()).collect(Collectors.toList());

        return userAll.size();
    }
}
