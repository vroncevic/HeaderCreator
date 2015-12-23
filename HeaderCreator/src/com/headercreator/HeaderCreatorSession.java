/**
 * HeaderCreatorSession.java
 *
 * This software is free to use and distribute.
 * 
 * @brief Save last App session to configuration file
 * @date 7:41:12 PM
 * @author Vladimir Roncevic <vladimir.roncevic@frobas.com> 
 */

package com.headercreator;

import com.Utilities.AppConfig.Session;

public class HeaderCreatorSession extends Session {

    /**
     * Saving APP configuration
     * @param CfgDir APP configuration directory
     * @param CfgFile APP configuration file
     */
    public HeaderCreatorSession(String CfgDir, String CfgFile) {
        super(CfgDir, CfgFile);
    }
}
