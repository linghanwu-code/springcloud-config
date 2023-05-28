package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.enties.CommonResult;
import com.atguigu.springcloud.enties.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderController {
    public static final String PAYMENT_URL ="http://cloud-payment-service";



    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer")
    public String paymentInfo(){
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
        return result;


    }

}
