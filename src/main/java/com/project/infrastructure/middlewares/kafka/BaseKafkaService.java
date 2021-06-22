package com.project.infrastructure.middlewares.kafka;

import com.project.infrastructure.utils.property.PropertyLoader;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

public class BaseKafkaService {

    Properties producerKafkaProps;
    Properties consumerKafkaProps;

    public BaseKafkaService() {
        producerKafkaProps = prepareProducerKafkaProps();
        consumerKafkaProps = prepareConsumerKafkaProps();
    }

    private Properties prepareProducerKafkaProps() {
        Properties props = PropertyLoader.loadProperties("kafka.properties");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaJsonSerializer");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return props;
    }

    private Properties prepareConsumerKafkaProps() {
        Properties props = PropertyLoader.loadProperties("kafka.properties");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaJsonDeserializer");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"test-group");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }
}
