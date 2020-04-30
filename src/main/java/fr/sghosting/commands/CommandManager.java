package fr.sghosting.commands;

import fr.sghosting.utils.Consts;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public final class CommandManager {

    private static final String prefix = "*";
    private final Map<String, SimpleCommand> commands = new HashMap<>();
    private final String adminRoleID = Consts.adminRole;
    private final Role adminRole;
    private final JDA jda;

    public CommandManager(JDA jda) {
        this.jda = jda;

        adminRole = jda.getRoleById(adminRoleID);
        if (adminRole == null) {
            throw new IllegalStateException("No Admin Role Found");
        }
    }

    public static String getPrefix() {
        return prefix;
    }

    public Collection<SimpleCommand> getCommands() {
        return commands.values();
    }

    public fr.sghosting.commands.CommandManager registerCommands(Object... objects) {
        for (Object object : objects) registerCommand(object);
        return this;
    }

    private void registerCommand(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                method.setAccessible(true);
                SimpleCommand simpleCommand = new SimpleCommand(command.name(), command.description(), command.executorType(), command.category(), command.showInHelp(), command.usableInAllChannels(), object, method);
                commands.put(command.name(), simpleCommand);
            }
        }
    }

    public void handleCommand(User user, String command, Message message, boolean inChannel) {
        Object[] object = getCommand(command);
        if (object[0] == null) return;

        SimpleCommand simpleCommand = (SimpleCommand) object[0];
        if (simpleCommand.getExecutorType().equals(Command.ExecutorType.ADMINS) && message.getMember() != null && !message.getMember().getRoles().contains(adminRole)) {
            message.getChannel().sendMessage(":x: Vous n'avez pas la permission d'utiliser cette commande.").complete();
            return;
        }

        if (simpleCommand.isUsableInAllChannels() == false && inChannel == false) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    message.delete().complete();
                }
            }, 500);
            return;
        }

        try {
            execute(simpleCommand, command, (String[]) object[1], message);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Object[] getCommand(String command) {
        String[] commandSplit = command.split(" ");
        String[] args = new String[commandSplit.length - 1];
        System.arraycopy(commandSplit, 1, args, 0, commandSplit.length - 1);
        SimpleCommand simpleCommand = commands.get(commandSplit[0]);
        return new Object[]{simpleCommand, args};
    }

    private void execute(SimpleCommand simpleCommand, String command, String[] args, Message message) throws Exception {
        Parameter[] parameters = simpleCommand.getMethod().getParameters();
        Object[] objects = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getType() == String[].class) objects[i] = args;
            else if (parameters[i].getType() == User.class) objects[i] = message == null ? null : message.getAuthor();
            else if (parameters[i].getType() == Member.class) objects[i] = message == null ? null : message.getMember();
            else if (parameters[i].getType() == TextChannel.class)
                objects[i] = message == null ? null : message.getTextChannel();
            else if (parameters[i].getType() == PrivateChannel.class)
                objects[i] = message == null ? null : message.getPrivateChannel();
            else if (parameters[i].getType() == Guild.class) objects[i] = message == null ? null : message.getGuild();
            else if (parameters[i].getType() == String.class) objects[i] = command;
            else if (parameters[i].getType() == Message.class) objects[i] = message;
            else if (parameters[i].getType() == JDA.class) objects[i] = jda;
            else if (parameters[i].getType() == fr.sghosting.commands.CommandManager.class) objects[i] = this;
        }
        simpleCommand.getMethod().invoke(simpleCommand.getObject(), objects);
    }

    public JDA getJDA() {
        return this.jda;
    }
}