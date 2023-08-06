package com.czj.modules.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class ExceptionUtil {

    public static Result<String> fallback(Throwable e){
        return Result.ok("===被异常降级啦===");
    }

    public static Result<String> handleException(BlockException e){
        return  Result.ok("===被限流啦===");
    }

    public static Result<String> flowThread(BlockException e){
        return  Result.ok("===被限流啦===");
    }

    public static String link(BlockException e){
        return "===被限流啦===";
    }
}
