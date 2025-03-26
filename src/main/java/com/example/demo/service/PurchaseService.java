package com.example.demo.service;

import com.example.demo.model.Insurance;
import com.example.demo.model.Purchase;
import com.example.demo.repository.InsuranceRepository;
import com.example.demo.repository.PurchaseRepository;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final InsuranceRepository insuranceRepository;

    private static final String POLICY_DIRECTORY = "policies/";

    public PurchaseService(PurchaseRepository purchaseRepository, InsuranceRepository insuranceRepository) {
        this.purchaseRepository = purchaseRepository;
        this.insuranceRepository = insuranceRepository;
    }

    public Purchase purchasePolicy(Long userId, Long insuranceId) {
        Optional<Insurance> insurance = insuranceRepository.findById(insuranceId);
        if (insurance.isEmpty()) {
            throw new RuntimeException("Insurance policy not found!");
        }

        // Save purchase record
        Purchase purchase = new Purchase(userId, insurance.get());
        purchaseRepository.save(purchase);

        // Generate PDF
        String filePath = generatePolicyPdf(purchase);
        purchase.setPolicyFilePath(filePath);
        purchaseRepository.save(purchase);

        return purchase;
    }

    public String generatePolicyPdf(Purchase purchase) {
        try {
            // Ensure directory exists
            Files.createDirectories(Paths.get(POLICY_DIRECTORY));

            // Define policy file path
            String filePath = POLICY_DIRECTORY + "policy_" + purchase.getUserId() + "_" + purchase.getInsurance().getId() + ".pdf";

            // Create PDF
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Insurance Policy Document");
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Policy Holder ID: " + purchase.getUserId());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Insurance ID: " + purchase.getInsurance().getId());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Policy Name: " + purchase.getInsurance().getName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Issued Date: " + LocalDateTime.now());

                contentStream.newLineAtOffset(0, -40);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.showText("Terms & Conditions:");

                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("1. Subject to standard policy conditions.");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("2. Claims must be filed within validity period.");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("3. The policyholder agrees to all terms.");

                contentStream.endText();
                contentStream.close();

                document.save(filePath);
            }
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate policy PDF", e);
        }
    }

    public File getPolicyPdf(Long purchaseId) {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);
        if (purchase.isEmpty()) {
            throw new RuntimeException("Purchase not found!");
        }

        String filePath = purchase.get().getPolicyFilePath();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Policy PDF not found!");
        }
        return file;
    }
}
