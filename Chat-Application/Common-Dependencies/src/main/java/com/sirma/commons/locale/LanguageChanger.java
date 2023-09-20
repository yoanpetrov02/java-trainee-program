package com.sirma.commons.locale;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Labeled;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class, used to bind elements in the app's UI to keys in the resource bundles for the different languages.
 * This way, when the locale gets changed, the UI elements' text gets updated automatically to that language.
 */
public final class LanguageChanger {

    private static String baseResourcePackage;
    private static final ObjectProperty<Locale> LOCALE = new SimpleObjectProperty<>();

    static {
        LOCALE.set(new Locale("en"));
    }

    private LanguageChanger() {
        // utility class
    }

    public static void setBaseResourcePackage(String pkg) {
        baseResourcePackage = pkg;
    }

    public static void setLocale(Locale locale) {
        LOCALE.set(locale);
    }

    public static Locale getLocale() {
        return LOCALE.get();
    }

    /**
     * Returns the value for the given key in the resource bundle for the current locale.
     *
     * @param key the key to get the value for.
     * @return the value, corresponding to the given key in the resource bundle.
     */
    public static String get(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseResourcePackage, getLocale());
        return bundle.getString(key);
    }

    /**
     * Creates a {@code StringBinding} with the given resource bundle key.
     *
     * @param key the key to create a binding for.
     * @return the {@code StringBinding} object.
     */
    public static StringBinding createStringBinding(String key) {
        return Bindings.createStringBinding(() -> get(key), LOCALE);
    }

    public static void bindUIElement(Labeled element, String key) {
        element.textProperty().bind(createStringBinding(key));
    }
}
