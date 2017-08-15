package br.com.utils;

import javax.servlet.http.HttpServletRequest;

public class Session {

    private static final String keyHttpConnection = "key_http_connection";

    public static Boolean isCanAccess(final HttpServletRequest httpServletRequest) {
        final String sessionId = httpServletRequest.getHeader(keyHttpConnection);
        // verificar também se tem usuário logado com esta chave
        return sessionId != null;
    }

}
