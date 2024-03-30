package com.WebSocket.cone.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    //Metodo para enviar un mensaje
    @MessageMapping("/chat.sendMessage") //La anotacion que mapea el mensaje a la ruta URL
    @SendTo("/topic/public")  //La anotacion envía el acontecimineto a todos los clientes del destino topic
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage  //Contiene el body de nuestra request
    ){
        return chatMessage; // Retorna el mensaje recibido
    }

    //Metodo de agregar usuarios
    @MessageMapping("/chat.addUser") // La anotacion que nos permitira extablecer una conexion entre el usuario y WebSocket
    @SendTo("/topic/public")  //La anotacion envía el acontecimiento a /topic
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor
    ){
        //Agrega a usuario en una sesión del webSocket
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return  chatMessage; // Retorna el mensaje recibido
    }

}
