package br.hm.example.api;

import br.hm.example.model.Message;
import br.hm.example.model.OutputMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by helmut.guimaraes on 25/04/2017.
 */
@Controller
public class ChatControllerImpl {
    final static Logger LOGGER = LoggerFactory.getLogger(ChatControllerImpl.class);

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(final Message message) throws Exception {
        LOGGER.info("from " + message.getFrom() + " msg: " + message.getText());
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

}
