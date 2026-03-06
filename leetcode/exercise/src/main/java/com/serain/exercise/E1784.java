package main.java.com.serain.exercise;

public class E1784 {
    public boolean checkOnesSegment(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                while (i<s.length() && s.charAt(i) == '1') {
                    i++;
                }
                count++;
                if (count > 1) return false;
            }
        }
        return true;
    }
}
