package com.skantuz.mutants.config;

import com.skantuz.mutants.mongodb.MongoDbSaveService;
import com.skantuz.mutants.mongodb.MongoDbStatsService;
import com.skantuz.mutants.mongodb.service.DnaMongoService;
import com.skantuz.mutants.identify_mutants.IdentifyMutantService;
import com.skantuz.mutants.repository.SaveDnaRepository;
import com.skantuz.mutants.repository.StatsRepository;
import com.skantuz.mutants.repository.ValidateMutantsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DrivenAdaptersConfig {
    @Bean
    public StatsRepository statsRepository(DnaMongoService dnaMongoService) {
        return new MongoDbStatsService(dnaMongoService);
    }

    @Bean
    public SaveDnaRepository saveDnaRepository(DnaMongoService dnaMongoService) {
        return new MongoDbSaveService(dnaMongoService);
    }

    @Bean
    public ValidateMutantsRepository validateMutantsRepository() {
        return new IdentifyMutantService();
    }
}
