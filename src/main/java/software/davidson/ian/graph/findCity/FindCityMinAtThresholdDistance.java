package software.davidson.ian.graph.findCity;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindCityMinAtThresholdDistance {

    public static void main(String[] args) {
        FindCityMinAtThresholdDistance findCity = new FindCityMinAtThresholdDistance();

        int[][] edges = new int[][]{new int[]{0, 1, 3}, new int[]{1, 2, 1}, new int[]{1, 3, 4}, new int[]{2, 3, 1}};
        int n = 4;
        int distance = 4;
//        log.info("{}", findCity.findTheCity(n, edges, distance));

        edges = new int[][]{new int[]{0, 1, 2}, new int[]{0, 4, 8}, new int[]{1, 2, 3}, new int[]{1, 4, 2},
                new int[]{2, 3, 1}, new int[]{3, 4, 1}};
        n = 5;
        distance = 2;
//        log.info("{}", findCity.findTheCity(n, edges, distance));

        edges = new int[][]{new int[]{0, 3, 7}, new int[]{2, 4, 1}, new int[]{0, 1, 5}, new int[]{2, 3, 10},
                new int[]{1, 3, 6}, new int[]{1, 2, 1}};
        n = 6;
        distance = 417;
        log.info("{}", findCity.findTheCity(n, edges, distance));
    }


    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        //to[0] -> from[1] -> distance[2]
        //from[1] -> to[0] -> distance[2]
        for (int[] edge : edges) {
            if (!adj.containsKey(edge[0])) {
                adj.put(edge[0], new HashMap<>());
            }
            adj.get(edge[0]).put(edge[1], edge[2]);

            if (!adj.containsKey(edge[1])) {
                adj.put(edge[1], new HashMap<>());
            }
            adj.get(edge[1]).put(edge[0], edge[2]);
        }

        // least # of cities reachable via "distance threathold"
        int res = -1;
        int minCitiesFound = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int tempFound = dijkstras(distanceThreshold, adj, i);

            if (tempFound <= minCitiesFound) {
                if (i > res) {
                    res = i;
                    minCitiesFound = tempFound;
                }
            }
        }

        return res;
    }


    private int dijkstras(int remaining, Map<Integer, Map<Integer, Integer>> adj, int start) {
        Map<Integer, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 0);

        Queue<Integer> unsettled = new LinkedList<>();
        unsettled.add(start);

        while (!unsettled.isEmpty()) {
            int current = unsettled.remove();
            log.info("current: {}", current);

            for (Integer neighborKey : adj.getOrDefault(current, Collections.emptyMap()).keySet()) {
                int traveled = distanceMap.get(current) + adj.get(current).get(neighborKey);
                //start at 0, a->b is 5, so traveled == 5;

                if (traveled >= distanceMap.getOrDefault(neighborKey, Integer.MAX_VALUE)) {
                    //been here before, at same cost or worse, don't add back to queue, skip iteration
                    continue;
                } else {
                    //better cost to arrive? update entry
                    distanceMap.put(neighborKey, Math.min(distanceMap.getOrDefault(neighborKey, Integer.MAX_VALUE),
                            traveled));

                    //since we are doing better, we can propogate lower cost to get to this ones neighbors, add/re-add
                    unsettled.add(neighborKey);
                }

            }


        }

        int count = 0;
        for (int key : distanceMap.keySet()) {
            if (distanceMap.get(key) <= remaining) {
                count++;
            }
        }

        return count - 1;
    }

}
