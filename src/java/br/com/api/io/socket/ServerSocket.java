/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.api.io.socket;

import br.com.commons.util.Objects;
import br.com.entitys.User;
import br.com.filters.UserFilter;
import br.com.json.Converter;
import br.com.language.Messages;
import br.com.rn.GenRn;
import br.com.rn.UserRn;
import br.com.socket.SocketMessage;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Richeli Vargas
 */
@ServerEndpoint("/{" + User.Columns.LOGIN + "}/{" + User.Columns.PASSWORD + "}/{locale}/{version}")
public class ServerSocket implements Serializable {
    
    private static final String currentVersion = "1.0.0";
    
    @OnOpen
    public void onOpen(final Session session) throws Throwable {
        
        // /////////////////////////////////////////////////////////////////////
        // Load params
        // /////////////////////////////////////////////////////////////////////
        final Map<String, String> pathParameters = session.getPathParameters();
        final String login = pathParameters.get(User.Columns.LOGIN);
        final String password = pathParameters.get(User.Columns.PASSWORD);
        final String locale = pathParameters.getOrDefault("locale", "pt_BR");
        final String version = pathParameters.get("version");
        final String sessionId = session.getId();
        
        // /////////////////////////////////////////////////////////////////////
        // Get locale
        // /////////////////////////////////////////////////////////////////////
        final String[] local = locale.split("_");
        final Messages messages = Messages.getInstance(new Locale(local[0], local[1]));
        
        // /////////////////////////////////////////////////////////////////////
        // Verify cliente version
        // /////////////////////////////////////////////////////////////////////
        if(!Objects.equals(currentVersion, version)){
            SocketHelper.sendError(session, 
                    new IllegalArgumentException(messages.getErrInvalidVersion()));            
            session.close();
        }
        
        // /////////////////////////////////////////////////////////////////////
        // Get user
        // /////////////////////////////////////////////////////////////////////
        final UserFilter filter = new UserFilter();
        filter.setLimit(1);
        filter.setLogin(login);
        filter.setPassword(password);
        
        final UserRn userRn = GenRn.newInstance(User.class);
        final List<User> users = userRn.list(filter);
        
        // /////////////////////////////////////////////////////////////////////
        // Logged user verification
        // /////////////////////////////////////////////////////////////////////
        if(users.isEmpty()){            
            SocketHelper.sendError(session, 
                    new IllegalArgumentException(messages.getErrInvalidUser()));            
            session.close();
            return;
        }
        
        // /////////////////////////////////////////////////////////////////////
        // Register the user
        // /////////////////////////////////////////////////////////////////////
        final User user = users.get(0);
        user.setSessionId(sessionId);
        
        final SocketMessage message = SocketHelper.message(SocketMessage.Type.LOGIN, user);
        
        ServerManager.getInstance().putUser(user, session);
        session.getBasicRemote().sendText(Converter.objectToJson(message));
    }
    
    @OnClose
    public void onClose(Session session) throws Throwable {
        ServerManager.getInstance().remove(session);
    }
    
    @OnMessage
    public void onMessage(Session session, String message) {
        
    }
    
}
