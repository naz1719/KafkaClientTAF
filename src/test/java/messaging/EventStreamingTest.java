package messaging;

import com.project.events.model.Message;
import com.project.inftrastructure.db.MessageRepository;
import com.project.inftrastructure.middlewares.http.kafka.ConsumerService;
import com.project.inftrastructure.middlewares.http.kafka.ProducerService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

public class EventStreamingTest {

    @Test(description = "Produce/Consume event test")
    public void eventTest() {
        ProducerService producerService = new ProducerService();
        ConsumerService consumerService = new ConsumerService();
        MessageRepository messageRepository = new MessageRepository();
        String inputTopicName = "test_topic";
        //        1. Create topic if it not exist
        producerService.createTopic(inputTopicName);
        List<Message> messageList = prepareTestMessageList();
        //        2. Produce events
        producerService.produceEvents(inputTopicName, messageList);
        //        3. Save produces events to db
        messageRepository.insertMessageList(messageList);
        //        4. Consume events
        consumerService.consumeEvents(10, inputTopicName);
        //        5. Check saved and consumed events
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
