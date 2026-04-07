package com.klu.service;

import com.klu.entity.Certificate;
import com.klu.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certRepo;

    public Certificate addCertificate(Certificate cert) {

        cert.setCertificateId("CERT-" + UUID.randomUUID());
        cert.setVerificationStatus("pending");
        cert.setRenewalStatus("none");
        cert.setCreatedAt(java.time.LocalDateTime.now().toString());

        return certRepo.save(cert);
    }

    public List<Certificate> getAllCertificates() {
        return certRepo.findAll();
    }

    public Certificate getById(Long id) {
        return certRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    public void deleteCertificate(Long id) {
        certRepo.deleteById(id);
    }

    public Certificate updateCertificate(Long id, Certificate updatedCert) {
        Certificate cert = getById(id);

        cert.setName(updatedCert.getName());
        cert.setIssuer(updatedCert.getIssuer());
        cert.setIssueDate(updatedCert.getIssueDate());
        cert.setExpiryDate(updatedCert.getExpiryDate());
        cert.setVisibility(updatedCert.getVisibility());
        cert.setDescription(updatedCert.getDescription());
        cert.setFile(updatedCert.getFile());

        return certRepo.save(cert);
    }

    public Certificate verifyCertificate(Long id, Long adminId) {
        Certificate cert = getById(id);

        cert.setVerificationStatus("verified");
        cert.setVerifiedBy(adminId);   // 🔥 FIX
        cert.setVerifiedAt(java.time.LocalDateTime.now().toString()); // optional

        return certRepo.save(cert);
    }

    public Certificate rejectCertificate(Long id) {
        Certificate cert = getById(id);
        cert.setVerificationStatus("rejected");
        return certRepo.save(cert);
    }

    public Certificate requestRenewal(Long id) {
        Certificate cert = getById(id);
        cert.setRenewalStatus("pending");
        return certRepo.save(cert);
    }

    public Certificate approveRenewal(Long id, String newExpiryDate) {
        Certificate cert = getById(id);
        cert.setExpiryDate(newExpiryDate);
        cert.setRenewalStatus("approved");
        return certRepo.save(cert);
    }

    public Certificate rejectRenewal(Long id) {
        Certificate cert = getById(id);
        cert.setRenewalStatus("rejected");
        return certRepo.save(cert);
    }

    public List<Certificate> getCertificatesByUser(Long userId) {
        return certRepo.findByOwnerId(userId);
    }
    
    public Certificate updateLikes(Long id, String likes) {
        Certificate cert = getById(id);
        cert.setLikes(likes);
        return certRepo.save(cert);
    }
    
   
    public Certificate updateComments(Long id, String comments) {
        Certificate cert = getById(id);
        cert.setComments(comments);
        return certRepo.save(cert);
    }
}