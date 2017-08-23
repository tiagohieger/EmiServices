/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.api.io.socket;

import br.com.entitys.User;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

/**
 *
 * @author Richeli Vargas
 */
public class ServerManager {

    private static final String SESSION_ID_PROPERTY = "session_id";
    
    private final Map<User, Session> connectedUsers = Collections.synchronizedMap(new HashMap<>());

    private ServerManager() {
    }

    private static ServerManager serverManager;

    public static ServerManager getInstance() {

        if (serverManager == null) {
            serverManager = new ServerManager();
        }

        return serverManager;
    }

    public void putUser(final User user, final Session session) {

        synchronized (connectedUsers) {
            connectedUsers.put(user, session);
        }

    }

    public User getUser(final Session session) {

        synchronized (connectedUsers) {
            if (connectedUsers.containsValue(session)) {
                return connectedUsers.entrySet().stream()
                        .filter(e -> e.getValue().equals(session))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);
            } else {
                return null;
            }
        }
    }

    public User getUser(final String sessionId) {

        synchronized (connectedUsers) {
            return connectedUsers.keySet().stream()
                    .filter(e -> e.getSessionId().equals(sessionId))
                    .findFirst()
                    .orElse(null);
        }
    }

    public Session getSession(final User user) {
        synchronized (connectedUsers) {
            return connectedUsers.get(user);
        }
    }

    public Session getSession(final String sessionId) {

        synchronized (connectedUsers) {
            return connectedUsers.entrySet().stream()
                    .filter(e -> e.getKey().getSessionId().equals(sessionId))
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElse(null);
        }
    }

    public void remove(final User user) {
        synchronized (connectedUsers) {
            connectedUsers.remove(user);
        }
    }

    public void remove(final Session session) {
        synchronized (connectedUsers) {
            if (connectedUsers.containsValue(session)) {
                final User user = connectedUsers.entrySet().stream()
                        .filter(e -> e.getValue().equals(session))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

                connectedUsers.remove(user);
            }
        }
    }

    public void remove(final String sessionId) {
        synchronized (connectedUsers) {
            final User user = connectedUsers.keySet().stream()
                    .filter(e -> e.getSessionId().equals(sessionId))
                    .findFirst()
                    .orElse(null);

            connectedUsers.remove(user);
        }
    }
    
    public Boolean isAuthorized(final HttpServletRequest httpServletRequest) {
        final String sessionId = httpServletRequest.getHeader(SESSION_ID_PROPERTY);
        return sessionId != null && getUser(sessionId) != null;
    }
}
