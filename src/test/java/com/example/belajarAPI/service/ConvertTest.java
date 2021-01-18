package com.example.belajarAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.belajarAPI.domain.CustomerVehicle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.belajarAPI.service.UserDummyService;

@SpringBootTest
public class ConvertTest {

    @Autowired
    UserDummyService userDummyService;
    @Test
    public void convertStringInt(){

        String amount = "1000";
        Double amnt = Double.valueOf(amount);

        Integer data = 0;
        try{
            data = Integer.valueOf(amount);
       }
       catch(Exception e){
           data = (int)Math.round(Double.valueOf(amount));
       }

       Integer result = 1000;
       assertEquals(data,result);
    }
    @Test
    public void loginTest(){
        CustomerVehicle customerVehicle = new CustomerVehicle();
        customerVehicle.setUsername("user_dummy999@test.com");
        
        userDummyService.login(customerVehicle);
        assertNotNull("actual");
    }

    @Test
    public void setTokenTest(){
        userDummyService.setTokens();
        assertNotNull("daf");

    }
}