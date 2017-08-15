package br.com.teste;

import br.com.filters.IndicationFilter;
import br.com.json.Converter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teste {

    public void testeConverter() throws Exception {
        IndicationFilter filter = new IndicationFilter();
        filter.setClient(1);
        filter.setDocument("sdhfgdsfg");
        String json = Converter.objectToJson(filter);
        System.out.println(json);
    }

    public static void main(String[] args) {
        try {
            new Teste().testeConverter();
        } catch (Exception ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
