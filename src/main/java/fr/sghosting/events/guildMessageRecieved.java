package fr.sghosting.events;

import fr.sghosting.commands.CommandManager;
import fr.sghosting.utils.Consts;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class guildMessageRecieved extends ListenerAdapter {

    private final CommandManager commandManager;

    public guildMessageRecieved(CommandManager comandManager) {
        this.commandManager = comandManager;
    }

    // -> event quand réaction
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // -> si event est le bot alors return;
        if (event.getAuthor().equals(event.getJDA().getSelfUser())) return;

        // -> récupération du message
        String message = event.getMessage().getContentDisplay();

        // -> check si le message commande par le prefix du bot
        if (message.startsWith(CommandManager.getPrefix())) {
            String str = event.getMessage().getContentRaw().substring(CommandManager.getPrefix().length());
            this.commandManager.handleCommand(event.getAuthor(), str, event.getMessage(), event.getChannel().getId().contentEquals(Consts.commandChannel));
        }
    }
}
