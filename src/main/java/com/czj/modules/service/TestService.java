package com.czj.modules.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.czj.modules.util.ExceptionUtil;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    /**
     * 测试链路
     * @return
     */
    @SentinelResource(value = "testLink", blockHandler = "link",blockHandlerClass = ExceptionUtil.class)
    public String testLink(){
        return "连接成功！";
    }

}
