package com.project.services.kafka.repository;

import com.project.messages.model.Message;
import com.project.messages.dto.MessageDTO;
import com.project.inftrastructure.db.DBController;
import com.project.services.kafka.queryBox.MessageQueryBox;
import java.util.List;

public class MessageRepository {

    public void insertMessageList(List<Message> messageList) {
        String insertMessageQuery = MessageQueryBox.getInsertMessageQuery(messageList);
        DBController.runInsertQuery(insertMessageQuery);
    }

    public MessageDTO selectMessageById(Long id){
        String selectMessageById = MessageQueryBox.getSelectMessageById(id);
        List<MessageDTO> messageDTOS = DBController.runSelectQueryGetAllRecords(selectMessageById, MessageDTO.class);
        if(messageDTOS.isEmpty()){
            throw new RuntimeException("Message by id " +  id + " not found");
        }
        return messageDTOS.stream().findFirst().get();
    }
}
