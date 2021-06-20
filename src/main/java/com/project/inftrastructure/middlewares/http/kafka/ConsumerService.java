package com.project.inftrastructure.middlewares.http.kafka;

import com.project.events.model.Message;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerService extends BaseKafkaService {

    final static Logger LOG = LoggerFactory.getLogger(ProducerService.class);

    public List<ConsumerRecord<String, Message>> consumeEvents(int waitSeconds, String topicName) {
        KafkaConsumer<String, Message> consumer = new KafkaConsumer<String, Message>(consumerKafkaProps);
        consumer.subscribe(Collections.singletonList(topicName));
        List<ConsumerRecord<String, Message>> consumerRecordList = new ArrayList<>();

        double pollDurationSeconds = 0.1;
        try {
            double iterations = waitSeconds / pollDurationSeconds;
            for (int i = 0; i < iterations; i++) {
                ConsumerRecords<String, Message> records = consumer.poll(Duration.ofSeconds((long) pollDurationSeconds));
                if(!records.isEmpty()){
                    records.iterator().forEachRemaining(consumerRecordList::add);
                }
                printRecords(records);
            }
        } finally {
            consumer.close();
        }
        return consumerRecordList;
    }

    private void printRecords(ConsumerRecords<String, Message> records) {
        for (ConsumerRecord<String, Message> record : records) {
            LOG.info("Key: " + record.key() + ", Value:" + record.value());
            LOG.info("Partition:" + record.partition() + ",Offset:" + record.offset());
        }
    }
}
