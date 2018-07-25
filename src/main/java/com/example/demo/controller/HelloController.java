package com.example.demo.controller;

import com.example.demo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    MathService mathService;

    @Autowired
    public HelloController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/math/pi")
    public String mathPi() {
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String mathCalculations(@RequestParam(defaultValue = "add") String operation,
                                  @RequestParam int x,
                                  @RequestParam int y) {
        return mathService.calculate(operation, x, y);
    }

    @PostMapping("/math/sum")
    public String mathSum(@RequestParam(value = "n") List<Integer> integerList) {
        return mathService.sum(integerList);
    }
}
