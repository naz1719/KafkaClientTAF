package com.project.inftrastructure.db;

import com.project.events.model.Message;
import java.util.Arrays;
import java.util.List;

public class MessageRepository {

    public void insertMessageList(List<Message> messageList) {
        String insertMessageQuery = QueryBox.getInsertMessageQuery(messageList);
        DBController.runInsertQuery(insertMessageQuery);
    }

    public static void main(String[] args) {

        Message message = new Message(1L, "message");
        Message message1 = new Message(2L, "message");
        MessageRepository messageRepository = new MessageRepository();
        messageRepository.insertMessageList(Arrays.asList(message, message1));
    }
}
