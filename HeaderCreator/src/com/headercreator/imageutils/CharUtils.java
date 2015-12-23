/**
 * CharUtils.java
 *
 * This software is free to use and redistribute.
 * 
 * @brief Character utilities
 * @date 12:48:35 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com> 
 */

package com.headercreator.imageutils;

import com.Utilities.Logging;
import java.util.Hashtable;

public class CharUtils {

    /**
     * Finding 'ESC' character
     * @param pixels
     * @param offset
     * @param bits
     * @return 
     */
    public static int findEscapeChar(int[] pixels, int offset, int bits) {
        
        Hashtable<Integer, Integer> h = new Hashtable<Integer, Integer>();
        int maxValue = 255;
        if (bits == 1) {
            maxValue = 65535;
        }
        for (int i = offset; i < pixels.length; i++) {
            Integer key = new Integer(pixels[i]);
            Integer num = (Integer) h.get(key);

            if (num != null) {
                h.put(key, new Integer(num.intValue() + 1));
            } else {
                h.put(key, new Integer(1));
            }
        }
        for (int i = 0; i < maxValue; i++) {
            if (h.get(new Integer(i)) == null) {
                return i;
            }
        }
        Logging.logging("Warning", "unique escape not used (finding least used not implemented)", 
                    System.getProperty("user.home") + "/" + ".headercreator", 
                    "headercreator");
        return 234;
    }

    /**
     * Conversion pixel to HEX value
     * @param value pixel value
     * @param bits 
     * @return HEX presentation of pixel 
     */
    public static String toHex(int value, int bits) {
        
        String hex = Integer.toHexString(value);
        int padLen = 4 - hex.length();
        if (bits == 0) {
            padLen = 2 - hex.length();
        }
        for (int i = 0; i < padLen; i++) {
            hex = "0" + hex;
        }
        hex = "0x" + hex;
        return hex;
    }
}
