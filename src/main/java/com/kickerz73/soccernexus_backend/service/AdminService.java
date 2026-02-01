package com.kickerz73.soccernexus_backend.service;


import com.kickerz73.soccernexus_backend.entity.*;
import com.kickerz73.soccernexus_backend.repository.*;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AdminService {
    
    @Autowired private AdminRepository adminRepository;
    private AdminEntity adminEntity;

    @PostConstruct
    private void initAdminEntity() {
        List<AdminEntity> list = adminRepository.findAll();
        if (!list.isEmpty()) {
            this.adminEntity = list.get(0);  // Bestehendes laden
        } else {
            // Nur bei leerer Tabelle: Neues anlegen + speichern
            this.adminEntity = new AdminEntity(-1, 0, 0);
            adminRepository.save(this.adminEntity);
        }
    }

    public Integer getMaxPlayers() {
        return adminEntity.getMaxPlayers();
    }

    public AdminEntity setMaxPlayers(int maxPlayers) {
        if (adminEntity.getId() != -1) {
            adminEntity.setMaxPlayers(maxPlayers);
        } else {
            adminEntity = new AdminEntity(maxPlayers,  0, 0); // payPalClicks und darkModeClicks auf 0 setzen
        }
        adminRepository.save(adminEntity);

        return adminEntity;
    }

    public Integer getPayPalClicks() {
		return adminEntity.getPayPalClicks();
	}

    public void setPayPalClicks(int payPalClicks){
        if (adminEntity.getId() != null) {
            adminEntity.setPayPalClicks(payPalClicks);
        } else {
            adminEntity = new AdminEntity(-1, payPalClicks, 0); // maxPlayers auf -1 und darkModeClicks auf 0 setzen, wenn nicht vorhanden
        }
        adminRepository.save(adminEntity);
    }

    public Integer getDarkModeClicks() {
		return adminEntity.getDarkModeClicks();
	}

    public void setDarkModeClicks(int darkModeClicks){
       if (adminEntity.getId() != null) {
            adminEntity.setDarkModeClicks(darkModeClicks);
        } else {
            adminEntity = new AdminEntity(-1, 0, darkModeClicks); // maxPlayers und payPalClicks auf Standardwerte setzen, wenn nicht vorhanden
        }
        adminRepository.save(adminEntity);
    }

    public boolean isPlayerAppliesLocked () {
        return adminEntity.isPlayerAppliesLocked();
    }

    public void lockPlayerApplies() {
        adminEntity.setPlayerAppliesLocked(true);
    }

    public void unlockPlayerApplies() {
        adminEntity.setPlayerAppliesLocked(false);
    }
}
