/**
 * FindSequence.java
 *
 * This software is free to use and redistribute.
 * 
 * @brief
 * @date 12:47:07 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com> 
 */

package com.headercreator.imageutils;

public class FindSequence {

    /**
     * 
     * @param pixels
     * @param offset
     * @return 
     */
    public static int[] findSequence(int[] pixels, int offset) {
        
        int color = pixels[offset];
        int minLen = 3;
        int[] result = new int[3];
        result[0] = offset;
        result[1] = -1;
        result[2] = -1;
        for (int i = offset + 1; i < pixels.length; i++) {
            if ((pixels[i] != color) && (i - result[0] > minLen)) {
                result[1] = (i - result[0]);
                result[2] = color;
                break;
            }
            if ((pixels[i] == color) || (i - result[0] > minLen)) {
                continue;
            }
            color = pixels[i];
            result[0] = i;
            result[1] = -1;
        }
        if (result[1] == -1) {
            return null;
        }
        return result;
    }
}
