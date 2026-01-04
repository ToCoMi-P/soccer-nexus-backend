package com.kickerz73.soccernexus_backend.controller;

import com.kickerz73.soccernexus_backend.entity.PlayerApplies;
import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.repository.PlayerAppliesRepository;
import com.kickerz73.soccernexus_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Instant;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PlayerAppliesController {

    private static final LocalDate currentMonday = LocalDate.of(2024, 5, 20);

    private static final String PATTERN_FORMAT = "dd.MM.yyyy HH:mm:ss.SSS";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.of("Europe/Berlin"));

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

    private LocalDateTime parseGermanDate(String instant) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");
    return LocalDateTime.parse(instant, formatter);
}

    @GetMapping("/playersappliesnextmonday")
    List<PlayerApplies> getAllPlayerAppliesForNextMonday(){
        /*if(playerAppliesRepository.findAll().isEmpty()){
            return new ArrayList<>();
        }*/
        if(!playerAppliesRepository.findAll().isEmpty()){
            return playerAppliesRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(p -> parseGermanDate(p.getInstant())))
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
        PlayerEntity toRemovedPlayer = playerRepository.getReferenceById(id);

        Optional<PlayerApplies> optPA = playerAppliesRepository.findAll().stream().filter(x-> x.getPlayer().equals(toRemovedPlayer)).findFirst();
        optPA.ifPresent(playerAppliesRepository::delete);
    }

    @PostMapping("/removeplayer")
    void removePlayer(@RequestParam List<Long> selectedPlayers) {
        for (Long id : selectedPlayers) {
            removePlayer(id);
        }
    }

    @DeleteMapping("/playersapplies/clear")
    void clearAllPlayerApplies() {
        playerAppliesRepository.deleteAll();
    }

}
