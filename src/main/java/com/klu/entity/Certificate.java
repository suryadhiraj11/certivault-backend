package com.klu.entity;

import jakarta.persistence.*;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certificateId;
    private String name;
    private String issuer;

    private String issueDate;
    private String expiryDate;

    private String visibility;
    private String verificationStatus;
    private String renewalStatus;

    private Long ownerId;        // ✅ matches frontend
    private String ownerName;    // ✅ matches frontend

    private String description;

    private Long verifiedBy;
    private String verifiedAt;
    private String createdAt;

    @Lob
    private String file; // JSON string (type, data, name)
    
    @Lob
    private String likes; // JSON string

    @Lob
    private String comments; // JSON string

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCertificateId() { return certificateId; }
    public void setCertificateId(String certificateId) { this.certificateId = certificateId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }

    public String getIssueDate() { return issueDate; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public String getVisibility() { return visibility; }
    public void setVisibility(String visibility) { this.visibility = visibility; }

    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }

    public String getRenewalStatus() { return renewalStatus; }
    public void setRenewalStatus(String renewalStatus) { this.renewalStatus = renewalStatus; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getVerifiedBy() { return verifiedBy; }
    public void setVerifiedBy(Long verifiedBy) { this.verifiedBy = verifiedBy; }
    
    public String getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(String verifiedAt) { this.verifiedAt = verifiedAt; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getFile() { return file; }
    public void setFile(String file) { this.file = file; }

    public String getLikes() { return likes; }
    public void setLikes(String likes) { this.likes = likes; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}