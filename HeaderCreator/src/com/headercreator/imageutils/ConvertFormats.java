/**
 * ConvertFormats.java
 *
 * This software is free to use and redistribute.
 * 
 * @brief Converting pixel-video formats
 * @date 12:44:26 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com> 
 */

package com.headercreator.imageutils;

public class ConvertFormats {

    /**
     * Converting video format
     * @param pixels in RGB32 format
     * @return pixels in RGB565 format
     */
    public static int[] rgb32ToRgb565(int[] pixels) {
        
        int[] convertedPixels = new int[pixels.length];
        convertedPixels[0] = pixels[0];
        convertedPixels[1] = pixels[1];
        for (int i = 2; i < pixels.length; i++) {
            int bit = pixels[i];
            int r5 = (bit >> 16 & 0xFF) >> 3;
            int g6 = (bit >> 8 & 0xFF) >> 2;
            int b5 = (bit & 0xFF) >> 3;
            convertedPixels[i] = ((r5 & 0x1F) << 11 | (g6 & 0x3F) << 5 | b5 & 0x1F);
        }
        return convertedPixels;
    }
    
    /**
     * Converting video format
     * @param pixels in RGB32 format
     * @return pixels in RGB332 format
     */
    public int[] rgb32ToRgb332(int[] pixels) {
        
        int[] convertedPixels = new int[pixels.length];
        convertedPixels[0] = pixels[0];
        convertedPixels[1] = pixels[1];
        for (int i = 2; i < pixels.length; i++) {
            int bit = pixels[i];
            int r3 = (bit >> 16 & 0xFF) >> 5;
            int g3 = (bit >> 8 & 0xFF) >> 5;
            int b2 = (bit & 0xFF) >> 6;
            convertedPixels[i] = ((r3 & 0x7) << 5 | (g3 & 0x7) << 2 | b2 & 0x3);
        }
        return convertedPixels;
    }
}
