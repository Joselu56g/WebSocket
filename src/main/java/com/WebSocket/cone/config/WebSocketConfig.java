package com.WebSocket.cone.config;

import org.springframework.context.annotation.*;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration // La anotacion que indica una analogia con el contenedor xml
@EnableWebSocketMessageBroker /* La anotacion habilita el broker que es punto intermediario
en que se enruta los mensajes para dar entrada o salida */

public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer { /* Clase que configura
    WebSocket y habilita el intercambio de mensajes brokered utilizando Spring Framework */

    //Guardar
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS(); /* Mediante el Endpoint se guardan los
        mensajes y se activa el protocolo STOMP tiene que llegar con la ruta /ws */
    }

    //Enviar

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); /* Configura el prefijo de destino de la
        aplicación para los mensajes entrantes de modo /app */
        registry.enableSimpleBroker("/topic"); // Habilita un broker simple con el prefijo /topic

    }
}
