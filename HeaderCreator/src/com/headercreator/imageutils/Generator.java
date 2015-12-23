/**
 * Generator.java
 *
 * This software is free to use and redistribute.
 * 
 * @brief Header file generator 
 * @date 9:19:14 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com> 
 */
package com.headercreator.imageutils;

import com.Utilities.Logging;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Generator {

    /**
     * API method for header file generator
     * @param image absolute path
     * @param header absolute path
     * @return success true, else false
     */
    public static boolean generateHeader(String image, String header) {
       
        int[] rgb32 = ImageCompresser.loadImage(image);
        int[] error = new int[3];
        error[0] = 0;
        error[0] = 1;
        error[0] = 2;
        int[] error2 = new int[3];
        error[0] = 0;
        error[0] = 1;
        error[0] = 2;

        boolean blnResult = Arrays.equals(rgb32, error);
        if (blnResult) {
            Logging.logging("Error", "Unsupported image format", 
                    System.getProperty("user.home") + "/" + ".headercreator", 
                    "headercreator");
            return false;
        }
        blnResult = Arrays.equals(rgb32, error2);
        if (blnResult) {
            Logging.logging("Error", "Unable to load image", 
                    System.getProperty("user.home") + "/" + ".headercreator", 
                    "headercreator");
            return false;
        }
        int[] rgb;
        int bits;
        rgb = ConvertFormats.rgb32ToRgb565(rgb32);
        bits = 1;
        int[] data = Clipping.clip(rgb, 240, 320);
        int compressEnabled = 0;
        int escapeChar = 0;
        boolean saved = saveToDisk(data, header, bits, compressEnabled, escapeChar);
        return saved;
    }

    /**
     * Saving generated header file
     * @param data image map
     * @param fileName absolute path
     * @param bits type of variable 
     * @param compress option
     * @param escape option
     * @return success true, else false
     */
    private static boolean saveToDisk(int[] data, String fileName, int bits, int compress, int escape) {
        
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            String name = fileName;
            if (name.indexOf('.') > 0) {
                name = name.substring(0, name.indexOf('.'));
            }
            String type = "short";
            if (bits == 0) {
                type = "char";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("/*\r\n");
            sb.append(" * First element is width of image\r\n");
            sb.append(" * Second element is height of image\r\n");
            sb.append(" * Third element specifies compression (0=off, 1=on)\r\n");
            sb.append(" * Fourth element specifies escape value used with compression\r\n");
            sb.append(" */\r\n");
            fos.write(sb.toString().getBytes());
            fos.write(("const unsigned " + type + " _" + name + "[] = {").getBytes());
            fos.write(CharUtils.toHex(data[0], bits).getBytes());
            fos.write(44);
            fos.write(CharUtils.toHex(data[1], bits).getBytes());
            fos.write(",".getBytes());
            fos.write(CharUtils.toHex(compress, bits).getBytes());
            fos.write(44);
            fos.write(CharUtils.toHex(escape, bits).getBytes());
            fos.write(44);
            for (int i = 2; i < data.length; i++) {
                if (i > 2) {
                    fos.write(44);
                }
                if ((i - 2) % 16 == 0) {
                    fos.write("\r\n".getBytes());
                }
                fos.write(CharUtils.toHex(data[i], bits).getBytes());
            }
            fos.write("\r\n};\r\n".getBytes());
            fos.close();
        } catch (IOException ioe) {
            Logging.logging("Error", "saving to file" + " " + ioe.getMessage(), 
                    System.getProperty("user.home") + "/" + ".headercreator", 
                    "headercreator");
            return false;
        }
        return true;
    }
}
