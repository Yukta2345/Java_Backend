package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    private Insurance insurance;

    private LocalDateTime purchaseDate;
    private String policyFilePath; // Path for generated PDF policy

    // Default constructor
    public Purchase() {}

    // Constructor with required fields
    public Purchase(Long userId, Insurance insurance) {
        this.userId = userId;
        this.insurance = insurance;
        this.purchaseDate = LocalDateTime.now();
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPolicyFilePath() { // ✅ Added this missing getter
        return policyFilePath;
    }

    public void setPolicyFilePath(String policyFilePath) { // ✅ Added this missing setter
        this.policyFilePath = policyFilePath;
    }
}
