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
import javax.websocket.Session;

/**
 *
 * @author Richeli Vargas
 */
public class ServerManager {

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

    public Session getSession(final User user) {
        synchronized (connectedUsers) {
            return connectedUsers.get(user);
        }
    }

    public void remove(final Session session) {
        synchronized (connectedUsers) {
            
            final User user = connectedUsers.entrySet().stream()
                        .filter(e -> e.getValue().equals(session))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);
            
            connectedUsers.remove(user);
            
        }
    }

    public void remove(final User user) {
        synchronized (connectedUsers) {
            connectedUsers.remove(user);
        }
    }
}
