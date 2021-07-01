package com.skantuz.mutants.model.dna;

public class Dna {
    private String id;
    private String[] dna;
    private Boolean mutant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public Boolean getMutant() {
        return mutant;
    }

    public void setMutant(Boolean mutant) {
        this.mutant = mutant;
    }

    private Dna(DnaBuilder dnaBuilder) {
        this.id = dnaBuilder.id;
        this.dna = dnaBuilder.dna;
        this.mutant = dnaBuilder.mutant;
    }
    public static DnaBuilder builder(){
        return new DnaBuilder();
    }

    public static class DnaBuilder{
        private String id;
        private String[] dna;
        private Boolean mutant;

        public DnaBuilder() {
        }

        public Dna build(){
            return new Dna(this);
        }

        public DnaBuilder id(String id){
            this.id = id;
            return this;
        }

        public DnaBuilder dna(String[] dna){
            this.dna = dna;
            return this;
        }

        public DnaBuilder mutant(Boolean mutant){
            this.mutant=mutant;
            return this;
        }

    }
}
