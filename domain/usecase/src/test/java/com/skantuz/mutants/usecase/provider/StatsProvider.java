package com.skantuz.mutants.usecase.provider;

import com.skantuz.mutants.model.stats.Stats;

public class StatsProvider {
    public static Stats getStats(long mutants, long humans, double ratio) {
        return Stats.builder()
                .countMutantDna(mutants)
                .countHumanDna(humans)
                .ratio(ratio)
                .builder();
    }
}
