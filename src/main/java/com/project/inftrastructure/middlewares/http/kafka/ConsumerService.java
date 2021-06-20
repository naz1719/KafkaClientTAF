package com.project.inftrastructure.middlewares.http.kafka;

import java.time.Duration;
import java.util.Collections;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerService extends BaseKafkaService{

    final static Logger LOG = LoggerFactory.getLogger(ProducerService.class);

    public void consumeEvents(String topicName) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerKafkaProps);
        consumer.subscribe(Collections.singletonList(topicName));
        //polling
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                LOG.info("Key: " + record.key() + ", Value:" + record.value());
                LOG.info("Partition:" + record.partition() + ",Offset:" + record.offset());
            }
        }
    }
}
