package messaging;

import com.project.events.model.Message;
import com.project.inftrastructure.middlewares.http.kafka.ConsumerService;
import com.project.inftrastructure.middlewares.http.kafka.ProducerService;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class EventStreamingTest {

    @Test(description = "Produce/Consume event test")
    public void eventTest(){
        ProducerService producerService = new ProducerService();
        ConsumerService consumerService = new ConsumerService();

        String inputTopicName = "test_topic";
        producerService.createTopic(inputTopicName);
        List<Message> messageList = prepareTestMessageList();
        producerService.produceEvents(inputTopicName, messageList);
        consumerService.consumeEvents(inputTopicName);
//        1. Produce event
//        2. Save produced event to DB
//        3. Consume event
//        4. assert saved and consumed event

    }

    @Test(description = "Produce/Consume event test")
    public void consumeEventTest(){
        ConsumerService consumerService = new ConsumerService();
        String inputTopicName = "test_topic";
        consumerService.consumeEvents(inputTopicName);
    }

    private List<Message> prepareTestMessageList() {
        int msgCount = 10;
        List<Message> messageList = new ArrayList<>();
        for (long i = 1; i <= msgCount; i++) {
            String message = "message" + i;
            messageList.add(new Message(i, message));
        }
        return messageList;
    }
}
