/**
 * ImageCompresser.java
 *
 * This software is free to use and redistribute.
 * 
 * @brief Image Compressor
 * @date 9:52:12 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com>
 */
package com.headercreator.imageutils;

import java.awt.Button;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

public class ImageCompresser {
    
    public static final int BITS_PER_PIXEL_8 = 0;
    public static final int BITS_PER_PIXEL_16 = 1;

    /**
     * Loading image from file
     * @param filePath absolute path
     * @return bitmap of image
     */
    public static int[] loadImage(String filePath) {
        Image img = null;
        int[] bitmap = (int[]) null;
        try {
            if ((filePath.endsWith(".gif")) || (filePath.endsWith(".jpg"))
                    || (filePath.endsWith(".jpeg")) || (filePath.endsWith(".png"))
                    || (filePath.endsWith(".GIF")) || (filePath.endsWith(".JPG"))
                    || (filePath.endsWith(".JPEG")) || (filePath.endsWith(".PNG"))) {
                img = Toolkit.getDefaultToolkit().getImage(filePath);
            } else if (filePath.endsWith(".bmp")) {
                img = LoadBitmap.loadbitmap(filePath);
            }
            if (img == null) {
                int[] error = new int[3];
                error[0] = 0;
                error[0] = 1;
                error[0] = 2;
                return error;
            }
            Button b = new Button();
            MediaTracker tracker = new MediaTracker(b);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
            int width = img.getWidth(null);
            int height = img.getHeight(null);
            if ((width == -1) || (height == -1)) {
                int[] error = new int[3];
                error[0] = 0;
                error[0] = 1;
                error[0] = 2;
                return error;
            }
            bitmap = new int[width * height + 2];
            PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, bitmap, 2, width);
            try {
                pg.grabPixels();
            } catch (InterruptedException e) {

            }
            bitmap[0] = width;
            bitmap[1] = height;
        } catch (InterruptedException e) {
            
        }
        return bitmap;
    }

    /**
     * Compress image map
     * @param pixels image to compress
     * @param bits
     * @return compressed image map
     */
    public static int[] compress(int[] pixels, int bits) {
        int[] seq;
        int[] tmp = new int[pixels.length];
        int lastOffset = 0;
        int pos = 0;
        int[] newPixels = (int[]) null;
        int maxLen = 255;
        if (bits == 1) {
            maxLen = 65535;
        }
        int escape = CharUtils.findEscapeChar(pixels, 2, bits);
        for (int i = 2; i < pixels.length; i++) {
            seq = FindSequence.findSequence(pixels, i);
            if (seq == null) {
                continue;
            }
            if (seq[0] > lastOffset) {
                System.arraycopy(pixels, lastOffset, tmp, pos, seq[0] - lastOffset);
                pos += seq[0] - lastOffset;
            }
            int num = seq[1];
            if (num > maxLen) {
                num = maxLen;
            }
            tmp[(pos++)] = escape;
            tmp[(pos++)] = num;
            tmp[(pos++)] = seq[2];
            lastOffset = seq[0] + num;
            i = seq[0] + num;
        }
        if (lastOffset < pixels.length) {
            System.arraycopy(pixels, lastOffset, tmp, pos, pixels.length - lastOffset);
            pos += pixels.length - lastOffset;
        }
        if (pos > 0) {
            newPixels = new int[pos];
            System.arraycopy(tmp, 0, newPixels, 0, pos);
        }
        return newPixels;
    }
}
