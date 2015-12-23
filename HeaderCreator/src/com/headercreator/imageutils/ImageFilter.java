/**
 * ImageFilter.java
 *
 * This software is free to use and redistribute.
 * 
 * @brief File filter for file chooser
 * @date 9:52:12 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com>
 */

package com.headercreator.imageutils;

import java.io.File;

public class ImageFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        return (file.getName().endsWith(".jpg")
                || file.getName().endsWith(".jpeg")
                || file.getName().endsWith(".png")
                || file.getName().endsWith(".gif")
                || file.getName().endsWith(".bmp"));
    }

    @Override
    public String getDescription() {
        return "Image documents (*.jpg |*.jpeg |*.png |*.gif |*.bmp)";
    }
}
