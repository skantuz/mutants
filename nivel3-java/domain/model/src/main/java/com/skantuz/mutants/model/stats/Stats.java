package com.skantuz.mutants.model.stats;

public class Stats {
    private Integer countMutantDna;
    private Integer countHumanDna;
    private Double ratio;

    private Stats(StatsBuilder statsBuilder) {
        this.countMutantDna = statsBuilder.countMutantDna;
        this.countHumanDna = statsBuilder.countHumanDna;
        this.ratio = statsBuilder.ratio;
    }

    public static StatsBuilder builder() {
        return new StatsBuilder();
    }

    public Integer getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(Integer countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public Integer getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(Integer countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public static class StatsBuilder {
        private Integer countMutantDna;
        private Integer countHumanDna;
        private Double ratio;

        private StatsBuilder() {
        }

        public StatsBuilder countMutantDna(Integer countMutantDna) {
            this.countMutantDna = countMutantDna;
            return this;
        }

        public StatsBuilder countHumanDna(Integer countHumanDna) {
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
