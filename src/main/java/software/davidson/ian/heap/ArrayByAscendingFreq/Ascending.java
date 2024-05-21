package software.davidson.ian.heap.ArrayByAscendingFreq;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class Ascending {
    public static void main(String[] args) {
        Ascending ascending = new Ascending();
        log.info("answer: {}", ascending.frequencySort(new int[]{1, 1, 1, 2, 2, 3}));
    }

    public int[] frequencySort(int[] nums) {
        int[] res = new int[nums.length];

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        heap.addAll(map.keySet());

        int currentIndex = 0;
        while (!heap.isEmpty()) {
            int num = heap.remove();
            for (int i = 0; i < map.get(num); i++) {
                res[currentIndex] = num;
                currentIndex++;
            }
        }

        return res;
    }
}
