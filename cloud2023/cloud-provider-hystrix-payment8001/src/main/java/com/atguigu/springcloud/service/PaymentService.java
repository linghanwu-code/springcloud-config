package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_ok(Integer id) {
        return "线程池 :" + Thread.currentThread().getName() + "paymentinfo_ok,id:" + id + "\t" + "你好";
    }

//三秒以内走正常的业务逻辑,超出三秒我们出错,需要有兜底的方法
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池 :" + Thread.currentThread().getName() + "paymentinfo_ok,id:" + id + "\t" + "出错拉,耗时3秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池 :" + Thread.currentThread().getName() + "paymentinfo_ok,id:" + id + "\t" + "00.00服务出错了";
    }
}
