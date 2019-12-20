package com.example.demospringboot.leenotes;

import java.util.BitSet;

/**
 * @author tianyalei
 */
public class FindNumber {
    public int FirstNotRepeatingChar2(String str) {
        BitSet bs1 = new BitSet(256);
        BitSet bs2 = new BitSet(256);
        for (char c : str.toCharArray()) {
            if (!bs1.get(c) && !bs2.get(c)) {
                bs1.set(c);     // 0 0 -> 0 1
            } else if (bs1.get(c) && !bs2.get(c)) {
                bs2.set(c);     // 0 1 -> 1 1
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c))  // 0 1
            {
                return i;
            }
        }
        return -1;
    }
}
