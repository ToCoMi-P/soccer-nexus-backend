package com.kickerz73.soccernexus_backend.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kickerz73.soccernexus_backend.entity.MatchDayEntity;
import com.kickerz73.soccernexus_backend.enums.MatchLocation;
import com.kickerz73.soccernexus_backend.repository.MatchDayRepository;


@Service
@Transactional
public class MatchDayService {

    @Autowired private MatchDayRepository matchDayRepository;

    public Optional<MatchDayEntity> findFirstByDateAfterOrderByDateAsc(LocalDate date) {
        return matchDayRepository.findFirstByDateAfterOrderByDateAsc(date);
    }

    public MatchDayEntity getActiveMatchDay() {
        return matchDayRepository.findByIsActiveTrue()
            .orElseThrow(() -> new IllegalStateException("Kein aktiver MatchDay"));
    }

    public MatchLocation getActiveMatchDayLocation() {
        return getActiveMatchDay().getMatchLocation();
    }

    public MatchDayEntity closeActiveMatchDay() {
        MatchDayEntity matchDayEntity = getActiveMatchDay();

        matchDayEntity.setActive(false);
        return matchDayRepository.save(matchDayEntity);
    }

    public MatchDayEntity openNextActiveMatchDay() {
        LocalDate nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        
        return matchDayRepository.findFirstByDateAfterOrderByDateAsc(LocalDate.now())
            .filter(day -> day.getDate().equals(nextMonday))
            .map(day -> {
                day.setActive(true);
                day.setPrice(MatchLocation.ALOHA.getPrice());
                return matchDayRepository.save(day);
            })
            .orElse(createNewMatchDay(nextMonday, MatchLocation.RUEHME));
    }

    private MatchDayEntity createNewMatchDay(LocalDate date, MatchLocation location) {
        MatchDayEntity matchDay = new MatchDayEntity(date, location);
        matchDay.setActive(true);
        return matchDayRepository.save(matchDay);
    }
}
