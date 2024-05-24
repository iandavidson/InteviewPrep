package software.davidson.ian.backtracking.MaxWordScore;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MaximumWordScore {

    /*
    1255. Maximum Score Words Formed by Letters -- Hard

Given a list of words, list of  single letters (might be repeating) and score of every character.

Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).

It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.



Example 1:

Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
Output: 23
Explanation:
Score  a=1, c=9, d=5, g=3, o=2
Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
Words "dad" and "dog" only get a score of 21.

Example 2:

Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
Output: 27
Explanation:
Score  a=4, b=4, c=4, x=5, z=10
Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
Word "xxxz" only get a score of 25.

Example 3:

Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
Output: 0
Explanation:
Letter "e" can only be used once.



Constraints:

    1 <= words.length <= 14
    1 <= words[i].length <= 15
    1 <= letters.length <= 100
    letters[i].length == 1
    score.length == 26
    0 <= score[i] <= 10
    words[i], letters[i] contains only lower case English letters.


        Map<[remaining chars], List<Words(can be made given remaining)>>


     */

    public static void main(String [] args){
        MaximumWordScore maximumWordScore = new MaximumWordScore();
        String [] words = new String[]{"azb","ax","awb","ayb","bpppp"};
        char [] letters = new char[]{'z','a','w','x','y','b','p','p','p'};
        int [] score = new int[]{10,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,3,2,3,3};
        log.info("best score: {}", maximumWordScore.maxScoreWords(words, letters, score));
    }

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        Map<String, Integer> wordScoreMap = toWordScoreMap(score, words);
        Map<Character, Integer> remainingLetters = new HashMap<>();
        for(char letter : letters){
            remainingLetters.put(letter, remainingLetters.getOrDefault(letter, 0) + 1);
        }


        return recurse(wordScoreMap, remainingLetters, words, 0, new ArrayList<>());
    }

    private int recurse(Map<String, Integer> wordScoreMap, Map<Character, Integer> remainingLetters, String [] words, Integer wIndex, List<String> current){
        if(wIndex >= words.length){
            return countWordsScore(current, wordScoreMap);
        }

        int maxScore = 0;
        for(int i = wIndex; i < words.length; i++){
            //determine if word[i] can be constructed with remainingLetters
            String currentWord = words[i];
//            char [] currentWordCount = new char[26];
            Map<Character, Integer> remLettersCopy = new HashMap<>(remainingLetters);

            boolean acceptable = true;
            for(Character ch : currentWord.toCharArray()){
                remLettersCopy.put(ch, remLettersCopy.getOrDefault(ch,0) - 1);

                if(remLettersCopy.get(ch) < 0){
                    acceptable = false;
                    break;
                }
            }

            if(!acceptable){
                continue;
            }
            List<String> currentCopy = new ArrayList<>(current);
            currentCopy.add(words[i]);

            maxScore = Math.max(maxScore, recurse(
               wordScoreMap, remLettersCopy, words, i+1, currentCopy
            ));
        }

        return Math.max(maxScore, countWordsScore(current, wordScoreMap));

    }

    private Map<String, Integer> toWordScoreMap(int [] scores, String[] words){
        Map<String, Integer> wordScoreMap = new HashMap<>();

        for(String word: words){
            int wordScore = 0;
            for(Character ch : word.toCharArray()){
                wordScore += scores[ch - 'a'];
            }
            wordScoreMap.put(word, wordScore);
        }

        return wordScoreMap;
    }

    private int countWordsScore(List<String> words, Map<String, Integer> wordScoreMap){
        int score = 0;
        for(String word : words){
            score += wordScoreMap.get(word);
        }

        return score;
    }
}
