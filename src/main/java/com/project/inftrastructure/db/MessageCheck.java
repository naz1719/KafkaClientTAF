package com.project.inftrastructure.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.events.model.Message;
import com.project.inftrastructure.db.dto.MessageDTO;
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

    @Step(value = "Check event message list")
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
