package com.project.inftrastructure.middlewares.kafka;

import com.project.messages.model.Message;
import java.util.Collections;
import java.util.List;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerService extends BaseKafkaService{

    final static Logger LOG = LoggerFactory.getLogger(ProducerService.class);

    public void createTopic(String topicName) {
        AdminClient adminClient = AdminClient.create(producerKafkaProps);
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
        boolean isPresent = isTopicPresent(adminClient, topicName);
        if (!isPresent) {
            LOG.debug("Topic " + topicName + " was created");
            adminClient.createTopics(Collections.singletonList(newTopic));
            adminClient.close();
        }
    }

    public void produceEvents(String topicName, List<Message> messageList) {
        Producer<String, Message> producer = new KafkaProducer<String, Message>(producerKafkaProps);
        messageList.forEach(message -> {
            ProducerRecord<String, Message> record = new ProducerRecord<String, Message>(topicName, message.getId().toString(),
                    message);
            producer.send(record);
            LOG.info("Producing record: {}\t{}", message.getId(), message.getMessage());
        });
        producer.flush();
        LOG.info("{} messages were produced to topic {}", messageList.size(), topicName);
        producer.close();
    }

    private boolean isTopicPresent(AdminClient adminClient, String topicName) {
        boolean isPresent;
        try {
            isPresent = adminClient.listTopics().names().get()
                    .stream()
                    .anyMatch(s -> s.equalsIgnoreCase(topicName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isPresent;
    }
}
