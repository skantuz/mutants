package com.skantuz.mutants.mongodb;

import com.skantuz.mutants.mongodb.repository.DnaMongoRepository;
import com.skantuz.mutants.model.stats.Stats;
import com.skantuz.mutants.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

@RequiredArgsConstructor
public class MongoDbStatsService implements StatsRepository {

    private final DnaMongoRepository dnaMongoRepository;

    private Mono<Stats> monoStats;

    /**
     * connection db to count total registries and count registries with mutant in true
     * @return Stats with information
     */
    @Override
    public Mono<Stats> getStats() {
        if(Objects.isNull(monoStats)){
            monoStats = Mono.zip(dnaMongoRepository.count(), dnaMongoRepository.countDnaMongoDtoByMutantTrue())
                    .map(t -> Stats.builder()
                            .countHumanDna(t.getT1())
                            .countMutantDna(t.getT2())
                            .ratio(t.getT2().doubleValue()/t.getT1().doubleValue())
                            .builder())
                    .cache(Duration.ofSeconds(2));
        }
        return monoStats;

    }
}
