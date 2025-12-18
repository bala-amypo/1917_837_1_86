
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;

@RestController
public class FarmController {
    @Autowired
    FarmService farmService;

    @PostMapping("/postdata")
    public Farm postdata(@RequestBody Farm farm){
        return farmService.saveFarm(farm);
    }
    
    

}