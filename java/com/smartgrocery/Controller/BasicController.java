package com.smartgrocery.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
	@GetMapping("/hello")
	public String hello() {
		return "Jai Bolo Ganesh Maharaj kii JAI !!!!";
	}
}
//package com.smartgrocery.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "Welcome to SmartGrocery!";
//    }
//}