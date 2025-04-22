package edu.icet.ecom.dto;

import edu.icet.ecom.util.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private String content;

    private String sender;

    private MessageType type;
}
