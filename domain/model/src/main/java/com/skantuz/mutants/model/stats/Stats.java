package com.skantuz.mutants.model.stats;

public class Stats {
    private Long countMutantDna;
    private Long countHumanDna;
    private Double ratio;

    private Stats(StatsBuilder statsBuilder) {
        this.countMutantDna = statsBuilder.countMutantDna;
        this.countHumanDna = statsBuilder.countHumanDna;
        this.ratio = statsBuilder.ratio;
    }

    public static StatsBuilder builder() {
        return new StatsBuilder();
    }

    public Long getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(Long countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public Long getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(Long countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public static class StatsBuilder {
        private Long countMutantDna;
        private Long countHumanDna;
        private Double ratio;

        private StatsBuilder() {
        }

        public StatsBuilder countMutantDna(Long countMutantDna) {
            this.countMutantDna = countMutantDna;
            return this;
        }

        public StatsBuilder countHumanDna(Long countHumanDna) {
            this.countHumanDna = countHumanDna;
            return this;
        }

        public StatsBuilder ratio(Double ratio) {
            this.ratio = ratio;
            return this;
        }

        public Stats builder() {
            return new Stats(this);
        }
    }
}
