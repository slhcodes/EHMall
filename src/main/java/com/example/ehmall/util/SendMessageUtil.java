package com.example.ehmall.util;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public class SendMessageUtil {

        public static void sendPostRequest(String phone) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://cspe.api.storeapi.net/pyi/86/204?appid=18774&template_id=10252&sign=c9b25cc3b1d3d8e0a4ab9d6d706f6247&mobile=";
            String response = restTemplate.getForObject(url+phone, String.class);
            System.out.println(response);
        }

}
