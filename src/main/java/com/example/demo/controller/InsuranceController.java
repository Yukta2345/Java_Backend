package com.example.demo.controller;

import com.example.demo.model.Insurance;
import com.example.demo.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @GetMapping
    public List<Insurance> getAllInsurances() {
        return insuranceService.getAllInsurances();
    }

    @PostMapping
    public Insurance createInsurance(@RequestBody Insurance insurance) {
        return insuranceService.saveInsurance(insurance);
    }

    @GetMapping("/curated")
    public List<Insurance> getCuratedInsurances(@RequestParam int age) {
        return insuranceService.getCuratedInsurances(age);
    }
}
