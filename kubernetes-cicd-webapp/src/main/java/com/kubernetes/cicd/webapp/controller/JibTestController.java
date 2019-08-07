package com.kubernetes.cicd.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@RequestMapping(value = "/jib/test")
@RestController
public class JibTestController {

    private static final Logger log = LoggerFactory.getLogger(JibTestController.class);

    @ResponseBody
    @GetMapping(value = "/test")
    public String startActivity(@RequestParam(required = true) final String name) {
        return name;
    }


}
