package com.example.devprofile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jianjun Xue
 * @date 2025/7/18
 * @description Test1
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return "Hello " + name + "!";
    }
}
