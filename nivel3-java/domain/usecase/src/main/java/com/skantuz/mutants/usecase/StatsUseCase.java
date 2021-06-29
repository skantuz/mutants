package com.skantuz.mutants.usecase;


import com.skantuz.mutants.model.stats.Stats;
import com.skantuz.mutants.repository.StatsRepository;
import reactor.core.publisher.Mono;

public class StatsUseCase {

    private final StatsRepository statsRepository;

    public StatsUseCase(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public Mono<Stats> getStats(){
        return statsRepository.getStats();
    }


}
