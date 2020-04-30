package fr.sghosting.commands;

import java.lang.reflect.Method;

public class SimpleCommand {
    private final String name, description;
    private final Command.ExecutorType executorType;
    private final Command.Category category;
    private final boolean showInHelp;
    private final boolean usableInAllChannels;

    private final Object object;
    private final Method method;

    SimpleCommand(String name, String description, Command.ExecutorType executorType, Command.Category category, boolean showinHelp, boolean usableInAllChannels, Object object, Method method) {
        this.name = name;
        this.description = description;
        this.executorType = executorType;
        this.category = category;
        this.showInHelp = showinHelp;
        this.usableInAllChannels = usableInAllChannels;

        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Command.ExecutorType getExecutorType() {
        return executorType;
    }

    public Command.Category getCategory() {
        return category;
    }

    public boolean isShownInHelp() {
        return showInHelp;
    }

    public boolean isUsableInAllChannels() {
        return usableInAllChannels;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
