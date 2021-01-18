package com.example.belajarAPI.service;

import java.util.Arrays;

import com.example.belajarAPI.domain.CustomerVehicle;
import com.example.belajarAPI.repository.CustomerVehicleRepository;
import com.example.belajarAPI.vo.LoginResVO;
import com.example.belajarAPI.vo.LoginVO;
import com.example.belajarAPI.vo.ResultVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoginListener {
    @Autowired
    CustomerVehicleRepository customerVehicleRepository;
    @JmsListener(destination = "Login", containerFactory = "myFactory")
    public void login(CustomerVehicle customerVehicle){
        String urlLogin = "https://api-staging.cariparkir.co.id/auth-service/api/v5/login" ;
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            LoginResVO result = LoginResVO.builder().build();
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

            result = (LoginResVO) response.getBody().getResults();
            System.out.println("Successs");
            customerVehicle.setToken(result.getToken());
            customerVehicleRepository.saveAndFlush(customerVehicle);


        } catch (Exception e) {
            //TODO: handle exception
 
        }

    }
}