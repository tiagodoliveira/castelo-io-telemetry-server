package io.castelo.telemetry_server.end_device_component_data;

import java.util.List;

public record EndDeviceComponent(
        int componentNumber,
        List<ComponentValue> componentValues
) {}
