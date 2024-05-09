package software.davidson.ian.EncodeDecodeStrings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
/*
Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.

Please implement encode and decode

Example 1:

Input: ["neet","code","love","you"]
Output:["neet","code","love","you"]

Example 2:

Input: ["we","say",":","yes"]
Output: ["we","say",":","yes"]


Constraints:
0 <= strs.length < 100
0 <= strs[i].length < 200
strs[i] contains only UTF-8 characters.
 */


    private final String delim = "\t$";

    public String encode(List<String> strs) {
        if(strs.isEmpty()){
            return null;
        }

        return String.join(delim, strs);
    }

    public List<String> decode(String str) {
        if(str == null){
            return Collections.emptyList();
        }

        return Arrays.asList(str.split("\\\t\\$"));
    }
}
