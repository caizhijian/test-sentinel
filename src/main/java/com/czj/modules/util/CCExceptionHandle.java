package com.czj.modules.util;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.nacos.common.http.param.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class CCExceptionHandle implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.info("BlockExceptionHandler BlockException===============-"+e.getRule());
        Result r = null;

        if (e instanceof FlowException){
            r = Result.error(10,"被限流了");
        } else if (e instanceof DegradeException) {
            r = Result.error(101, "服务降级了");
        /*}else if (e instanceof ParamFlowException){
            r = Result.error(102,"热点参数限流了");*/
        }else if (e instanceof SystemBlockException){
            r = Result.error(103,"触发系统保护规则了");
        } else if (e instanceof AuthorityException){
            r = Result.error(104,"授权规则不通过");
        }
        //返回ison数据
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON);
        new ObjectMapper().writeValue(response.getWriter(),r);
    }



}
