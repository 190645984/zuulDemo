package com.cww.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2018/11/9.
 */
@Component
public class FileterBeanConfigure {
    @Bean
    public AuthFilter forBiddenFilter(){
        return new AuthFilter();
    }
    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }
}
