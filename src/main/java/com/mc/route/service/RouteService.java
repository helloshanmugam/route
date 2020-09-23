package com.mc.route.service;

import com.mc.route.config.RouterHelper;
import com.mc.route.controller.RouteController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService {

    @Autowired
    RouterHelper router;

    Logger logger = LoggerFactory.getLogger(RouteService.class);

    public String isConnectedT(String origin, String destination) {

        Set<String> visited = new HashSet<>();

        Map<String, Set<String>> cityMap = router.getCityMap();

        if(origin.equalsIgnoreCase(destination)) {
            return "Yes";
        }

        if(cityMap.containsKey(origin)) {

            visited.add(origin);

            Set<String> nearByCities = cityMap.get(origin);

            if (nearByCities.contains(destination)) {
                return "Yes";
            }

            for(String city : nearByCities) {

                visited.add(city);

                if(cityMap.get(city).contains(destination)) {
                    return "Yes";
                }
            }
        }

        return "No";
    }



    public String isConnected(String origin, String destination) {
        logger.info("Inside isConnected");

        Map<String, Set<String>> cityMap = router.getCityMap();

        HashSet<String> visited = new HashSet<>();

        if(cityMap.containsKey(origin)) {

            if (cityMap.get(origin).contains(destination)) {
                return "Yes";
            }

            visited.add(origin);

            Queue<String> adjacent = new LinkedList<>(cityMap.get(origin));

            while (!adjacent.isEmpty()) {

                String city = adjacent.remove();
                visited.add(city);

                if (city.equalsIgnoreCase(destination)) {
                    return "Yes";
                }

                for (String s : cityMap.get(city)) {

                    if (s.equalsIgnoreCase(destination)) {
                        return "Yes";
                    }

                    if (!visited.contains(s)) {
                        adjacent.add(s);
                    }
                }
            }
        }

        return "No";
    }
}
