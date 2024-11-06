package com.kickerz73.soccernexus_backend.controller;

import com.kickerz73.soccernexus_backend.entity.AdminEntity;
import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.repository.AdminRepository;
import com.kickerz73.soccernexus_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final AdminRepository adminRepository;

    @GetMapping("/admin/maxPlayers")
    AdminEntity getMaxPlayers(){
        List<AdminEntity> list = adminRepository.findAll();
        if(list.size() > 0) return list.get(0);
        return new AdminEntity(-1);
    }

    @PostMapping("/admin/maxPlayers")
    void setMaxPlayers(@RequestParam Integer maxPlayers){
        AdminEntity adminEntity = getMaxPlayers();
        if(adminEntity.getId() != null){
            adminEntity.setMaxPlayers(maxPlayers);

        }else{
            adminEntity = new AdminEntity(maxPlayers);
        }
        adminRepository.save(adminEntity);
    }

}
