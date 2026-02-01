package com.kickerz73.soccernexus_backend.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kickerz73.soccernexus_backend.entity.PlayerApplies;
import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.repository.PlayerAppliesRepository;
import com.kickerz73.soccernexus_backend.repository.PlayerRepository;
import com.kickerz73.soccernexus_backend.util.DateUtil;

@Service
@Transactional
public class PlayerAppliesService {

    @Autowired private PlayerAppliesRepository playerAppliesRepository;
    @Autowired private PlayerRepository playerRepository;
    @Autowired private PlayerService playerService;

    @Autowired private AdminService adminService;

    @Autowired private MatchDayService matchDayService;

    public List<PlayerApplies> getActivePlayerApplies() {
        if(!playerAppliesRepository.findAll().isEmpty()){
            return playerAppliesRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(p -> DateUtil.parseGermanDateFull(p.getInstant())))
                    .limit(adminService.getMaxPlayers())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    
    public List<PlayerApplies> getAllBackupCandidate() {
        List<PlayerApplies> all = playerAppliesRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(p -> DateUtil.parseGermanDateFull(p.getInstant())))
            .collect(Collectors.toList());
        
        if (all.size() <= adminService.getMaxPlayers()) {
            return new ArrayList<>();  // Keine Backup-Kandidaten
        }

        return all.subList(adminService.getMaxPlayers(), all.size());  // 15-17 → Spieler 16-18
    }


    public PlayerApplies addPlayer(Long id) {
        if(!adminService.isPlayerAppliesLocked()){
            PlayerEntity p = playerRepository.getReferenceById(id);
            
            if(playerService.canPlayerRegister(p)){
                PlayerApplies pa = new PlayerApplies(p, DateUtil.formatter.format(Instant.now()), matchDayService.getActiveMatchDay());
                return playerAppliesRepository.save(pa);
            }
        }
        return null;
    }

    public void clearAllPlayerApplies() {
        playerAppliesRepository.deleteAll();
    }
}
