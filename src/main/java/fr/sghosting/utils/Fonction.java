package fr.sghosting.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Instant;
import java.util.Objects;

public class Fonction {

    public EmbedBuilder ClientJoin(User user) {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("**SG Hosting :**")
                .setDescription("Bienvenue " + user.getAsMention() + " sur le serveur discord de **" + Objects.requireNonNull(user.getJDA().getGuildById(Consts.guildID)).getName() + "**" +
                        "\n" +
                        "\n" +
                        "Pensez à valider notre règlement (<#" + Consts.rulesChannel + ">) pour voir accéder à la totalité de notre discord.")
                .setColor(Color.CYAN)
                .setFooter("Bowered By SG Hosting")
                .setTimestamp(Instant.now());

        return embedBuilder;
    }

    public EmbedBuilder erreurCounterDM(User user) {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("**SG Hosting :**")
                .setDescription(user.getAsMention() + ", un problème a été détecté sur le système de count : ARRÊT du BOT ! :warning:")
                .setColor(Color.RED)
                .setFooter("Bowered By SG Hosting")
                .setTimestamp(Instant.now());

        return embedBuilder;
    }

    public EmbedBuilder erreurCounterGuild() {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("**SG Hosting :**")
                .setDescription("Un problème a été détecté sur le système de count : ARRÊT du BOT ! :warning:")
                .setColor(Color.RED)
                .setFooter("Bowered By SG Hosting")
                .setTimestamp(Instant.now());

        return embedBuilder;
    }

    public void stopBot() {

        System.exit(0);
    }

}
