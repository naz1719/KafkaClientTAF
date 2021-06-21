package com.project.inftrastructure.db;

import com.project.events.model.Message;
import com.project.inftrastructure.db.dto.MessageDTO;
import java.util.List;

public class MessageRepository {

    public void insertMessageList(List<Message> messageList) {
        String insertMessageQuery = QueryBox.getInsertMessageQuery(messageList);
        DBController.runInsertQuery(insertMessageQuery);
    }

    public MessageDTO selectMessageById(Long id){
        String selectMessageById = QueryBox.getSelectMessageById(id);
        List<MessageDTO> messageDTOS = DBController.runSelectQueryGetAllRecords(selectMessageById, MessageDTO.class);
        if(messageDTOS.isEmpty()){
            throw new RuntimeException("Message by id " +  id + " not found");
        }
        return messageDTOS.stream().findFirst().get();
    }
}
