package io.castelo.telemetry_server.mqtt;


import io.castelo.telemetry_server.end_device_component_data.EndDeviceComponentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    private final MqttService mqttService;

    @Autowired
    public MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void publishMessage(@RequestBody EndDeviceComponentData endDeviceComponentData) {
        mqttService.publishMessage(endDeviceComponentData);
    }

}
