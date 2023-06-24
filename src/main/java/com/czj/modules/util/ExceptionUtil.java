package com.czj.modules.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.ResponseEntity;

public class ExceptionUtil {

    public static ResponseEntity<String> fallback(Throwable e){
        return ResponseEntity.ok("===被异常降级啦===");
    }

    public static ResponseEntity<String> handleException(BlockException e){
        return  ResponseEntity.ok("===被限流啦===");
    }
}
