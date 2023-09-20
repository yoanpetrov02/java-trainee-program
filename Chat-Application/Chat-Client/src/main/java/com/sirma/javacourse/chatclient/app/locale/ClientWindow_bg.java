package com.sirma.javacourse.chatclient.app.locale;

import java.util.ListResourceBundle;

/**
 * ClientWindow Bulgarian language resource bundle.
 */
public class ClientWindow_bg extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"window.title", "Чат клиент"},
                {"label.onlineusers", "Онлайн"},
                {"label.offlineusers", "Офлайн"},
                {"button.sendbutton", "Изпрати"},
                {"button.reconnectbutton", "Свържи се отново"},
                {"error.chathistory", "Неуспешно зареждане на историята на чата."},
                {"error.usermessage", "Получателят не съществува!"},
                {"error.title", "Грешка"},
                {"error.header", "Възникна грешка."},
                {"exitwindow.title", "Затваряне"},
                {"exitwindow.header", "Затваряне на чат клиента"},
                {"exitwindow.content", "Сигурни ли сте, че искате да затворите чат клиента?"}
        };
    }
}
