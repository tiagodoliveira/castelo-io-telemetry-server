package io.castelo.telemetry_server.mqtt;

import io.castelo.telemetry_server.end_device_component_data.ComponentDataService;
import io.castelo.telemetry_server.end_device_component_data.EndDeviceComponentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    private final MqttRepository mqttRepository;
    private final ComponentDataService componentDataService;

    @Autowired
    public MqttService(MqttRepository mqttRepository, ComponentDataService componentDataService) {
        this.mqttRepository = mqttRepository;
        this.componentDataService = componentDataService;
    }

    public void publishMessage(EndDeviceComponentData endDeviceComponentData) {

        String topic = String.format("device/%s/%d",
                endDeviceComponentData.metaField().endDeviceMac(),
                endDeviceComponentData.metaField().componentNumber());

        mqttRepository.publishToMqtt(topic, endDeviceComponentData.value());

        componentDataService.insertComponentData(endDeviceComponentData);
    }
}
