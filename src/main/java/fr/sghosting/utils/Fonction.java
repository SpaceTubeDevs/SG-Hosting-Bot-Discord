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
}
