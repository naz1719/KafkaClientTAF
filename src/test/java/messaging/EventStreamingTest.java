package messaging;

import com.project.events.model.Message;
import com.project.inftrastructure.db.MessageCheck;
import com.project.inftrastructure.db.MessageRepository;
import com.project.inftrastructure.db.dto.MessageDTO;
import com.project.inftrastructure.middlewares.http.kafka.ConsumerService;
import com.project.inftrastructure.middlewares.http.kafka.ProducerService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.testng.annotations.Test;

public class EventStreamingTest {

    ProducerService producerService = new ProducerService();
    ConsumerService consumerService = new ConsumerService();
    MessageRepository messageRepository = new MessageRepository();

    @Test(description = "Produce/Consume event test, positive scenario")
    public void positiveEventTest() {
        List<Message> actualMessageList = baseEventTest();
        // assert
        List<MessageDTO> expectedMessageDTOList = prepareExpectedMessageDTOList(
                messageRepository, actualMessageList);
        MessageCheck.getInstance().validateMessageList(expectedMessageDTOList, actualMessageList);
    }

    @Test(description = "Produce/Consume event test, negative scenario")
    public void negativeEventTest() {
        List<Message> actualMessageList = baseEventTest();
        // assert
        List<MessageDTO> expectedMessageDTOList = prepareExpectedMessageDTOList(
                messageRepository, actualMessageList);
        expectedMessageDTOList.add(new MessageDTO(46556L, "message"));
        MessageCheck.getInstance().validateMessageList(expectedMessageDTOList, actualMessageList);
    }

    private List<Message> baseEventTest() {
        // arrange
        String inputTopicName = "test_topic";
        producerService.createTopic(inputTopicName);
        List<Message> messageList = prepareTestMessageList();
        // act
        producerService.produceEvents(inputTopicName, messageList);
        messageRepository.insertMessageList(messageList);
        List<ConsumerRecord<String, Message>> consumerRecordList = consumerService.consumeEvents(10, inputTopicName);
        List<Message> actualMessageList = consumerRecordList.stream()
                .map(ConsumerRecord::value)
                .collect(Collectors.toList());
        return actualMessageList;
    }

    private List<MessageDTO> prepareExpectedMessageDTOList(MessageRepository messageRepository,
            List<Message> actualMessageList) {
        return actualMessageList
                .stream()
                .map(message -> messageRepository
                        .selectMessageById(message.getId()))
                .collect(Collectors.toList());
    }

    private List<Message> prepareTestMessageList() {
        int msgCount = 3;
        List<Message> messageList = new ArrayList<>();
        for (long i = 1; i <= msgCount; i++) {
            Long id = Long.valueOf(RandomStringUtils.randomNumeric(1));
            String message = "message_" + id;
            messageList.add(new Message(id, message));
        }
        return messageList;
    }
}
