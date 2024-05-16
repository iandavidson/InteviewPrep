package software.davidson.ian.string.DecodeWays;

import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Solution {
    /*
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"

To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

    "AAJF" with the grouping (1 1 10 6)
    "KJF" with the grouping (11 10 6)

Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:

Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:

Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
     */

    public static void main(String[] arg) {
        Solution solution = new Solution();

        String s1 = "12";
        String s2 = "11106";
        String s3 = "1111111111111";
        log.info("{} yields: {}", s1, solution.numDecodings(s1));
        log.info("{} yields: {}", s2, solution.numDecodings(s2));
        log.info("{} yields: {}", s3, solution.numDecodings(s3));
    }


    public int numDecodings(String s) {
        return numDecodingsRec(s, new HashMap<String, Integer>());
    }

    private int numDecodingsRec(String s, Map<String, Integer> mem) {
        if (mem.containsKey(s)) {
            return mem.get(s);
        }


        int size2Count = 0;

        //s is empty -> return count;
        if (s.isEmpty()) {
            return 1;
        } else if (s.charAt(0) == '0') {
            return 0;
        } else if (s.length() >= 2) {
            //get both size 1 and 2
            String two = s.substring(0, 2);
            if (Integer.parseInt(two) <= 26) {
                size2Count = numDecodingsRec(s.substring(2), mem);
            }
        }


        int count = size2Count + numDecodingsRec(s.substring(1), mem);

        mem.put(s, count);

        return count;
    }
}
