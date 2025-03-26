package com.example.demo.service;

import com.example.demo.model.Insurance;
import com.example.demo.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InsuranceService {
    
    @Autowired
    private InsuranceRepository insuranceRepository;

    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    public Insurance saveInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public List<Insurance> getCuratedInsurances(int age) {
        return insuranceRepository.findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(age, age);
    }
}
