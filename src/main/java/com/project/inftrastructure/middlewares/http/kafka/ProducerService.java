package com.project.inftrastructure.middlewares.http.kafka;

import com.project.inftrastructure.execution.logger.AllureLogger;
import com.project.inftrastructure.utils.property.PropertyLoader;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerService {
    final static Logger LOG = LoggerFactory.getLogger(AllureLogger.class);

    private static final Properties kafkaProperties = PropertyLoader.loadProperties("kafka.properties");

    public void createTopic(String topicName)  {
        AdminClient adminClient = AdminClient.create(kafkaProperties);
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
        boolean isPresent = isTopicPresent(adminClient, topicName);
        if (!isPresent) {
            LOG.debug("Topic " + topicName + " was created");
            adminClient.createTopics(Collections.singletonList(newTopic));
            adminClient.close();
        }
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
