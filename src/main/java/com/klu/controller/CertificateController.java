package com.klu.controller;

import com.klu.entity.Certificate;
import com.klu.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin
public class CertificateController {

    @Autowired
    private CertificateService service;

    // ================= BASIC CRUD =================

    @PostMapping
    public Certificate add(@RequestBody Certificate cert) {
        return service.addCertificate(cert);
    }

    @GetMapping
    public List<Certificate> getAll() {
        return service.getAllCertificates();
    }

    @GetMapping("/{id}")
    public Certificate getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Certificate update(@PathVariable Long id, @RequestBody Certificate cert) {
        return service.updateCertificate(id, cert);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteCertificate(id);
        return "Deleted";
    }

    // ================= VERIFICATION =================

    @PutMapping("/{id}/verify")
    public Certificate verify(@PathVariable Long id,
                              @RequestParam Long adminId) {
        return service.verifyCertificate(id, adminId);
    }

    @PutMapping("/{id}/reject")
    public Certificate reject(@PathVariable Long id) {
        return service.rejectCertificate(id);
    }

    // ================= RENEWAL =================

    @PutMapping("/{id}/renew")
    public Certificate renew(@PathVariable Long id) {
        return service.requestRenewal(id);
    }

    @PutMapping("/{id}/approve-renewal")
    public Certificate approveRenewal(@PathVariable Long id,
                                      @RequestParam String expiryDate) {
        return service.approveRenewal(id, expiryDate);
    }

    @PutMapping("/{id}/reject-renewal")
    public Certificate rejectRenewal(@PathVariable Long id) {
        return service.rejectRenewal(id);
    }

    // ================= FILTER =================

    @GetMapping("/user/{userId}")
    public List<Certificate> getByUser(@PathVariable Long userId) {
        return service.getCertificatesByUser(userId);
    }

    // ================= SOCIAL =================

    @PutMapping("/{id}/likes")
    public Certificate updateLikes(@PathVariable Long id,
                                   @RequestBody Map<String, String> body) {
        return service.updateLikes(id, body.get("likes"));
    }

    @PutMapping("/{id}/comments")
    public Certificate updateComments(@PathVariable Long id,
                                      @RequestBody Map<String, String> body) {
        return service.updateComments(id, body.get("comments"));
    }
}