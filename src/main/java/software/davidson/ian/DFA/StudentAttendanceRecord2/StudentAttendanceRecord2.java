package software.davidson.ian.DFA.StudentAttendanceRecord2;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class StudentAttendanceRecord2 {

    public static void main(String[] args) {
        StudentAttendanceRecord2 studentAttendanceRecord2 = new StudentAttendanceRecord2();
        log.info("answer: {}", studentAttendanceRecord2.checkRecord(2));
    }


    public int checkRecord(int n) {
        List<Character> inputLanguage = List.of('A', 'L', 'P');
        Set<Integer> garbageStates = Set.of(2, 5, 9);

        // just realized I can combine all garbage states

        Map<Integer, Map<Character, Integer>> transitions = new HashMap<>();
        transitions.put(0, Map.of('A', 1, 'L', 7, 'P', 10));
        transitions.put(1, Map.of('A', 2, 'L', 3, 'P', 6));
        transitions.put(2, Map.of('A', 2, 'L', 2, 'P', 2));
        transitions.put(3, Map.of('A', 2, 'L', 4, 'P', 6));
        transitions.put(4, Map.of('A', 2, 'L', 5, 'P', 6));
        transitions.put(5, Map.of('A', 5, 'L', 5, 'P', 5));
        transitions.put(6, Map.of('A', 2, 'L', 3, 'P', 6));
        transitions.put(7, Map.of('A', 1, 'L', 8, 'P', 10));
        transitions.put(8, Map.of('A', 1, 'L', 9, 'P', 10));
        transitions.put(9, Map.of('A', 9, 'L', 9, 'P', 9));
        transitions.put(10, Map.of('A', 1, 'L', 7, 'P', 10));

        Map<Integer, Long> current = new HashMap<>();
        current.put(0, 1L);
        current.put(1, 0L);
        current.put(2, 0L);
        current.put(3, 0L);
        current.put(4, 0L);
        current.put(5, 0L);
        current.put(6, 0L);
        current.put(7, 0L);
        current.put(8, 0L);
        current.put(9, 0L);
        current.put(10, 0L);

        for (int i = 0; i < n; i++) {
            Map<Integer, Long> next = new HashMap<>();
            for (int state : transitions.keySet()) {
                for (Character input : inputLanguage) {
                    // -> where we will go from current state to next given input character
                    // transitions.get(state).get(character);
                    Integer nextState = transitions.get(state).get(input);
                    Long nextStateCount = next.getOrDefault(nextState, 0L);
                    next.put(nextState, nextStateCount + current.getOrDefault(state,0L));
                }
            }
            current = next;
        }

        long counter = 0L;
        for (int state : current.keySet()) {
            if(!garbageStates.contains(state)){
                counter += current.get(state);
            }
        }

        return (int) (counter % 1_000_000_007);
    }
}
