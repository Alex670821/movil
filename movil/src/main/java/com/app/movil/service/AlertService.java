package com.app.movil.service;

import com.app.movil.entity.Alert;
import com.app.movil.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Alert getAlertById(Long id) {
        return alertRepository.findById(id).orElse(null);
    }

    public Alert createAlert(Alert alert) {
        alert.setTimestamp(LocalDateTime.now());
        return alertRepository.save(alert);
    }

    public Alert updateAlert(Long id, Alert alertDetails) {
        Alert alert = alertRepository.findById(id).orElse(null);
        if (alert != null) {
            alert.setTitle(alertDetails.getTitle());
            alert.setDescription(alertDetails.getDescription());
            alert.setLocation(alertDetails.getLocation());
            alert.setTimestamp(LocalDateTime.now());
            return alertRepository.save(alert);
        }
        return null;
    }

    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }
}
