package software.davidson.ian.string.longPressedName;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LongPressedName {

    public static void main(String[] args) {
        LongPressedName longPressedName = new LongPressedName();
        String n = "alex";
        String t = "aaleex";
        log.info("n: {}, t: {}; answer: {}", n, t, longPressedName.isLongPressedName(n, t));
    }

    public boolean isLongPressedName(String name, String typed) {

        int nIdx = 0;
        int tIdx = 0;
        while (tIdx < typed.length()) {
            if (nIdx < name.length() && name.charAt(nIdx) == typed.charAt(tIdx)) {
                nIdx++;
                tIdx++;
            } else if (tIdx > 0 && typed.charAt(tIdx) == typed.charAt(tIdx - 1)) {
                tIdx++;
            } else {
                return false;
            }
        }
        return true;
    }

}
