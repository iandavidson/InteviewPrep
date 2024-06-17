package software.davidson.ian.trie;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MinExtraChar {

    public static void main(String[] args) {
        MinExtraChar minExtraChar = new MinExtraChar();
//        System.out.println(minExtraChar.minExtraChar("leetscode", new String[]{"leet","code","leetcode"}));
//        System.out.println(minExtraChar.minExtraChar("leetsscode", new String[]{"leet", "leets","code","leetcode"}));
//        System.out.println(minExtraChar.minExtraChar("ecolloycollotkvzqpdaumuqgs", new String[]{"flbri","uaaz","numy","laper","ioqyt","tkvz","ndjb","gmg","gdpbo","x","collo","vuh","qhozp","iwk","paqgn","m","mhx","jgren","qqshd","qr","qpdau","oeeuq","c","qkot","uxqvx","lhgid","vchsk","drqx","keaua","yaru","mla","shz","lby","vdxlv","xyai","lxtgl","inz","brhi","iukt","f","lbjou","vb","sz","ilkra","izwk","muqgs","gom","je"}));
        System.out.println(minExtraChar.minExtraChar("abcdef", new String[]{"abc", "a", "cdef"}));
    }


    public int minExtraChar(String s, String[] dictionary) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.add(word);
        }


        return dpHelper(s, 0, s.length(), trie, new Integer[s.length()]);
    }

    private int dpHelper(String s, int current, int n, Trie root, Integer[] dp) {
        if (current == n) {
            // no more character to consume, return 0
            return 0;
        }

        if (dp[current] != null) {
            return dp[current];
        }

        //recusively call with blindly skipping current index
        int answer = 1 + dpHelper(s, current + 1, n, root, dp);
        for (int i = current; i < n; i++) {
            String sub = s.substring(current, i + 1);
            if (root.isPrefix(sub)) {
                if (root.isWord(sub)) {
                    answer = Math.min(answer, dpHelper(s, i + 1, n, root, dp));
                }
            } else {
                break;
            }
        }


        dp[current] = answer;
        return answer;
    }

    public static class Trie {
        Map<Character, Trie> map;
        boolean isWord;

        public Trie() {
            this.map = new HashMap<>();
            this.isWord = false;
        }

        public void add(String word) {
            if (word.isEmpty()) {
                this.isWord = true;
                return;
            }

            Character first = word.charAt(0);
            if (map.containsKey(first)) {
                map.get(first).add(word.substring(1));
            } else {
                Trie trie = new Trie();
                map.put(first, trie);
                trie.add(word.substring(1));
            }
        }

        public boolean isPrefix(String word) {
            if (word.isEmpty()) {
                return true;
            }

            Character first = word.charAt(0);
            if (map.containsKey(first)) {
                return map.get(first).isPrefix(word.substring(1));
            } else {
                return false;
            }
        }

        public boolean isWord(String word) {
            if (word.isEmpty()) {
                return this.isWord;
            }

            Character first = word.charAt(0);
            if (map.containsKey(first)) {
                return map.get(first).isWord(word.substring(1));
            } else {
                return false;
            }
        }
    }
}
