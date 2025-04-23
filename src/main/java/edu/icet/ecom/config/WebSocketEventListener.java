package edu.icet.ecom.config;

import edu.icet.ecom.dto.ChatMessage;
import edu.icet.ecom.util.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headersAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headersAccessor.getSessionAttributes().get("username");

        if (username!=null) {
            log.info("User Disconnected: {}", username);
            var chatMessage = ChatMessage.builder().type(MessageType.LEAVE).sender(username).build();

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
