package br.com.linguage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Messages {

    private static final String caminho = "br/com/linguage/messages";
    private static final Map<Locale, Messages> mapMensagens = new HashMap<>();
    private ResourceBundle bundle;

    private Messages(Locale locale) {
        if (locale == null) {
            locale = new Locale("pt", "BR"); // default
        }
        try {
            bundle = ResourceBundle.getBundle(caminho, locale);
            if (bundle == null) {
                bundle = ResourceBundle.getBundle(caminho, new Locale("pt", "BR")); // default
            }
        } catch (Exception ex) {
            bundle = ResourceBundle.getBundle(caminho);
        }
    }

    public static Messages getInstance(final Locale locale) {

        if (Messages.mapMensagens.containsKey(locale)) {
            return Messages.mapMensagens.get(locale);
        } else {
            final Messages mensagens = new Messages(locale);
            Messages.mapMensagens.put(locale, mensagens);
            return mensagens;
        }
    }

    public String getErrAccessNotPermited() {
        return bundle.getString("err_access_not_permited");
    }

}
