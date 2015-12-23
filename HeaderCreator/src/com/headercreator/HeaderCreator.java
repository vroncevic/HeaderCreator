/**
 * HeaderCreator.java
 *
 * This software is free to use and distribute.
 * 
 * @brief Main entry point
 * @date 7:35:06 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com> 
 */

package com.headercreator;

import com.Utilities.AboutContainer;
import com.Utilities.HelpContainer;
import com.Utilities.SplashScreen.SplashScreen;
import com.Utilities.Version;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HeaderCreator {
    
    protected static SplashScreen splashScreen;
    protected static AboutContainer about;
    protected static HelpContainer help;
    protected static Version ver;

    /**
     * TODO adding console support (without GUI)
     * @param args not processing
     */
    public static void main(String[] args) {
        splashScreen = new SplashScreen(3000, HeaderCreator.class);
        ver = new Version();
        about = new AboutContainer(HeaderCreator.class);
        about.setTitle("About Header Creator");
        about.setAppName("Header Creator ver." + ver.fullAppVersion());
        about.setCompanyName("Frobas 2015 www.frobas.com");
        about.setAppInfo(" Frobas, www.frobas.com");
        help = new HelpContainer(HeaderCreator.class);
        help.setTitle("Help Topics Header Creator");
        help.setAppName("Header Creator ver." + ver.fullAppVersion());
        help.setCompanyName("Frobas IT Department 2015");
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HeaderCreatorGUI(about, help).setVisible(true);
            }
        });
    }
}
