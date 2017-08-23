package br.com.utils;

import javax.servlet.http.HttpServletRequest;

public class Session {

    private static final String keyHttpConnection = "session_id";

    public static Boolean isCanAccess(final HttpServletRequest httpServletRequest) {
        final String sessionId = httpServletRequest.getHeader(keyHttpConnection);
        // verificar também se tem usuário logado com esta chave
        return sessionId != null;
    }

}
