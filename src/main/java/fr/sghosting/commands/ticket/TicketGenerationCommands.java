package fr.sghosting.commands.ticket;

import fr.sghosting.Main;
import fr.sghosting.commands.Command;
import fr.sghosting.utils.Consts;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class TicketGenerationCommands {

    @Command(name = "ticketmsg", executorType = Command.ExecutorType.USER)
    private void ticket(Member member, TextChannel tChannel) throws SQLException {

        tChannel = Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getTextChannelById(Consts.ticketChannel);

        if (member.hasPermission(Permission.ADMINISTRATOR)) {

            if (!tChannel.getId().contentEquals(Main.getClassManager().getSqlSelect().getChannel(tChannel.getId())) && !Main.getClassManager().getSqlSelect().hasMSGTicketByChannel(tChannel.getId())) {

                EmbedBuilder embedRules = new EmbedBuilder()
                        .setTitle("__**SG HOSTING SUPPORT**__")
                        .setDescription(
                                "Bonjour / Bonsoir, si vous avez une question, un problème ou si vous ne souhaitez." +
                                        "\n" +
                                        "qu'un renseignement, vous pouvez ouvrir un \"dossier\" pour discuter directement" +
                                        "\n" +
                                        "avec notre équipe, nous sommes disponibles très souvent et nous vous garantissons" +
                                        "\n" +
                                        "une réponse sous 24h maximum." +
                                        "\n" +
                                        "\n" +
                                        "Ce système vous permet de discuter avec nous à l'abris des regards indiscret." +
                                        "\n" +
                                        "\n" +
                                        "Pour ouvrir un dossier, il vous suffit de cliquer sur la réaction \uD83C\uDF9F ci dessous et un" +
                                        "\n" +
                                        "ticket sera créé, vous serez notifié dedans.")
                        .setColor(Color.decode("#08F4DB"));

                String a = tChannel.sendMessage(embedRules.build()).complete().getId();

                Objects.requireNonNull(Main.getJDA().getTextChannelById(Consts.ticketChannel)).addReactionById(a, Consts.emojiTicket).queue();

                Main.getClassManager().getSqlInsert().createTicket(tChannel.getName(), tChannel.getId(), a);
            }
        }
    }
}
