package com.sirma.javacourse.chatserver.app.locale;

import java.util.ListResourceBundle;

/**
 * ServerWindow Bulgarian language resource bundle.
 */
public class ServerWindow_bg extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"window.title", "Сървър"},
                {"button.start", "Старт"},
                {"button.stop", "Стоп"},
                {"error.start", "Сървърът вече е стартиран!"},
                {"error.stop", "Сървърът не е стартиран!"},
                {"error.title", "Грешка"},
                {"error.header", "Възникна грешка."},
                {"error.io", "Възникна грешка при стартирането на сървъра."},
                {"exitwindow.title", "Затваряне"},
                {"exitwindow.header", "Затваряне на сървъра"},
                {"exitwindow.content", "Сигурни ли сте, че искате да затворите сървъра?"}
        };
    }
}
