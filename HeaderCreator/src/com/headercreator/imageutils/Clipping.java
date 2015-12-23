/**
 * Clipping.java
 *
 * This software is free to use and distribute.
 * 
 * @brief Clip image operation
 * @date 12:52:06 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com>
 */

package com.headercreator.imageutils;

public class Clipping {

    /**
     * Clip image
     * @param pixels converted pixels
     * @param maxWidth of image
     * @param maxHeight of image
     * @return hex map presentation of image
     */
    public static int[] clip(int[] pixels, int maxWidth, int maxHeight) {
       
        int[] result;
        int pos;
        int width = pixels[0];
        int height = pixels[1];
        if (maxWidth < width) {
            width = maxWidth;
        }
        if (maxHeight < height) {
            height = maxHeight;
        }
        result = new int[width * height + 2];
        result[0] = width;
        result[1] = height;
        pos = 2;
        for (int i = 2; i < pixels.length; i += pixels[0]) {
            System.arraycopy(pixels, i, result, pos, width);
            pos += width;
            if ((pos - 2) / width >= height) {
                break;
            }
        }
        return result;
    }
}
