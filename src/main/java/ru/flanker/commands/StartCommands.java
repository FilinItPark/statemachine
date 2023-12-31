package ru.flanker.commands;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 1ommy
 * @version 24.12.2023
 */
public class StartCommands {

    public static List<BotCommand> init() {
        List<BotCommand> botCommands = new ArrayList<>();

        for (Commands command : Commands.values()) {
            botCommands.add(new BotCommand(command.getCommand(), command.getDescription()));
        }

        return botCommands;
    }
}
