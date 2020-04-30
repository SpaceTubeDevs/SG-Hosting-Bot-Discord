package fr.sghosting.events.rules;

import fr.sghosting.Main;
import fr.sghosting.utils.Consts;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Objects;

public class rulesReaction extends ListenerAdapter {


    // -> ajout d'un réaction
    @Override
    public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event) {
        if (event.getUser().isBot()) return;

        // -> test si message = msg du règlement
        if (event.getMessageId().contentEquals(Consts.msgRulesChannel)) {

            // -> type émoji
            if (event.getReactionEmote().isEmoji()) {
                String emoji = event.getReactionEmote().getEmoji();

                // -> test si emoji = émoji rules
                if (Consts.emojiRules.equals(emoji)) {
                    Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).addRoleToMember(event.getMember(), (Objects.requireNonNull(Main.getJDA().getRoleById(Consts.clientRole)))).queue();
                }
            }
        }
    }

    // -> suppresion emoji
    @Override
    public void onGuildMessageReactionRemove(@Nonnull GuildMessageReactionRemoveEvent event) {

        // -> si message = msg règlement
        if (event.getMessageId().contentEquals(Consts.msgRulesChannel)) {

            // -> type emoji
            if (event.getReactionEmote().isEmoji()) {
                String emoji = event.getReactionEmote().getEmoji();

                // -> si réaction du client + présence sur le serveur alors suppréssion émoji + suppresssion du role "RULES" pour le client
                if (Consts.emojiRules.equals(emoji) && Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).getMembers().contains(event.getMember())) {
                    Objects.requireNonNull(Main.getJDA().getGuildById(Consts.guildID)).removeRoleFromMember(Objects.requireNonNull(event.getMember()), Objects.requireNonNull(Main.getJDA().getRoleById(Consts.clientRole))).queue();
                }
            }
        }
    }
}
