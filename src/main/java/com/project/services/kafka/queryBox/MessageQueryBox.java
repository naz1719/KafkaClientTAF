package com.project.services.kafka.queryBox;

import com.project.messages.model.Message;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class MessageQueryBox {

    public static String getInsertMessageQuery(List<Message> messageList) {
        String query = "INSERT INTO events(id, message) VALUES";
        if (messageList.size() == 1) {
            Message message = messageList.get(0);
            query +=String.format(" (%s,'%s')", message.getId(), message.getMessage());
        } else {
            for (Message message : messageList) {
                query += String.format(" (%s,'%s')", message.getId(), message.getMessage());
                query += (",");
            }
             query = StringUtils.chop(query);
        }
        return query;
    }

    public static String getSelectMessageById(Long id){
        return String.format("select * from events where id = %s;", id);
    }
}
