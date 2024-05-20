package com.kickerz73.soccernexus_backend.controller;

import com.kickerz73.soccernexus_backend.entity.PlayerApplies;
import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.repository.PlayerAppliesRepository;
import com.kickerz73.soccernexus_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.time.Instant;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PlayerAppliesController {

    @Autowired final PlayerRepository playerRepository;
    @Autowired
    private final PlayerAppliesRepository playerAppliesRepository;

    /*@GetMapping("/playersapplies/{id}")
    PlayerEntity getPlayerById(Long id){
        return playerRepository.findById(id).get();
    }*/

    @GetMapping("/playersapplies")
    List<PlayerApplies> getAllPlayerApplies(){
        /*if(playerAppliesRepository.findAll().isEmpty()){
            return new ArrayList<>();
        }*/
        return playerAppliesRepository.findAll();
    }

    @GetMapping("/playersappliesnextmonday")
    List<PlayerApplies> getAllPlayerAppliesForNextMonday(){
        /*if(playerAppliesRepository.findAll().isEmpty()){
            return new ArrayList<>();
        }*/
        if(!playerAppliesRepository.findAll().isEmpty()){
            return playerAppliesRepository.findAll()
                    .stream()
                    .filter(x->x.getDate().equals(LocalDate.of(2024, 5, 20)))
                    .sorted(Comparator.comparing(PlayerApplies::getInstant))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @PostMapping("/playersapplies/{id}")
    void addPlayer(@RequestParam Long id){
        PlayerEntity p = playerRepository.getReferenceById(id);
        PlayerApplies pa = new PlayerApplies(p, Instant.now(), LocalDate.of(2024, 5, 20));
        playerAppliesRepository.save(pa);
    }

    @PostMapping("/playersapplies")
    void addPlayer(@RequestParam List<Long> selectedPlayers) {
        for (Long id : selectedPlayers) {
            addPlayer(id);
        }
    }

}
