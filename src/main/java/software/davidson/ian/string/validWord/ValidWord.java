package software.davidson.ian.string.validWord;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class ValidWord {

    public static void main(String [] args){
        ValidWord validWord =  new ValidWord();
        log.info("answer: {}", validWord.isValid("3pp"));
    }

    public boolean isValid(String word) {
        Set<Character> vowels = Set.of('a','e','i','o','u');

        if(word.length() < 3){
            return false;
        }

        boolean vowelFound = false;
        boolean consonantFound = false;

        for(Character ch : word.toCharArray()){
            if(Character.isAlphabetic(ch)){
                System.out.println("current: " + ch);
                if(vowels.contains(Character.toLowerCase(ch))){
                    vowelFound = true;
                }else{
                    consonantFound = true;
                }
            }else if(Character.isDigit(ch)){

            }else{
                return false;
            }
        }

        return vowelFound && consonantFound;
    }
}
