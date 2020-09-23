package com.mc.route.controller;

import com.mc.route.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RouteController {

    @Autowired
    RouteService routeService;

    Logger logger = LoggerFactory.getLogger(RouteController.class);

    @Operation(summary="Check whether the two given cities are connected")
    @GetMapping("/connected")
    public String isConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        logger.info("Origin and Destination {} - {}", origin, destination);

        if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)) {
            return "No";
        }
        return routeService.isConnected(origin.toUpperCase(), destination.toUpperCase());
    }
}
