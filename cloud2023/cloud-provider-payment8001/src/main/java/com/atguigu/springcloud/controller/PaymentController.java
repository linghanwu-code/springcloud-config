package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.enties.CommonResult;
import com.atguigu.springcloud.enties.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService  paymentService;
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;
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
        return new CommonResult<>(200, "查询成功!"+serverPort, payment);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        for (String service : discoveryClient.getServices()) {
            log.info("**********element" + service);

        }
        //查看服务下的实例
        for (ServiceInstance instance : discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE")) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }
}
