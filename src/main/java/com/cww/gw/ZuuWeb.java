package com.cww.gw;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2018/11/9.
 */
@RestController
public class ZuuWeb {
    @RequestMapping("testZuul")
    public String test(){
        return "this is a  zuul";
    }
    @RequestMapping("zuulError")
    public String error(){
        return "this is a  error";
    }
    @RequestMapping("default/{1}")
    public String noPage(){
        return "this is a default";
    }
}
