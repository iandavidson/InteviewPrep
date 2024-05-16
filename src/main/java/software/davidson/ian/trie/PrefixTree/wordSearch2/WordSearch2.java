package software.davidson.ian.trie.PrefixTree.wordSearch2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Slf4j
public class WordSearch2 {


    public static void main(String [] args){
        WordSearch2 wordSearch2 = new WordSearch2();

        char[][] board = new char[4][4];
        board[0] = new char[]{'o','a','a','n'};
        board[1] = new char[]{'e','t','a','e'};
        board[2] = new char[]{'i','h','k','r'};
        board[3] = new char[]{'i','f','l','v'}; //["i","f","l","v"]
        String [] words = new String[]{"oath","pea","eat","rain"};
        log.info("Answer1: {}", wordSearch2.findWords(board, words));
    }



    private static final List<List<Integer>> SHIFT = List.of(List.of(0, 1), List.of(0, 1), List.of(-1, 0),
            List.of(0, -1));

    public List<String> findWords(char[][] board, String[] words) {
        // Create root
        Trie root = new Trie();

        // add all words to trie
        for (String word : words) {
            root.add(word);
        }

        List<String> foundWords = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (String word : words) {
                    if (board[i][j] == word.charAt(0) && bfsFoundWord(root, word, board, i, j)) {
                        foundWords.add(word);
                    }
                }
            }
        }

        return foundWords;
        // start from each cell and see if we get any hits, capture hits, return them
    }

    private boolean bfsFoundWord(Trie trie, String word, char[][] board, int r, int c){
        boolean[][] vis = new boolean[board.length][board[0].length];

        Map<int[], String> coordMap = new HashMap<>();

        Queue<int []> queue = new LinkedList<>();

        //init start
        coordMap.put(new int[]{r,c}, String.valueOf(board[r][c]));
        vis[r][c] = true;
        queue.add(new int[]{r,c});


        while(!queue.isEmpty()){
            int [] current = queue.remove();
            int tempR = current[0];
            int tempC = current[1];


            String prefix = coordMap.get(new int[]{current[0],current[1]});

            //todo: fix this, it is checking via reference rather than content of entries 0,1
            if(trie.isMember(prefix)){
                return true;
            }

            for(List<Integer> shift : SHIFT){
                int newR = tempR + shift.get(0);
                int newC = tempC + shift.get(1);

                List<int []> neighbors = new ArrayList<>();
                if(newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length){
                    if(vis[newR][newC]){
                        continue;
                    }

                    String tempPrefix = prefix + board[newR][newC];

                    if(trie.isValidPrefix(tempPrefix)){
                        //only coninute to operate if prefix is true with respect to word we are searching for
                        coordMap.put(new int[]{newR, newC}, tempPrefix);
                        vis[newR][newC] = true;
                        queue.add(new int[]{newR, newC});
                    }
                }
            }


            /*
                how do I keep track of the substring we are at given the coordinate
                _> hash map {r,c} -> "subword"

            */
        }

        return false;
    }

    class Trie {
        public Map<Character, Trie> children;
        public boolean validWord;

        // init:
        public Trie() {
            this.validWord = false;
            this.children = new HashMap<Character, Trie>();
        }

        public void add(String input){
            if(input.isEmpty()){
                this.validWord = true;
                return;
            }

            if(!children.containsKey(input.charAt(0))){
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
