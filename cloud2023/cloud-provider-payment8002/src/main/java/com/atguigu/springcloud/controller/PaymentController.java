package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.enties.CommonResult;
import com.atguigu.springcloud.enties.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService  paymentService;
    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        Integer res = paymentService.create(payment);
        if (res == 0) {
            return new CommonResult<>(500, "插入数据库失败!", null);
        }
        return new CommonResult<>(200, "插入数据库成功!", res);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new CommonResult<>(404, "查询不存在!", null);
        }
        return new CommonResult<>(200, "查询成功!", payment);
    }

}
