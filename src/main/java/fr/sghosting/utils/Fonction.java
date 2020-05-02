package fr.sghosting.utils;

import fr.sghosting.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Instant;
import java.util.Objects;

public class Fonction {

    private final String[] serverEmote = Consts.serveurEmote.split(":");
    private final String[] sqlEmote = Consts.sqlEmote.split(":");

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

    public EmbedBuilder erreurSQLDM(User user) {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("**SG Hosting :**")
                .setDescription(user.getAsMention() + ", un problème a été détecté sur la connection SQL : ARRÊT du BOT ! :warning:")
                .setColor(Color.RED)
                .setFooter("Bowered By SG Hosting")
                .setTimestamp(Instant.now());

        return embedBuilder;
    }

    public EmbedBuilder erreurSQLGuild() {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("**SG Hosting :**")
                .setDescription("Un problème a été détecté sur la connection SQL : ARRÊT du BOT ! :warning:")
                .setColor(Color.RED)
                .setFooter("Bowered By SG Hosting")
                .setTimestamp(Instant.now());

        return embedBuilder;
    }

    public void stopBotSQL() {

        Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getTextChannelById(Consts.logsSgbot))

                .sendMessage(Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getRoleById(Consts.directorRole)).getAsMention()).complete()
                .getJDA().getGuildById(Consts.guildID)).getTextChannelById(Consts.logsSgbot)).sendMessage(this.erreurSQLGuild().build()).complete();

        for (Member member : Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getMembers()) {

            if (member.getRoles().contains(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getRoleById(Consts.directorRole))) {

                if (!member.getUser().hasPrivateChannel()) member.getUser().openPrivateChannel().complete();
                member.getUser().openPrivateChannel().flatMap(msg -> msg.sendMessage(this.erreurSQLDM(member.getUser()).build())).queue();
            }
        }

        System.exit(0);
    }

    public void stopBotCounter() {

        Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getTextChannelById(Consts.logsSgbot))

                .sendMessage(Objects.requireNonNull(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getRoleById(Consts.directorRole)).getAsMention()).complete()
                .getJDA().getGuildById(Consts.guildID)).getTextChannelById(Consts.logsSgbot)).sendMessage(Main.getClassManager().getFonction().erreurCounterGuild().build()).complete();

        for (Member member : Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getMembers()) {

            if (member.getRoles().contains(Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getRoleById(Consts.directorRole))) {

                if (!member.getUser().hasPrivateChannel()) member.getUser().openPrivateChannel().complete();
                member.getUser().openPrivateChannel().flatMap(msg -> msg.sendMessage(Main.getClassManager().getFonction().erreurCounterDM(member.getUser()).build())).queue();
            }
        }

        System.exit(0);
    }

    public EmbedBuilder erreurNewTicket(User user) {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("**SG Hosting :**")
                .setDescription(user.getAsMention() + ", vous avez atteint la limite de ticket ! :warning:" +
                        "\n" +
                        "\n" +
                        "Pensez à fermer vos ticket avez d'en ouvrir d'autres ^^")
                .setColor(Color.RED)
                .setFooter("Bowered By SG Hosting")
                .setTimestamp(Instant.now());

        return embedBuilder;
    }

    public EmbedBuilder msgCloseTicket() {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("__**Votre dossier**__")
                .setDescription("Si vous avez accidentellement ouvert un ticket ou souhaitez fermer votre ticket, veuillez cliquer sur " + Consts.closeTicket + " ci-dessous.")
                .setColor(Color.RED)
                .setFooter("Bowered By SG Hosting");

        return embedBuilder;
    }

    public EmbedBuilder msgServiceTicket(User user) {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("__**Votre dossier**__")
                .setDescription("Création du dossier" +
                        "\n" +
                        user.getAsMention() + ", vous venez d'ouvrir une demande de support. Pour vous aider au mieux, pouvez vous nous catégoriser votre problème à l'aider des reéactions afin que notre support puisse vous aider au mieux." +
                        "\n" +
                        "\n" +
                        "Notre équipe d'assistance s'efforcera de répondre à votre ticket dans les 24 à 48 heures. Cependant, nous pouvons parfois être extrêmement occupés et les réponses peuvent être retardées." +
                        "\n" +
                        "\n" +
                        Consts.serverGames + " • Serveurs de Jeux" +
                        "\n" +
                        Objects.requireNonNull(Main.getJDA().getEmoteById(serverEmote[1])).getAsMention() + " • VPS" +
                        "\n" +
                        Objects.requireNonNull(Main.getJDA().getEmoteById(sqlEmote[1])).getAsMention() + " • SQL" +
                        "\n" +
                        Consts.domaine + " • Nom de domaine" +
                        "\n" +
                        Consts.mail + " • Email")
                .setColor(Color.YELLOW)
                .setFooter("Bowered By SG Hosting");

        return embedBuilder;
    }

}
