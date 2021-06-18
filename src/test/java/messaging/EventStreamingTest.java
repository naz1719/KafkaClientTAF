package messaging;

import com.project.inftrastructure.middlewares.http.kafka.ProducerService;
import org.testng.annotations.Test;

public class EventStreamingTest {

    @Test(description = "Produce/Consume event test")
    public void eventTest(){
        ProducerService producerService = new ProducerService();
        producerService.createTopic("test_topic");
//        1. Produce event
//        2. Save produced event to DB
//        3. Consume event
//        4. assert saved and consumed event


    }
}
