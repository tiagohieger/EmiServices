package br.com.api.config;

import br.com.api.io.services.ListMethods;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import javax.ws.rs.core.Application;

public class ApplicationConfig extends Application {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();
        resources.add(ListMethods.class);
        return resources;
    }

}
