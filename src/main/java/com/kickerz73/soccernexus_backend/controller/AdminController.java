package com.kickerz73.soccernexus_backend.controller;

import com.kickerz73.soccernexus_backend.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AdminController {

    @Autowired private AdminService adminService;

    // Methode, um das Admin-Objekt mit maxPlayers zu holen
    @GetMapping("/admin/maxPlayers")
    private Integer getMaxPlayers() {
        return adminService.getMaxPlayers();
    }

    // Methode, um maxPlayers zu setzen
    @PostMapping("/admin/maxPlayers")
    void setMaxPlayers(@RequestParam Integer maxPlayers) {
       adminService.setMaxPlayers(maxPlayers);
    }

    // Endpunkt, um payPalClicks zu erhalten
    @GetMapping("/admin/payPalClicks")
    Integer getPayPalClicks() {
        return adminService.getPayPalClicks();
    }

    // Endpunkt, um payPalClicks zu aktualisieren
    @PostMapping(value = "/admin/payPalClicks", consumes = MediaType.APPLICATION_JSON_VALUE)
    void setPayPalClicks(@RequestBody Map<String, Integer> payload) {
        Integer payPalClicks = payload.get("payPalClicks");

        adminService.setPayPalClicks(payPalClicks);
        
    }

    // Neuer Endpunkt, um darkModeClicks zu erhalten
    @GetMapping("/admin/darkModeClicks")
    Integer getDarkModeClicks() {
        return adminService.getDarkModeClicks();
    }

    // Neuer Endpunkt, um darkModeClicks zu aktualisieren
    @PostMapping(value = "/admin/darkModeClicks", consumes = MediaType.APPLICATION_JSON_VALUE)
    void setDarkModeClicks(@RequestBody Map<String, Integer> payload) {
        Integer darkModeClicks = payload.get("darkModeClicks");
        adminService.setDarkModeClicks(darkModeClicks);
        
    }

    // Neuer Endpunkt, um darkModeClicks zu erhalten
    @GetMapping("/admin/isPlayerAppliesLocked")
    boolean isPlayerAppliesLocked() {
        return adminService.isPlayerAppliesLocked();
    }
}
