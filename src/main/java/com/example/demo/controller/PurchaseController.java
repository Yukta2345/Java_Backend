package com.example.demo.controller;

import com.example.demo.model.Purchase;
import com.example.demo.service.PurchaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/buy/{userId}/{insuranceId}")
    public ResponseEntity<Purchase> buyPolicy(@PathVariable Long userId, @PathVariable Long insuranceId) {
        Purchase purchase = purchaseService.purchasePolicy(userId, insuranceId);
        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/download/{purchaseId}")
    public ResponseEntity<byte[]> downloadPolicy(@PathVariable Long purchaseId) {
        try {
            File file = purchaseService.getPolicyPdf(purchaseId);
            byte[] contents = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + file.getName());
            return new ResponseEntity<>(contents, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
