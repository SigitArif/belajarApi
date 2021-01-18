package com.example.belajarAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.belajarAPI.domain.CustomerVehicle;
import com.example.belajarAPI.repository.CustomerVehicleRepository;
import com.example.belajarAPI.vo.LoginResVO;
import com.example.belajarAPI.vo.LoginVO;
import com.example.belajarAPI.vo.ResultVO;

@Service
public class UserDummyService {
    @Autowired
    CustomerVehicleRepository customerVehicleRepository;

    @Autowired
    private JmsTemplate jmsTemplate;
    
    // @Scheduled(fixedDelay = 10000)
    public void setTokens(){
        List<CustomerVehicle> customerVehicles = customerVehicleRepository.findAll();
        customerVehicles.stream().forEach((customerVehicle)->{
        // jmsTemplate.convertAndSend("Login", customerVehicle);
        String token =login(customerVehicle);
        customerVehicle.setToken(token);
        customerVehicleRepository.saveAndFlush(customerVehicle); 
        });


    }

    public String login(CustomerVehicle customerVehicle){
        String urlLogin = "https://api-staging.cariparkir.co.id/auth-service/api/v5/login" ;
        String token ="";
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String,String> result = new HashMap<String,String>();
            LoginVO reqVO = LoginVO.builder()
                            .id(customerVehicle.getUsername())
                            .password("123456")
                            .statusLogin("GOOGLE")
                            .build();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<LoginVO> requestEntity = new HttpEntity<>(reqVO, headers);
            ResponseEntity<ResultVO> response = restTemplate.exchange(
                    urlLogin, HttpMethod.POST, requestEntity,
                    ResultVO.class);

            result = (Map<String, String>) response.getBody().getResults();
            System.out.println("Successs");
            token = result.get("token");

        } catch (Exception e) {
            //TODO: handle exception
 
        }
        return token;

    }
}