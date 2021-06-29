package com.skantuz.mutants.identify_mutants;

import com.skantuz.mutants.model.dna.Dna;
import com.skantuz.mutants.repository.ValidateMutantsRepository;

import java.util.Arrays;
import java.util.regex.Pattern;

public class IdentifyMutantService implements ValidateMutantsRepository {

    //regex to validate if contain mutant dna
    private static final Pattern MUTANT_REGEX = Pattern.compile("AAAA|TTTT|CCCC|GGGG");

    /**
     * Validate if Mutant horizontal, vertical and oblique
     * @param dna entity with array dna to verified
     * @return true o false if is mutant
     */
    @Override
    public Boolean validateMutant(Dna dna) {
        if (Arrays.stream(dna.getDna()).anyMatch(s -> MUTANT_REGEX.matcher(s).find())) {
            return true;
        }
        if (Arrays.stream(getVertical(dna.getDna())).anyMatch(s -> MUTANT_REGEX.matcher(s).find())){
            return true;
        }
        if (Arrays.stream(getOblique(dna.getDna())).anyMatch(s -> MUTANT_REGEX.matcher(s).find())){
            return true;
        }
        return false;
    }

    /**
     * generate new array whit vertical letters
     * @param data array to transform
     * @return new array with vertical string
     */
    private String[] getVertical(String[] data) {
        String[] vertical = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            StringBuilder s = new StringBuilder();
            for (String d : data) {
                s.append(d.charAt(i));
            }
            vertical[i] = s.toString();
        }
        return vertical;
    }

    /**
     * gerate new array with oblique string with len more 3 letters
     * @param data arra to transform
     * @return new array with oblique string
     */
    private String[] getOblique(String[] data){
        var len = ((data.length * 2) - 7) * 2;
        String[] oblique = new String[len];
        int x= 0,y = data.length -4,as = 0;
        do {
            int ys = y, xs = x;
            var s = new StringBuilder();
            while (xs != data.length && ys != data.length) {
                s.append(data[ys++].charAt(xs++));
            }
            oblique[as++] = s.toString();
            if (y == 0) {x++;}
            if (x == 0 && y != 0) {y--;}
        } while (x != data.length - 3);
        x = data.length -4;
        y = data.length-1;
        do {
            int ys = y, xs = x;
            var s = new StringBuilder();
            while (xs != data.length && ys >= 0) {
                s.append(data[ys--].charAt(xs++));
            }
            oblique[as++] = s.toString();
            if (x == 0) {y--;}
            if (y == data.length - 1 && x != 0) {x--;}
        } while (y != 2);
        return oblique;
    }
}
