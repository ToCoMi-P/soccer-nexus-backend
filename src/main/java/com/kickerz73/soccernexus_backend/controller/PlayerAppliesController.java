package com.kickerz73.soccernexus_backend.controller;

import com.kickerz73.soccernexus_backend.entity.PlayerApplies;
import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.repository.PlayerAppliesRepository;
import com.kickerz73.soccernexus_backend.repository.PlayerRepository;
import com.kickerz73.soccernexus_backend.service.AdminService;
import com.kickerz73.soccernexus_backend.service.PlayerAppliesService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PlayerAppliesController {

    @Autowired final PlayerRepository playerRepository;
    
    @Autowired private final PlayerAppliesRepository playerAppliesRepository;

    @Autowired private PlayerAppliesService playerAppliesService;
    @Autowired private AdminService adminService;

    @GetMapping("/playersapplies/{id}")
    PlayerEntity getPlayerById(Long id){
        return playerRepository.findById(id).get();
    }

    @GetMapping("/playersapplies")
    List<PlayerApplies> getAllPlayerApplies(){
        return playerAppliesRepository.findAll();
    }

    @GetMapping("/playersapplies/active")
    List<PlayerApplies> getAllPlayerAppliesForNextMonday(){
        return playerAppliesService.getActivePlayerApplies();
    }

    @GetMapping("/playerapplies/backupCandidate")
    List<PlayerApplies> getAllBackUpCandidate(){
        return playerAppliesService.getAllBackupCandidate();
    }

    @PostMapping("/playersapplies/{id}")
    void addPlayer(@RequestParam Long id){
        playerAppliesService.addPlayer(id);
    }

    @PostMapping("/playersapplies")
    void addPlayer(@RequestParam List<Long> selectedPlayers) {
        selectedPlayers.forEach(playerId -> addPlayer(playerId));
    }

    @PostMapping("/removeplayer/{id}")
    void removePlayer(@RequestParam Long id){
        PlayerEntity toRemovedPlayer = playerRepository.getReferenceById(id);

        Optional<PlayerApplies> optPA = playerAppliesRepository.findAll().stream().filter(x-> x.getPlayer().equals(toRemovedPlayer)).findFirst();
        optPA.ifPresent(playerAppliesRepository::delete);
    }

    @PostMapping("/removeplayer")
    void removePlayer(@RequestParam List<Long> selectedPlayers) {
        selectedPlayers.forEach(playerid -> removePlayer(playerid));
    }

    @DeleteMapping("/playersapplies/clear")
    void clearAllPlayerApplies() {
        playerAppliesService.clearAllPlayerApplies();
    }

    @GetMapping("/playersapplies/lock")
    void lockPlayerApplies(){
        adminService.lockPlayerApplies();
    }

    @GetMapping("/playersapplies/unlock")
    void unLockPlayerApplies(){
        adminService.unlockPlayerApplies();
    }

}
