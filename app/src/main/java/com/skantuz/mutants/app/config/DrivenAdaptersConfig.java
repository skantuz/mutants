package com.skantuz.mutants.app.config;

import com.skantuz.mutants.identify_mutants.IdentifyMutantService;
import com.skantuz.mutants.mongodb.MongoDbSaveService;
import com.skantuz.mutants.mongodb.MongoDbStatsService;
import com.skantuz.mutants.mongodb.repository.DnaMongoRepository;
import com.skantuz.mutants.repository.SaveDnaRepository;
import com.skantuz.mutants.repository.StatsRepository;
import com.skantuz.mutants.repository.ValidateMutantsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DrivenAdaptersConfig {
    @Bean
    public StatsRepository statsRepository(DnaMongoRepository dnaMongoService) {
        return new MongoDbStatsService(dnaMongoService);
    }

    @Bean
    public SaveDnaRepository saveDnaRepository(DnaMongoRepository dnaMongoService) {
        return new MongoDbSaveService(dnaMongoService);
    }

    @Bean
    public ValidateMutantsRepository validateMutantsRepository() {
        return new IdentifyMutantService();
    }
}
