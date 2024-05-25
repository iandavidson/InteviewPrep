package software.davidson.ian.trie.PrefixTree.WordBreak2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class WordBreak2 {

    public static void main(String [] args){
        WordBreak2 wordBreak2 = new WordBreak2();
        String s = "catsanddog";
        List<String> wordDict = List.of("cat","cats","and","sand","dog");
        log.info("Answer: {}", wordBreak2.wordBreak(s, wordDict));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Trie root = new Trie();

        for(String word: wordDict){
            root.add(word);
        }

        List<String> res = new ArrayList<>();

        recurse(res, root, s, "", "", 0);
        return res;
    }

    // can probably optmize by traversing trie, but we would need to pass both current Trie state + Trie root
    private void recurse(List<String> result, Trie root, String input, String currentLine, String currentWord, int index){
        if(index >= input.length()){

            if(currentWord.isEmpty()){
                result.add(currentLine.trim());
            }
            return;
        }

        //add letter at index to current word
        currentWord = currentWord + input.charAt(index);

        if(root.isWord(currentWord)){
            String tempLine = currentLine + currentWord + " ";
            String tempWord = "";
            recurse(result, root, input, tempLine, tempWord, index+1);

        }

        if(root.isPrefix(currentWord)){
            recurse(result, root, input, currentLine, currentWord, index+1);
        }
    }

    static class Trie{
        Map<Character, Trie> map;
        boolean isWord;

        public Trie(){
            this.map = new HashMap<>();
            this.isWord = false;
        }

        public void add(String word){
            if(word.isEmpty()){
                this.isWord = true;
                return;
            }

            if(this.map.containsKey(word.charAt(0))){
                map.get(word.charAt(0)).add(word.substring(1));
            }else{
                map.put(word.charAt(0), new Trie());
                map.get(word.charAt(0)).add(word.substring(1));
            }
        }

        public boolean isPrefix(String word){
            if(word.isEmpty()){
                return true;
            }

            if(this.map.containsKey(word.charAt(0))){
                return map.get(word.charAt(0)).isPrefix(word.substring(1));
            }else{
                return false;
            }
        }

        public boolean isWord(String word){
            if(word.isEmpty()){
                return this.isWord;
            }

            if(this.map.containsKey(word.charAt(0))){
                return map.get(word.charAt(0)).isWord(word.substring(1));
            }else{
                return false;
            }
        }
    }


}
