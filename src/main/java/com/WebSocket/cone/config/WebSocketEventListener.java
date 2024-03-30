package com.WebSocket.cone.config;

import com.WebSocket.cone.chat.ChatMessage;
import com.WebSocket.cone.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component // Marca esta clase como un componente de Spring para ser escaneado e inicializado por el contenedor de Spring
@Slf4j // Anotación de registro simple
@RequiredArgsConstructor //genera los constructores con un parámetro para cada campo que necesita un manejo especial
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate; /* Variable para acceder a las operaciones de envío de
    mensajes simples.*/

    @EventListener // Detector de eventos de anotacion del Spring framwork
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) { // Metodo que utiliza el parametro para un evento de desconexion
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage()); //Un objeto envuelve el mensaje del evento en un event.getMessage()
        String username = (String) headerAccessor.getSessionAttributes().get("username"); // Obtiene el nombre de usuario de los atributos de la sesión
        if (username != null) { // Verifica si el nombre de usuario no es nulo
            log.info("user disconnected: {}", username); // Registra un mensaje de información indicando que un usuario se ha desconectado
            var chatMessage = ChatMessage.builder() // Construye un objeto ChatMessage utilizando el patrón Builder
                    .type(MessageType.LEAVE) // Establece el tipo de mensaje como LEAVE
                    .sender(username) // Establece el remitente del mensaje como el nombre de usuario
                    .build(); // Crea la instancia de ChatMessage
            messagingTemplate.convertAndSend("/topic/public", chatMessage); // Envía el mensaje a todos los clientes topic/public
        }
    }

}