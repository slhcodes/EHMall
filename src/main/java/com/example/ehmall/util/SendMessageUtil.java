package com.example.ehmall.util;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public class SendMessageUtil {

        public static void sendPostRequest(String phone) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestBody = String.format("{\"appid\":\"%d\",\"mobile\":\"%s\",\"sign\":\"%s\",\"template_id\":\"%d" +
                    "\"}", 18774, phone, "c9b25cc3b1d3d8e0a4ab9d6d706f6247",10252);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange("https://cspe.api.storeapi.net/pyi/86/204", HttpMethod.POST, entity, String.class);
            System.out.println(response);
        }

}
