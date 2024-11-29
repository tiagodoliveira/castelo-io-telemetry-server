package io.castelo.main_server.end_device_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/end-device-telemetry")
public class EndDeviceDataController {

    private final EndDeviceDataService endDeviceDataService;

    @Autowired
    public EndDeviceDataController(EndDeviceDataService endDeviceDataService) {
        this.endDeviceDataService = endDeviceDataService;
    }

    @GetMapping("/{endDeviceMac}")
    @ResponseStatus(HttpStatus.OK)
    public EndDeviceData getEndDeviceDataByMac(@PathVariable String endDeviceMac,
                                               @RequestParam(required = false, defaultValue = "10", name = "maxEntries") int maxEntries) {
        return endDeviceDataService.findByEndDeviceMac(endDeviceMac, maxEntries);
    }

    @GetMapping("/{endDeviceMac}/get-all-end-device-data")
    @ResponseStatus(HttpStatus.OK)
    public EndDeviceData getAllEndDeviceDataByMac(@PathVariable String endDeviceMac) {
        return endDeviceDataService.findAllByEndDeviceMac(endDeviceMac);
    }

    @PostMapping("/save-data")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEndDeviceData(@RequestBody EndDeviceData endDeviceData) {
        endDeviceDataService.saveEndDeviceData(endDeviceData);
    }
}