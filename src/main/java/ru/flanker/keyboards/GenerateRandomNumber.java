package ru.flanker.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 1ommy
 * @version 24.12.2023
 */
public class GenerateRandomNumber {
    public InlineKeyboardMarkup getKeyboard() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("50..100");
        inlineKeyboardButton1.setCallbackData("/gen 50..100");

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("100..150");
        inlineKeyboardButton2.setCallbackData("/gen 100..150");

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("150..200");
        inlineKeyboardButton3.setCallbackData("/gen 150..200");

        rowInline1.add(inlineKeyboardButton1);
        rowInline1.add(inlineKeyboardButton2);
        rowInline1.add(inlineKeyboardButton3);

        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("200..250");
        inlineKeyboardButton4.setCallbackData("/gen 200..250");
        rowInline2.add(inlineKeyboardButton4);

        markupInline.setKeyboard(List.of(rowInline1, rowInline2));

        return markupInline;
    }
}
