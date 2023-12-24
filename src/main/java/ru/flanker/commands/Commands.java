package ru.flanker.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
enum Commands {
    START("/start", "запуск бота"),
    GET("/get", "Получить список объявлений"),
    HISTORY("/history", "Получить историю сообщений");

    private final String command;
    private final String description;
}