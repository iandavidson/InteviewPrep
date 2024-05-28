package software.davidson.ian.string.equalSubstringsWithBudget;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EqualSubstringsWithBudget {

    public static void main(String[] args) {
        EqualSubstringsWithBudget equalSubstringsWithBudget = new EqualSubstringsWithBudget();
        log.info("Answer: {}", equalSubstringsWithBudget.equalSubstring("abcd", "bcdf", 3));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        int n = t.length();
        int maxFound = 0;
        int left = 0;
        int current = 0;
        for (int right = 0; right < n; right++) {
            current += Math.abs(s.charAt(right) - t.charAt(right));

            while (left < right && current > maxCost) {
                current -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            maxFound = Math.max(maxFound, right - left + 1);
        }

        return maxFound;
    }
}
