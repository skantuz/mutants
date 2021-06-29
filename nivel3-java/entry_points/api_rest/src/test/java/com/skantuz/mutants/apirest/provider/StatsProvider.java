package com.skantuz.mutants.apirest.provider;

import com.skantuz.mutants.model.stats.Stats;

public class StatsProvider {
    public static Stats getStats(int mutants, int humans, double ratio) {
        return Stats.builder()
                .countMutantDna(mutants)
                .countHumanDna(humans)
                .ratio(ratio)
                .builder();
    }
}
