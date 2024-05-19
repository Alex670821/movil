package com.app.movil.repository;

import com.app.movil.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlertRepository extends JpaRepository<Alert,Long> {
}
