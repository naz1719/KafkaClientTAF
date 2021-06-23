package com.project.services.kafka.check;

import com.project.messages.dto.MessageDTO;
import com.project.messages.model.Message;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCheck {

    private static final MessageCheck instance = new MessageCheck();

    private MessageCheck() {
    }

    public static MessageCheck getInstance() {
        return instance;
    }

    @Step(value = "Check message list")
    public void validateMessageList(List<Message> actualList, List<MessageDTO> expectedDTOList) {
        List<Message> expectedList = expectedDTOList
                .stream()
                .map(messageDTO -> new Message(messageDTO.getId(), messageDTO.getMessage()))
                .collect(Collectors.toList());

        assertThat(expectedList).containsExactlyElementsOf(actualList);
    }
}
