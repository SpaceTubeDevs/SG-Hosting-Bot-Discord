package fr.sghosting.events.counter;

import fr.sghosting.Main;
import fr.sghosting.utils.Consts;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.sql.SQLException;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Counter {

    private final String counterDysplay = "\uD83D\uDCCA Serveur \uD83D\uDCCA";
    private final String counterMember = "\uD83D\uDCC8 Membres: ";
    private final String counterOnline = "✅ En ligne: ";

    private String memberCounterId = "";
    private String memberOnlineID = "";

    // -> Création du système de count sur le serveur
    public void createMemberCountCategory() throws SQLException {


        if (!Main.getClassManager().getSqlSelect().hasCategoryCounter(counterDysplay)) {

            String catID = Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).createCategory(counterDysplay).complete().getId();

            Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getCategoryById(catID)).getManager())
                    .putPermissionOverride(Objects.requireNonNull(Main.getJDA().getRoleById(Consts.clientRole)), EnumSet.of(Permission.VIEW_CHANNEL),
                            EnumSet.of(
                                    Permission.CREATE_INSTANT_INVITE,
                                    Permission.MANAGE_CHANNEL,
                                    Permission.MANAGE_PERMISSIONS,
                                    Permission.MANAGE_WEBHOOKS,
                                    Permission.MESSAGE_WRITE,
                                    Permission.MESSAGE_TTS,
                                    Permission.MESSAGE_MANAGE,
                                    Permission.MESSAGE_EMBED_LINKS,
                                    Permission.MESSAGE_ATTACH_FILES,
                                    Permission.MESSAGE_HISTORY,
                                    Permission.MESSAGE_MENTION_EVERYONE,
                                    Permission.MESSAGE_EXT_EMOJI,
                                    Permission.MESSAGE_ADD_REACTION,
                                    //-----
                                    Permission.VOICE_CONNECT,
                                    Permission.VOICE_SPEAK,
                                    Permission.VOICE_STREAM,
                                    Permission.VOICE_MUTE_OTHERS,
                                    Permission.VOICE_DEAF_OTHERS,
                                    Permission.VOICE_MOVE_OTHERS,
                                    Permission.VOICE_USE_VAD,
                                    Permission.PRIORITY_SPEAKER))

                    .putPermissionOverride(Objects.requireNonNull(Main.getJDA().getRoleById(Consts.everyoneRole)), EnumSet.of(Permission.VIEW_CHANNEL),
                            EnumSet.of(
                                    Permission.CREATE_INSTANT_INVITE,
                                    Permission.MANAGE_CHANNEL,
                                    Permission.MANAGE_PERMISSIONS,
                                    Permission.MANAGE_WEBHOOKS,
                                    Permission.MESSAGE_WRITE,
                                    Permission.MESSAGE_TTS,
                                    Permission.MESSAGE_MANAGE,
                                    Permission.MESSAGE_EMBED_LINKS,
                                    Permission.MESSAGE_ATTACH_FILES,
                                    Permission.MESSAGE_HISTORY,
                                    Permission.MESSAGE_MENTION_EVERYONE,
                                    Permission.MESSAGE_EXT_EMOJI,
                                    Permission.MESSAGE_ADD_REACTION,
                                    //-----
                                    Permission.VOICE_CONNECT,
                                    Permission.VOICE_SPEAK,
                                    Permission.VOICE_STREAM,
                                    Permission.VOICE_MUTE_OTHERS,
                                    Permission.VOICE_DEAF_OTHERS,
                                    Permission.VOICE_MOVE_OTHERS,
                                    Permission.VOICE_USE_VAD,
                                    Permission.PRIORITY_SPEAKER)).queue();

            this.memberCounterId = Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).createVoiceChannel(counterMember + this.getAllMember()).complete().getId();
            this.memberOnlineID = Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).createVoiceChannel(counterOnline + this.getOnlineMember()).complete().getId();

            Main.getClassManager().getSqlInsert().createCategoryCounter(counterDysplay, catID);
            Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getCategoryById(catID)).getManager().setPosition(0).queue();

            Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID))
                    .getVoiceChannelById(memberCounterId)).getManager()
                    .setParent(Objects.requireNonNull(Main.getJDA()
                            .getGuildById(Consts.guildID)).getCategoryById(catID))
                    .sync(Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getCategoryById(catID))).queue();

            Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID))
                    .getVoiceChannelById(memberOnlineID)).getManager()
                    .setParent(Objects.requireNonNull(Main.getJDA()
                            .getGuildById(Consts.guildID)).getCategoryById(catID))
                    .sync(Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getCategoryById(catID))).queue();

            Main.getClassManager().getSqlInsert().createVoiceChannelCounter("voice", counterMember, memberCounterId);
            Main.getClassManager().getSqlInsert().createVoiceChannelCounter("voice", counterOnline, memberOnlineID);

        } else {

            String a = Main.getClassManager().getSqlSelect().getVoiceCounter(counterMember);
            String b = Main.getClassManager().getSqlSelect().getVoiceCounter(counterOnline);

            if (!Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getVoiceChannels().contains(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getVoiceChannelById(a)) || !Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getVoiceChannels().contains(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getVoiceChannelById(b))) {

                Main.getClassManager().getFonction().stopBotCounter();

            } else {

                Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getVoiceChannelById(a)).getManager().setName(counterMember + this.getAllMember()).complete();
                Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getVoiceChannelById(b)).getManager().setName(counterOnline + this.getOnlineMember()).complete();
            }
        }
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
