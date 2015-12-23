/**
 * HeaderCreatorGUI.java
 *
 * This software is free to use and redistribute.
 * 
 * @brief Main GUI frame
 * @date 7:40:45 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com>
 */

package com.headercreator;

import com.Utilities.About.About;
import com.Utilities.AboutContainer;
import com.Utilities.Dialog;
import com.Utilities.HelpContainer;
import com.Utilities.Help.Help;
import com.Utilities.Logging;
import com.Utilities.OSValidator;
import com.headercreator.imageutils.Generator;
import com.headercreator.imageutils.ImageFilter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HeaderCreatorGUI extends javax.swing.JFrame {
    
    protected About aboutui;
    protected Help helpui;
    protected Dialog dialog;
    protected HeaderCreatorSession config;
    protected Settings setting;
    protected JFileChooser imageChooser;

    /**
     * Initial UI
     * @param about container class with about-info
     * @param help container class with help-info
     */
    public HeaderCreatorGUI(AboutContainer about, HelpContainer help) {
        initComponents();
        imageChooser = new JFileChooser();
        imageChooser.addChoosableFileFilter(new ImageFilter());
        this.aboutui = new About(about);
        this.helpui = new Help(help);
        this.dialog = new Dialog(this);
        this.setting = new Settings(about.getAppName());
        this.config = new HeaderCreatorSession(".headercreator", "hc.cfg");
        setIconImage(about.getAppIcon().getImage());
        setTitle(setting.getVersion());
        java.net.URL imageIconBlank = 
                HeaderCreatorGUI.class.getResource("/resource/blankimg.png");
        ImageIcon blankimg = new ImageIcon(imageIconBlank);
        this.ImageLabel.setIcon(blankimg);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 4 - this.getSize().width / 4, 
                dim.height / 4 - this.getSize().height / 4);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        if (OSValidator.isUnix()) {
            Logging.logging("Info", "Started Header Creator" + " " + 
                    about.getAppName(),
                    System.getProperty("user.home") + "/.headercreator", "HeaderCreator");
        } else if (OSValidator.isWindows()) {
            Logging.logging("Info", "Started Header Creator" + " " + 
                    about.getAppName(),
                    System.getProperty("user.home") + "/", "headercreator");
        } else {
            Logging.logging("Error", "Unsupported OS" + " " + about.getAppName(),
                    System.getProperty("user.home") + "/", "headercreator");
            ApplicationExit();
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ApplicationExit();
            }
        });
    }
    
    /**
     * Closing App method
     */
    private void ApplicationExit() {
        int status = dialog.ShowDialog("Exit Header Creator ?", "Confirm Exit!", 
                JOptionPane.YES_NO_OPTION);
        if (status == JOptionPane.YES_OPTION) {
            config.write(setting);
            System.exit(0);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SelectImage = new javax.swing.JButton();
        GenerateHeader = new javax.swing.JButton();
        Help = new javax.swing.JButton();
        About = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        ImagePanel = new javax.swing.JPanel();
        ImageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        SelectImage.setText("Select");
        SelectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectImageActionPerformed(evt);
            }
        });

        GenerateHeader.setText("Generate");
        GenerateHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateHeaderActionPerformed(evt);
            }
        });

        Help.setText("Help");
        Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpActionPerformed(evt);
            }
        });

        About.setText("About");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        ImagePanel.setMaximumSize(new java.awt.Dimension(240, 320));
        ImagePanel.setMinimumSize(new java.awt.Dimension(240, 320));
        ImagePanel.setPreferredSize(new java.awt.Dimension(240, 320));

        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanel);
        ImagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
        ImagePanelLayout.setVerticalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(GenerateHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SelectImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Help, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(About, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addComponent(ImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SelectImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GenerateHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Help)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(About)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Exit))
                    .addComponent(ImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        ApplicationExit();
    }//GEN-LAST:event_ExitActionPerformed

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        this.aboutui.setVisible(true);
    }//GEN-LAST:event_AboutActionPerformed

    private void HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpActionPerformed
        this.helpui.setVisible(true);
    }//GEN-LAST:event_HelpActionPerformed

    private void SelectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectImageActionPerformed
        int retval = imageChooser.showOpenDialog(HeaderCreatorGUI.this);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File f = imageChooser.getSelectedFile();
            setting.setCurrentFile(f.getAbsolutePath());
            try {
                ImageLabel.setIcon(new ImageIcon(ImageIO.read(f)));
            } catch (IOException ex) {
        
            }
        }
    }//GEN-LAST:event_SelectImageActionPerformed

    private void GenerateHeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateHeaderActionPerformed
        String filename = JOptionPane.showInputDialog("Type header filename", 
                setting.getCurrentFile()+".h");
        if(filename != null) {
           if(Generator.generateHeader(setting.getCurrentFile(), filename)) {
               dialog.ShowDialog("Generated header file " + filename, 
                       "Notification", 
                        JOptionPane.CLOSED_OPTION);
           } else {
               dialog.ShowDialog("Unable to generate header file " + filename, 
                       "Error", 
                        JOptionPane.OK_OPTION);
           } 
        } else {
            dialog.ShowDialog("Please type header filename !", "Warning", 
                        JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_GenerateHeaderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton About;
    private javax.swing.JButton Exit;
    private javax.swing.JButton GenerateHeader;
    private javax.swing.JButton Help;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JPanel ImagePanel;
    private javax.swing.JButton SelectImage;
    // End of variables declaration//GEN-END:variables
}
