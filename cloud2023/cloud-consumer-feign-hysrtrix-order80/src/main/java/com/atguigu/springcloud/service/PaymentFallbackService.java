package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------service fallback";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "哈哈哈,超时了";
    }
}
