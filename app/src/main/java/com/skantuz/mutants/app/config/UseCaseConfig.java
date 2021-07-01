package com.skantuz.mutants.app.config;

import com.skantuz.mutants.repository.SaveDnaRepository;
import com.skantuz.mutants.repository.StatsRepository;
import com.skantuz.mutants.repository.ValidateMutantsRepository;
import com.skantuz.mutants.usecase.DnaUseCase;
import com.skantuz.mutants.usecase.StatsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public DnaUseCase dnaUseCase(SaveDnaRepository saveDnaRepository,
                                 ValidateMutantsRepository validateMutantsRepository){
        return new DnaUseCase(saveDnaRepository,validateMutantsRepository);
    }

    @Bean
    public StatsUseCase statsUseCase(StatsRepository statsRepository){
        return new StatsUseCase(statsRepository);
    }
}
