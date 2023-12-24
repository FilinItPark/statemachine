package ru.flanker.handlers;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.flanker.TransmittedData;
import ru.flanker.commands.StartCommands;
import ru.flanker.keyboards.GenerateRandomNumber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 1ommy
 * @version 17.12.2023
 */
public class TelegramRequestHandler extends TelegramLongPollingBot {
    private Map<String, List<TransmittedData>> historyOfMessages = new HashMap<>();


    public void init() throws TelegramApiException {
        this.execute(new SetMyCommands(StartCommands.init(), new BotCommandScopeDefault(), null));
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var message = update.getMessage();

            if (message.hasText()) {
                String text = message.getText();
                String chatId = message.getChatId().toString();


                // cmd: /parse <PAGE> <COUNT_ADVERTISEMENETS>

                if (text.startsWith("/random")) {
                    //random и кнопки с выбором диапазона: 0..50 50..100 100..150 150..200
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    var genRandom = new GenerateRandomNumber();
                    sendMessage.setText("Выберите действие:");
                    sendMessage.setReplyMarkup(genRandom.getKeyboard());

                    execute(sendMessage);
                }

            }
        } else if (update.hasCallbackQuery()) {
            var query = update.getCallbackQuery();

            String callData = query.getData();
            Long chatID = query.getMessage().getChatId();

            sendMessage(callData, chatID.toString());
        }
    }

    private void sendMessage(String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("чет пошло не так при отправке сообщения");
        }
    }

    @Override
    public String getBotUsername() {
        return "jsoupjdbcbot";
    }

    @Override
    public String getBotToken() {
        return "6264596503:AAERj0RR-sOyaWxOETnR970Qs7o7Wdv-geQ";
    }
}
