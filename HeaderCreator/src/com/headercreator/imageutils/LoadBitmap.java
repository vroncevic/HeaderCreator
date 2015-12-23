/**
 * LoadBitmap.java
 *
 * This software is free to use and redistribute.
 * 
 * @brief Loading bitmap
 * @date 12:41:17 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com> 
 */

package com.headercreator.imageutils;

import com.Utilities.Logging;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.io.FileInputStream;

public class LoadBitmap {

    /**
     * Loading bitmap from file
     * @param path absolute path
     * @return Image class
     */
    public static Image loadbitmap(String path) {
        
        FileInputStream fs;
        try {
            fs = new FileInputStream(path);
            int bflen = 14;
            byte[] bf = new byte[bflen];
            fs.read(bf, 0, bflen);
            int bilen = 40;
            byte[] bi = new byte[bilen];
            fs.read(bi, 0, bilen);
            int nwidth = (bi[7] & 0xFF) << 24
                    | (bi[6] & 0xFF) << 16
                    | (bi[5] & 0xFF) << 8
                    | bi[4] & 0xFF;
            int nheight = (bi[11] & 0xFF) << 24
                    | (bi[10] & 0xFF) << 16
                    | (bi[9] & 0xFF) << 8
                    | bi[8] & 0xFF;
            int nbitcount = (bi[15] & 0xFF) << 8 | bi[14] & 0xFF;
            int nsizeimage = (bi[23] & 0xFF) << 24
                    | (bi[22] & 0xFF) << 16
                    | (bi[21] & 0xFF) << 8
                    | bi[20] & 0xFF;
            int nclrused = (bi[35] & 0xFF) << 24
                    | (bi[34] & 0xFF) << 16
                    | (bi[33] & 0xFF) << 8
                    | bi[32] & 0xFF;
            Image image;
            switch (nbitcount) {
                case 24:
                        int npad = nsizeimage / nheight - nwidth * 3;
                        if (npad == 4) {
                            npad = 0;
                        }   
                        int[] ndata = new int[nheight * nwidth];
                        byte[] brgb = new byte[(nwidth + npad) * 3 * nheight];
                        fs.read(brgb, 0, (nwidth + npad) * 3 * nheight);
                        int nindex = 0;
                        for (int j = 0; j < nheight; j++) {
                            for (int i = 0; i < nwidth; i++) {
                                ndata[(nwidth * (nheight - j - 1) + i)] = 
                                        (0xFF000000
                                        | (brgb[(nindex + 2)] & 0xFF) << 16
                                        | (brgb[(nindex + 1)] & 0xFF) << 8
                                        | brgb[nindex] & 0xFF);
                                nindex += 3;
                            }
                            nindex += npad;
                        }   image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(nwidth, nheight, ndata, 0, nwidth));
                        break;
                case 8:
                        {
                            int nNumColors;
                            if (nclrused > 0) {
                                nNumColors = nclrused;
                            } else {
                                nNumColors = 1 << nbitcount;
                            }       
                            if (nsizeimage == 0) {
                                nsizeimage = (nwidth * nbitcount + 31 & 0xFFFFFFE0) >> 3;
                                nsizeimage *= nheight;
                            }       int[] npalette = new int[nNumColors];
                            byte[] bpalette = new byte[nNumColors * 4];
                            fs.read(bpalette, 0, nNumColors * 4);
                            int nindex8 = 0;
                            for (int n = 0; n < nNumColors; n++) {
                                npalette[n]
                                        = (0xFF000000 | (bpalette[(nindex8 + 2)] & 0xFF) << 16
                                        | (bpalette[(nindex8 + 1)] & 0xFF) << 8
                                        | bpalette[nindex8] & 0xFF);
                                nindex8 += 4;
                            }       int npad8 = nsizeimage / nheight - nwidth;
                            int[] ndata8 = new int[nwidth * nheight];
                            byte[] bdata = new byte[(nwidth + npad8) * nheight];
                            fs.read(bdata, 0, (nwidth + npad8) * nheight);
                            nindex8 = 0;
                            for (int j8 = 0; j8 < nheight; j8++) {
                                for (int i8 = 0; i8 < nwidth; i8++) {
                                    ndata8[(nwidth * (nheight - j8 - 1) + i8)]
                                            = npalette[(bdata[nindex8] & 0xFF)];
                                    nindex8++;
                                }
                                nindex8 += npad8;
                            }       image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(nwidth, nheight, ndata8, 0, nwidth));
                            break;
                        }
                case 1:
                        {
                            int npad1 = nsizeimage / nheight - nwidth / 8;
                            byte[] bdata = new byte[(nwidth + npad1) * nheight];
                            fs.read(bdata, 0, 8);
                            fs.read(bdata, 0, (nwidth + npad1) * nheight);
                            int[] ndata1 = new int[nwidth * nheight];
                            int nindex1 = 0;
                            for (int j1 = 0; j1 < nheight; j1++) {
                                int iindex = nindex1;
                                for (int i1 = 0; i1 <= nwidth / 8; i1++) {
                                    int ib1 = 0;
                                    if (i1 * 8 < nwidth) {
                                        for (int b1 = 128; b1 > 0; b1 /= 2) {
                                            ndata1[(nwidth * (nheight - j1 - 1) + i1 * 8 + ib1)] = ((b1 & bdata[iindex]) > 0 ? 16777215 : 0);
                                            ib1++;
                                            if (i1 * 8 + ib1 >= nwidth) {
                                                b1 = 0;
                                            }
                                        }
                                    }
                                    iindex++;
                                }
                                nindex1 += nsizeimage / nheight;
                            }       image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(nwidth, nheight, ndata1, 0, nwidth));
                            break;
                        }
                default:
                        Logging.logging("Error", "Not a 24-bit or 8-bit or 1-bit Windows Bitmap format", 
                        System.getProperty("user.home") + "/" + ".headercreator", 
                        "headercreator");
                        image = null;
                        break;
            }
            fs.close();
            return image;
        } catch (Exception e) {
            Logging.logging("Error", "Unable to load bitmap image", 
                    System.getProperty("user.home") + "/" + ".headercreator", 
                    "headercreator");
        }
        return null;
    }
}
