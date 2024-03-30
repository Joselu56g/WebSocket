package com.WebSocket.cone.chat;

import lombok.*; /*Es una anotacion que genera automaticamente los getter, setter un coustrutor vacio y los atributos
 ademas crea un constructor con datos personalizados a partir de los contenedores la aplican las instacias para las clases*/

@Getter // Genera automáticamente un método getter para cada campo de la clase
@Setter // Genera automáticamente un método setter para cada campo de la clase
@NoArgsConstructor // Genera automáticamente un constructor que inicializa todos los campos de la clase
@AllArgsConstructor // Genera automáticamente un constructor sin argumentos
@Builder // Patrón de diseño para construir objetos de manera más flexible y legible

public class ChatMessage {

    private String content; // Es una clase que es el contenido de cadena privada el remitente del mensaje
    private String sender; // Es una clase que es el contenido de cadena privada y este representara el mensaje
    private MessageType type; // Variable que contendra mensajes entre sus diferentes tipos

}
