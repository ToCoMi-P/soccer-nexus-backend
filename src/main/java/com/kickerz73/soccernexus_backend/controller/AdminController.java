package com.kickerz73.soccernexus_backend.controller;

import com.kickerz73.soccernexus_backend.entity.AdminEntity;
import com.kickerz73.soccernexus_backend.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final AdminRepository adminRepository;

    // Methode, um das Admin-Objekt mit maxPlayers zu holen
    @GetMapping("/admin/maxPlayers")
    AdminEntity getMaxPlayers() {
        List<AdminEntity> list = adminRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        if (!list.isEmpty()) return list.get(0);
        return new AdminEntity(-1, 0, 0); // Standardwert für maxPlayers, payPalClicks und darkModeClicks
    }

    // Methode, um maxPlayers zu setzen
    @PostMapping("/admin/maxPlayers")
    void setMaxPlayers(@RequestParam Integer maxPlayers) {
        AdminEntity adminEntity = getMaxPlayers();
        if (adminEntity.getId() != null) {
            adminEntity.setMaxPlayers(maxPlayers);
        } else {
            adminEntity = new AdminEntity(maxPlayers, 0, 0); // payPalClicks und darkModeClicks auf 0 setzen
        }
        adminRepository.save(adminEntity);
    }

    // Endpunkt, um payPalClicks zu erhalten
    @GetMapping("/admin/payPalClicks")
    Integer getPayPalClicks() {
        AdminEntity adminEntity = getMaxPlayers();
        return adminEntity.getPayPalClicks();
    }

    // Endpunkt, um payPalClicks zu aktualisieren
    @PostMapping(value = "/admin/payPalClicks", consumes = MediaType.APPLICATION_JSON_VALUE)
    void setPayPalClicks(@RequestBody Map<String, Integer> payload) {
        Integer payPalClicks = payload.get("payPalClicks");
        AdminEntity adminEntity = getMaxPlayers();
        if (adminEntity.getId() != null) {
            adminEntity.setPayPalClicks(payPalClicks);
        } else {
            adminEntity = new AdminEntity(-1, payPalClicks, 0); // maxPlayers auf -1 und darkModeClicks auf 0 setzen, wenn nicht vorhanden
        }
        adminRepository.save(adminEntity);
    }

    // Neuer Endpunkt, um darkModeClicks zu erhalten
    @GetMapping("/admin/darkModeClicks")
    Integer getDarkModeClicks() {
        AdminEntity adminEntity = getMaxPlayers();
        return adminEntity.getDarkModeClicks();
    }

    // Neuer Endpunkt, um darkModeClicks zu aktualisieren
    @PostMapping(value = "/admin/darkModeClicks", consumes = MediaType.APPLICATION_JSON_VALUE)
    void setDarkModeClicks(@RequestBody Map<String, Integer> payload) {
        Integer darkModeClicks = payload.get("darkModeClicks");
        AdminEntity adminEntity = getMaxPlayers();
        if (adminEntity.getId() != null) {
            adminEntity.setDarkModeClicks(darkModeClicks);
        } else {
            adminEntity = new AdminEntity(-1, 0, darkModeClicks); // maxPlayers und payPalClicks auf Standardwerte setzen, wenn nicht vorhanden
        }
        adminRepository.save(adminEntity);
    }
}
