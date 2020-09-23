package com.mc.route;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_Boston_to_Newark() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("origin", "Boston");
        params.put("destination", "Newark");

        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assertions.assertEquals("Yes", body);
    }

    @Test
    public void test_Boston_to_Philadelphia() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("origin", "Boston");
        params.put("destination", "Philadelphia");

        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assertions.assertEquals("Yes", body);
    }

    @Test
    public void test_Philadelphia_Albany() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("origin", "Philadelphia");
        params.put("destination", "Albany");

        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assertions.assertEquals("No", body);
    }
}