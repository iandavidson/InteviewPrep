package software.davidson.ian.trie.wordSearch2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class WordSearch2 {


    public static void main(String[] args) {
        WordSearch2 wordSearch2 = new WordSearch2();

        char[][] board = new char[4][4];
        board[0] = new char[]{'o', 'a', 'a', 'n'};
        board[1] = new char[]{'e', 't', 'a', 'e'};
        board[2] = new char[]{'i', 'h', 'k', 'r'};
        board[3] = new char[]{'i', 'f', 'l', 'v'}; //["i","f","l","v"]
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        log.info("Answer1: {}", wordSearch2.findWords(board, words));

        /*
       [["a","b","c"]
        ["a","e","d"],
        ["a","f","g"]]

        ["abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"]
        i got   ["abcdefg","befa","ade","gfedcbaaa"]
        answer: ["abcdefg","befa","eaabcdgfa","gfedcbaaa"]
         */
    }

    private static final List<List<Integer>> SHIFT = List.of(List.of(1, 0), List.of(0, 1), List.of(-1, 0),
            List.of(0, -1));

    public List<String> findWords(char[][] board, String[] words) {
        List<String> validWords = new ArrayList<>();


        //try to remove values that we know cannot be found
        boolean [] count = new boolean[26];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                count[board[i][j] - 'a'] = true;
            }
        }

        for(String word : words){
            boolean valid = true;
            for(Character ch : word.toCharArray()){
                if(count[ch - 'a'] == false){
                    valid = false;
                    break;
                }
            }
            if(valid){
                validWords.add(word);
            }
        }

        // Create root
        Trie root = new Trie();

        // add all valid words to trie
        for(String word : validWords){
            root.add(word);
        }

        Set<String> foundWords = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for(String word: validWords){
                    if(foundWords.size() < validWords.size() && board[i][j] == word.charAt(0)){
                        dfs(root, String.valueOf(board[i][j]), foundWords, board, i, j, word);
                    }
                }
            }
        }

        return foundWords.stream().toList();
        // start from each cell and see if we get any hits, capture hits, return them
    }

    private void dfs(Trie trie, String current, Set<String> res, char[][] board, int r, int c, String searchWord) {
        // if (trie.isMember(current)) {
        //     res.add(current);
        //     // return;
        if(current.equals(searchWord)){
            res.add(current);
            return;
            // } else if (!trie.isValidPrefix(current)) {
        } else if (!searchWord.startsWith(current)) {
            return;
        }

        char ch = board[r][c];
        board[r][c] = '#';

        for (List<Integer> shift : SHIFT) {
            int newR = r + shift.get(0);
            int newC = c + shift.get(1);

            if (newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length) {

                String currentSub = current + board[newR][newC];
                if(trie.isValidPrefix(currentSub)){
                    dfs(trie, currentSub, res, board, newR, newC, searchWord);
                }
            }
        }

        board[r][c] = ch;
    }

    class Trie {
        public Map<Character, Trie> children;
        public boolean validWord;

        // init:
        public Trie() {
            this.validWord = false;
            this.children = new HashMap<Character, Trie>();
        }

        public void add(String input) {
            if (input.isEmpty()) {
                this.validWord = true;
                return;
            }

            if (!children.containsKey(input.charAt(0))) {
                Trie child = new Trie();
                children.put(input.charAt(0), child);
            }

            children.get(input.charAt(0)).add(input.substring(1));
        }

        public boolean isValidPrefix(String value) {
            if (value.isEmpty()) {
                return true;
            }

            if (children.containsKey(value.charAt(0))) {
                return children.get(value.charAt(0)).isValidPrefix(value.substring(1));
            }

            return false;
        }

        public boolean isMember(String value) {
            if (value.isEmpty()) {
                return validWord;
            }

            if (children.containsKey(value.charAt(0))) {
                return children.get(value.charAt(0)).isMember(value.substring(1));
            }

            return false;
        }
    }


}
