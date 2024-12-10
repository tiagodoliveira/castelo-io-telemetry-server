package io.castelo.telemetry_server.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MqttRepository {

    private static final Logger log = LoggerFactory.getLogger(MqttRepository.class);
    private final MessageChannel mqttOutboundChannel;

    @Autowired
    public MqttRepository(MessageChannel mqttOutboundChannel) {
        this.mqttOutboundChannel = mqttOutboundChannel;
    }

    public void publishToMqtt(String topic, String message) {

        Message<String> mqttMessage = MessageBuilder.withPayload(message)
                .setHeader("mqtt_topic", topic)
                .build();
        log.info(mqttMessage.toString());
        mqttOutboundChannel.send(mqttMessage);
    }
}
