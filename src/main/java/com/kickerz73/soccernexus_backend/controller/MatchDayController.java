package com.kickerz73.soccernexus_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kickerz73.soccernexus_backend.entity.MatchDayEntity;
import com.kickerz73.soccernexus_backend.service.MatchDayService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class MatchDayController {

    @Autowired private MatchDayService matchDayService;

    @GetMapping("/matchday")
    public List<MatchDayEntity> getAllMatchDays() {
        //TODO: matchDayService.get

        return null;
    }
  
}
