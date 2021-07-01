package com.skantuz.mutants.repository;

import com.skantuz.mutants.model.stats.Stats;
import reactor.core.publisher.Mono;

public interface StatsRepository {
    Mono<Stats> getStats();
}
