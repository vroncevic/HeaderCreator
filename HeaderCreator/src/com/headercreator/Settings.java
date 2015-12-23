/**
 * Settings.java
 *
 * This software is free to use and distribute.
 * 
 * @brief Container class for App settings 
 * @date 7:39:03 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com> 
 */

package com.headercreator;

public class Settings {
    
    private String CurrentFile;
    private String Version;

    /**
     * Default constructor
     */
    public Settings() {
        this.CurrentFile = null;
        this.Version = null;
    }
    
    /**
     * Constructor with parameter
     * @param Version of App
     */
    public Settings(String Version) {
        this.CurrentFile = null;
        this.Version = Version;
    }
    
    /**
     * Constructor with parameters
     * @param CurrentFile last selected image
     * @param Version of App
     */
    public Settings(String CurrentFile, String Version) {
        this.CurrentFile = CurrentFile;
        this.Version = Version;
    }

    /**
     * Getting version of App 
     * @return version id
     */
    public String getVersion() {
        return Version;
    }

    /**
     * Setting version of App
     * @param Version id
     */
    public void setVersion(String Version) {
        this.Version = Version;
    }

    /**
     * Getting last selected image 
     * @return absolute path 
     */
    public String getCurrentFile() {
        return CurrentFile;
    }

    /**
     * Setting last selected image
     * @param CurrentFile absolute path
     */
    public void setCurrentFile(String CurrentFile) {
        this.CurrentFile = CurrentFile;
    }
}
