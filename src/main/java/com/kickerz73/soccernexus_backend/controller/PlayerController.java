package com.kickerz73.soccernexus_backend.controller;

import com.kickerz73.soccernexus_backend.entity.PlayerEntity;
import com.kickerz73.soccernexus_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin//(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private final PlayerRepository playerRepository;

    @GetMapping("/players/{id}")
    PlayerEntity getPlayerById(Long id){
        return playerRepository.findById(id).get();
    }

    @GetMapping("/players")
    List<PlayerEntity> getAllPlayers(){
        return playerRepository.findAll();
    }

    @PostMapping("/players")
    void addPlayer(@RequestParam String vorname, @RequestParam String nachname){
        playerRepository.save(new PlayerEntity(vorname, nachname));
    }

}
