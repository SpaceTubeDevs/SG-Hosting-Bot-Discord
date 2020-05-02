package fr.sghosting.events.ticket;

import fr.sghosting.Main;
import fr.sghosting.utils.Consts;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Random;

public class TicketGenerator extends ListenerAdapter {

    private final int ticketMax = 5;
    private final String catDisplay = "\uD83D\uDCC1 | Dossiers ";
    private final String[] serverEmote = Consts.serveurEmote.split(":");
    private final String[] sqlEmote = Consts.sqlEmote.split(":");
    private int ticketID = 0;

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

        if (Objects.requireNonNull(event.getUser()).isBot()) return;

        try {
            if (event.getMessageId().contentEquals(Main.getClassManager().getSqlSelect().getMessageIDByChannelID(event.getChannel().getId()))) {

                Objects.requireNonNull(Main.getJDA().getTextChannelById(Consts.ticketChannel)).removeReactionById(Main.getClassManager().getSqlSelect()
                        .getMessageIDByChannelID(event.getTextChannel().getId()), Consts.emojiTicket, event.getUser()).complete();

                if (Main.getClassManager().getSqlSelect().getticketByUserID(event.getUserId()) >= this.ticketMax) {

                    if (!event.getUser().hasPrivateChannel()) event.getUser().openPrivateChannel().complete();
                    event.getUser().openPrivateChannel().flatMap(msg -> msg.sendMessage(Main.getClassManager().getFonction().erreurNewTicket(event.getUser()).build())).queue();
                } else {

                    this.ticketID = new Random().nextInt((((1000 - 10) + 1) + 10));

                    TextChannel channel = Objects.requireNonNull(Objects.requireNonNull(
                            Main.getJDA().getGuildById(Consts.guildID)).getCategoryById(Consts.catFoldeBase)).createTextChannel(Consts.ticketPublic + "│ticket-" + this.ticketID)
                            .addPermissionOverride(Objects.requireNonNull(event.getMember()),
                                    EnumSet.of(Permission.MESSAGE_READ,
                                            Permission.MESSAGE_HISTORY), null)
                            .complete();

                    channel.sendMessage(Objects.requireNonNull(event.getUser()).getAsMention()).queue();

                    String msgIDMsgStart = channel.sendMessage(Main.getClassManager().getFonction().msgCloseTicket().build()).complete().getId();
                    String msgIDService = channel.sendMessage(Main.getClassManager().getFonction().msgServiceTicket(event.getUser()).build()).complete().getId();


                    Objects.requireNonNull(Main.getJDA().getTextChannelById(channel.getId())).addReactionById(msgIDMsgStart, Consts.closeTicket).complete();

                    Objects.requireNonNull(Main.getJDA().getTextChannelById(channel.getId())).addReactionById(msgIDService, Consts.serverGames).complete();
                    Objects.requireNonNull(Main.getJDA().getTextChannelById(channel.getId())).addReactionById(msgIDService, Consts.serveurEmote).complete();
                    Objects.requireNonNull(Main.getJDA().getTextChannelById(channel.getId())).addReactionById(msgIDService, Consts.sqlEmote).complete();
                    Objects.requireNonNull(Main.getJDA().getTextChannelById(channel.getId())).addReactionById(msgIDService, Consts.domaine).complete();
                    Objects.requireNonNull(Main.getJDA().getTextChannelById(channel.getId())).addReactionById(msgIDService, Consts.mail).complete();

                    while (Main.getClassManager().getSqlSelect().getTicketIDByChannelID(event.getChannel().getId()).contentEquals(Consts.ticketPublic + "│ticket-" + this.ticketID)) {

                        this.ticketID = new Random().nextInt((((1000 - 10) + 1) + 10));
                    }

                    Main.getClassManager().getSqlInsert().createTicket(Main.getJDA(), Consts.ticketPublic + "│ticket-" + this.ticketID, event.getUserId(), channel.getId(), msgIDMsgStart, msgIDService, "0", "public");
                }
            } else {

                return;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
