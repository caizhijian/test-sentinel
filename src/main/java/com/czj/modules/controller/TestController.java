package com.czj.modules.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.czj.modules.service.TestService;
import com.czj.modules.util.ExceptionUtil;
import com.czj.modules.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/TestController")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/hello")
    @SentinelResource(value = "test-hello",
            fallback = "fallback",fallbackClass = ExceptionUtil.class,
            blockHandler = "handleException",blockHandlerClass = ExceptionUtil.class
    )
    public Result<?> hello() throws Exception {
        double a = Math.random()*50+20;
        try {
            Thread.sleep(Long.valueOf((long) a));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Result.ok("fail!!!!!");
        }
        /*if(a>=100){
            throw new Exception("抛出异常！");
        }*/
        return Result.ok("Hello World!!!!!");
    }

    @GetMapping("/flow")
//    @SentinelResource(value = "test-flow")
    public Result<?> flow() throws Exception {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Result.ok("fail!!!!!");
        }
        return Result.ok("flow");
    }

    @GetMapping("/test1")
    public Result<?> test1(){
        return Result.ok(testService.testLink());
    }

    @GetMapping("/test2")
    public Result<?> test2(){
        return Result.ok(testService.testLink());
    }

}
