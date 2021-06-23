package messaging;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.infrastructure.middlewares.kafka.ConsumerService;
import com.project.infrastructure.middlewares.kafka.ProducerService;
import com.project.messages.dto.MessageDTO;
import com.project.messages.model.Message;
import com.project.services.kafka.check.MessageCheck;
import com.project.services.kafka.repository.MessageRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageStreamingTest {

    ProducerService producerService = new ProducerService();
    ConsumerService consumerService = new ConsumerService();
    MessageRepository messageRepository = new MessageRepository();
    ObjectMapper mapper = new ObjectMapper();

    @Test(description = "Produce/Consume event test, positive scenario")
    public void positiveMessageStreamingTest() {
        List<Message> actualMessageList = baseMessageEventTest();
        // assert
        List<MessageDTO> expectedMessageDTOList = prepareExpectedMessageDTOList(
                messageRepository, actualMessageList);
        MessageCheck.getInstance().validateMessageList(actualMessageList, expectedMessageDTOList);
    }

    @Test(description = "Produce/Consume event test, negative scenario")
    public void negativeMessageStreamingTest() {
        List<Message> actualMessageList = baseMessageEventTest();
        // assert
        List<MessageDTO> expectedMessageDTOList = prepareExpectedMessageDTOList(
                messageRepository, actualMessageList);
        expectedMessageDTOList.add(new MessageDTO(46556L, "message"));
        MessageCheck.getInstance().validateMessageList(actualMessageList, expectedMessageDTOList);
    }

    private List<Message> baseMessageEventTest() {
        // arrange
        String inputTopicName = "test_topic";
        producerService.createTopic(inputTopicName);
        List<Message> messageList = prepareTestMessageList();
        // act
        producerService.produceEvents(inputTopicName, messageList);
        messageRepository.insertMessageList(messageList);
        List<ConsumerRecord<String, Message>> consumerRecordList = consumerService.consumeMessages(10, inputTopicName);
        List<Message> consumedMessageList = consumerRecordList.stream()
                .map(stringMessageConsumerRecord ->
                        mapper.convertValue(stringMessageConsumerRecord.value(), new TypeReference<Message>() {}))
                .collect(Collectors.toList());
        return consumedMessageList;
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
            Long id = Long.valueOf(RandomStringUtils.randomNumeric(8));
            String message = "message_" + id;
            messageList.add(new Message(id, message));
        }
        return messageList;
    }
}
