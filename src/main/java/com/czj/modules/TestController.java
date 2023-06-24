package com.czj.modules;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.czj.modules.util.ExceptionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/TestController")
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    @SentinelResource(value = "test-hello",
            fallback = "fallback",fallbackClass = ExceptionUtil.class,
            blockHandler = "handleException",blockHandlerClass = ExceptionUtil.class
    )
    public ResponseEntity<String> hello() throws Exception {
        double a = Math.random()*100+50;
        try {
            Thread.sleep(Long.valueOf((long) a));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.ok("fail!!!!!");
        }
        if(a>=100){
            throw new Exception("抛出异常！");
        }
        return ResponseEntity.ok("Hello World!!!!!");
    }

}
