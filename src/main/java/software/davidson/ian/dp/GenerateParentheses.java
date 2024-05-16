package software.davidson.ian.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();

        recurse("(", results, n -1, n);

        return results;
    }

    private void recurse(String current, List<String> list, int lRem, int rRem){
        if(lRem == 0 && rRem == 0){
            list.add(current);
        }

        //(( if lRem < rRem -> both ( & ) allowed
        //() if lRem == rRem -> only ( allowed

        if(lRem > 0){
            recurse(current + "(", list, lRem-1, rRem);
        }

        if(lRem < rRem){
            recurse(current + ")", list, lRem, rRem-1);
        }
    }
}
