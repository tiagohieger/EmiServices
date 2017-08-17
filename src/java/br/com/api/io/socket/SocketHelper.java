/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.api.io.socket;

import br.com.json.Converter;
import br.com.socket.SocketMessage;

/**
 *
 * @author Richeli Vargas
 */
public class SocketHelper {

    public static SocketMessage message(final SocketMessage.Type type, final Object data) throws Exception{
        
        final SocketMessage message = new SocketMessage();
        message.setId(-1);
        message.setData(Converter.objectToJson(data));
        message.setType(type);
        
        return message;
    }
    
}
