package com.kickerz73.soccernexus_backend.controller;

import com.kickerz73.soccernexus_backend.entity.PlayerApplies;
import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.repository.PlayerAppliesRepository;
import com.kickerz73.soccernexus_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Instant;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:3000") https://soccer-nexus-backend.onrender.com
@RestController
@RequiredArgsConstructor
public class PlayerAppliesController {

    private static final LocalDate currentMonday = LocalDate.of(2024, 5, 20);

    private static final String PATTERN_FORMAT = "dd.MM.yyyy HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());

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
                    .filter(x->x.getDate().equals(currentMonday))
                    .sorted(Comparator.comparing(PlayerApplies::getInstant))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @PostMapping("/playersapplies/{id}")
    void addPlayer(@RequestParam Long id){
        PlayerEntity p = playerRepository.getReferenceById(id);

        PlayerApplies pa = new PlayerApplies(p, formatter.format(Instant.now()), currentMonday);
        playerAppliesRepository.save(pa);
    }

    @PostMapping("/playersapplies")
    void addPlayer(@RequestParam List<Long> selectedPlayers) {
        for (Long id : selectedPlayers) {
            addPlayer(id);
        }
    }

    @PostMapping("/removeplayer/{id}")
    void removePlayer(@RequestParam Long id){
        /*PlayerEntity p = playerRepository.getReferenceById(id);

        PlayerApplies pa = new PlayerApplies(p, formatter.format(Instant.now()), currentMonday);
        playerAppliesRepository.save(pa);*/
    }

    @PostMapping("/removeplayer")
    void removePlayer(@RequestParam List<Long> selectedPlayers) {
        for (Long id : selectedPlayers) {
            removePlayer(id);
        }
    }

}
