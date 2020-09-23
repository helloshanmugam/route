package com.mc.route.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

@Component
public class RouterHelper {

    Logger logger = LoggerFactory.getLogger(RouterHelper.class);

    @Value("${data.file:classpath:cities.txt}")
    private String CITIES;

    private Map<String, Set<String>> cityMap = new HashMap<>();

    @Autowired
    private ResourceLoader resourceLoader;


    public Map<String, Set<String>> getCityMap() {
        return cityMap;
    }

    @PostConstruct
    private void read() throws IOException {

        Resource resource = resourceLoader.getResource(CITIES);

        InputStream is;

        if (!resource.exists()) {
            // file on the filesystem path
            is = new FileInputStream(new File(CITIES));
        } else {
            // file is a classpath resource
            is = resource.getInputStream();
        }

        Scanner scanner = new Scanner(is);

        while (scanner.hasNext()) {

            String line = scanner.nextLine();

            String[] split = line.split(",");
            String origin = split[0].trim().toUpperCase();
            String destination = split[1].trim().toUpperCase();

            if (!origin.equals(destination)) {

                populateCities(origin, destination);
                populateCities(destination, origin);
            }
        }

        logger.info("City Map " + cityMap);
    }

    public void populateCities(String origin, String destination){
        if(! cityMap.containsKey(origin)) {
            Set<String> nearByCities = new HashSet<>();
            nearByCities.add(destination);
            cityMap.put(origin, nearByCities);
        }else {
            Set<String> nearByCities = cityMap.get(origin);
            nearByCities.add(destination);
            cityMap.put(origin, nearByCities);
        }
    }
}
