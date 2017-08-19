/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.language;

import br.com.senaimg.wms.model.sistema.Settings;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author Aluno
 */
public class Lang {

    private static ResourceBundle resourceBundle;
    private static Locale locale;

    static {
        loadLanguage();
    }

    public static void loadLanguage() {
        Settings.changeLast();
        Settings last = Settings.getLast();

        if (last == null) {
            locale = Locale.getDefault();

        } else {

            Language language = last.getLanguageSystem();
            if (null != language) {
                switch (language) {
                    case PTBR:
                        locale = new Locale("pt", "BR");

                        break;
                    case ENUS:
                        locale = new Locale("en", "US");
                        break;
                    default:
                        locale = Locale.getDefault();
                        break;
                }
            }
        }

        Locale.setDefault(locale);
        resourceBundle = ResourceBundle.getBundle("br/com/senaimg/wms/language/Bundle", locale);

    }

    public static void loadLanguage(Language language) {

        if (null != language) {
            switch (language) {
                case PTBR:
                    locale = new Locale("pt", "BR");

                    break;
                case ENUS:
                    locale = new Locale("en", "US");
                    break;
                default:
                    locale = Locale.getDefault();
                    break;
            }
        }

        Locale.setDefault(locale);
        resourceBundle = ResourceBundle.getBundle("br/com/senaimg/wms/language/Bundle", locale);

    }

    public static String get(String key) {
        String value;

        try {

            value = resourceBundle.getString(key);

            return value;
        } catch (NullPointerException | MissingResourceException | ClassCastException ex) {
            System.err.println(ex.getMessage());
//            Console console = Console.getConsole();
//            JTextArea textArea = console.getTextArea();
//            textArea.append(ex.getMessage() + "\n");
            
            return key;
        }
    }

}
