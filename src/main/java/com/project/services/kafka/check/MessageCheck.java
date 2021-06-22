package com.project.services.kafka.check;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.messages.model.Message;
import com.project.messages.dto.MessageDTO;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;

public class MessageCheck {

    private static final MessageCheck instance = new MessageCheck();

    private MessageCheck() {
    }

    public static MessageCheck getInstance() {
        return instance;
    }

    @Step(value = "Check message list")
    public void validateMessageList(List<MessageDTO> actualList, List<Message> expectedList) {
        SoftAssertions sa = new SoftAssertions();

        List<Message> messageDTOList = actualList
                .stream()
                .map(messageDTO -> new Message(messageDTO.getId(), messageDTO.getMessage()))
                .collect(Collectors.toList());
        sa.assertThat(expectedList).containsAll(messageDTOList);
        sa.assertAll();
    }
}