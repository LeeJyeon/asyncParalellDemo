package com.test.project.async.controller;

import com.test.project.async.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StartController {

    @Autowired
    StartService startService;

    @GetMapping("test")
    @ResponseBody
    public void findName(@RequestParam(value = "context") String context) {
        startService.receiveData(context);
    }
}
