package software.davidson.ian.string.PercentLetterOfString;

import lombok.extern.slf4j.Slf4j;
import software.davidson.ian.backtracking.permutations.Permutations;

@Slf4j
public class PercentLetterOfString {

    public static void main(String [] args){
        PercentLetterOfString percentLetterOfString = new PercentLetterOfString();
        log.info("answer: {}", percentLetterOfString.percentageLetter("foobar", 'o'));
    }

    public int percentageLetter(String s, char letter) {
        double letterCount = 0;
        for(Character c : s.toCharArray()){
            if(c == letter){
                letterCount++;
            }
        }

        Double proportion = (letterCount / s.length());
        proportion *= 100;
        return proportion.intValue();
    }
}
