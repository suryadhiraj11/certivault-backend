package com.klu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByOwnerId(Long ownerId);
}