package software.davidson.ian.string.replaceWord;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ReplaceWords {

    public static void main(String [] args){
        List<String> words = List.of("cat","bat","rat");
        String sent = "the cattle was rattled by the battery";
        ReplaceWords replaceWords = new ReplaceWords();
        log.info(replaceWords.replaceWords(words, sent));
    }


    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        for (String item : dictionary) {
            root.addWord(item);
        }

        StringBuilder sb = new StringBuilder();
        String[] sentenceChunks = sentence.split("\\s+");

        for (String word : sentenceChunks) {
            String tempWord = word;
            for (int i = 0; i < word.length(); i++) {

                if (root.isWord(word.substring(0, i + 1))) {
                    tempWord = word.substring(0, i + 1);
                    break;
                }else if(!root.isPrefix(word.substring(0, i + 1))){
                    break;
                }
            }

            sb.append(tempWord).append(" ");
        }

        return sb.toString().trim();

    }

    static class Trie {
        Map<Character, Trie> map;
        boolean isWord;

        public Trie() {
            this.map = new HashMap<>();
            this.isWord = false;
        }

        public void addWord(String word) {
            if (word.isEmpty()) {
                this.isWord = true;
                return;
            }

            if (map.containsKey(word.charAt(0))) {
                map.get(word.charAt(0)).addWord(word.substring(1));
            } else {
                Trie trie = new Trie();
                map.put(word.charAt(0), trie);
                trie.addWord(word.substring(1));
            }
        }

        public boolean isWord(String word) {
            if (word.isEmpty()) {
                return this.isWord;
            }

            if (map.containsKey(word.charAt(0))) {
                return map.get(word.charAt(0)).isWord(word.substring(1));
            }
            return false;
        }

        public boolean isPrefix(String word) {
            if (word.isEmpty()) {
                return true;
            }

            if (map.containsKey(word.charAt(0))) {
                return map.get(word.charAt(0)).isPrefix(word.substring(1));
            }

            return false;
        }
    }
}
