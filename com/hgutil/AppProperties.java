package com.hgutil;

import java.util.*;
import java.awt.*;
import java.io.*;
/**
 * author:      hgrein<BR>
 * date:        May 31, 2004<BR>
 * Package:     com.hgutil<BR>
 * File Name:   AppProperties.java<BR>
 * Type Name:   AppProperties<BR>
 * Description: Application Properties handler
 */
public class AppProperties extends ParseData
{
  /** Field <code>propertiesFile</code> : File */
  private File propertiesFile = null;

  /**
   * Field <code>defaultSettings</code> : Properties
   * 
   * @uml.property name="defaultSettings" 
   */
  private Properties defaultSettings = null;

  /** Field <code>settings</code> : Properties */
  private Properties settings = null;
  /** Field <code>TIMESTAMP</code> : String */
  private static final String TIMESTAMP = "TIMESTAMP";
  /** Field <code>APPTITLE</code> : String */
  // Public Variables
  public static final String APPTITLE = "APPTITLE";
  /** Field <code>FRAMESIZE</code> : String */
  public static final String FRAMESIZE = "FRAMESIZE";
  /** Field <code>BGCOLOR</code> : String */
  public static final String BGCOLOR = "BGCOLOR";
  /** Field <code>FGCOLOR</code> : String */
  public static final String FGCOLOR = "FGCOLOR";
  /** Field <code>DEFAULTFONT</code> : String */
  public static final String DEFAULTFONT = "DEFAULTFONT";
  /** Field <code>DEFLOCALE</code> : String */
  public static final String DEFLOCALE = "DEFLOCALE";
  /**
   * AppProperties constructor comment.
   */
  public AppProperties()
  {
    super();
  }
  /**
   * Constructor for AppProperties. 
   * @param propertyFile - File, the File to use for the properties.
   */
  public AppProperties(File propertyFile)
  {
    propertiesFile = propertyFile;
    initProperties();
  }
  /**
   * Constructor for AppProperties. 
   * @param propertyFile - String, the file name to use for the properties.
   */
  public AppProperties(String propertyFile)
  {
    propertiesFile = new File(propertyFile);
    initProperties();
  }
  /**
   * Method getBooleanProperty.  Returns a boolean primitive for a provided key
   * @param key - String, the key to look for.
   * @return boolean
   */
  public boolean getBooleanProperty(String key)
  {
    return (getBooleanProperty(key, false));
  }
  /**
   * Method getBooleanProperty.  Returns a boolean primitive for a provided key
   * @param key - String, the key to look for.
   * @param defaultValue - boolean,The Default value.  
   * @return boolean
   */
  public boolean getBooleanProperty(String key, boolean defaultValue)
  {
    // Get the property and default value
    boolean value = parseBool(getProperty(key, null), defaultValue);
    return value;
  }
  /**
   * Method getColorProperty.  Returns a Color Object for a provided key
   * @param key  - String, the key to look for.
   * @return Color
   */
  public Color getColorProperty(String key)
  {
    // Get the property and default value
    return (getColorProperty(key, Color.black));
  }
  /**
   * Method getColorProperty.  Returns a Color Object for a provided key
   * @param key  - String, the key to look for.
   * @param defaultValue - Color,The Default value.  
   * @return Color
   */
  public Color getColorProperty(String key, Color defaultValue)
  {
    // Get the property and default value
    Color value = parseColor(getProperty(key, null), defaultValue);
    return (value);
  }

  /**
   * Method getDefaultProperties.  Retu4ns the default properties
   * @return Properties
   * 
   * @uml.property name="defaultSettings"
   */
  public Properties getDefaultProperties() {
    // return the default properties
    return (defaultSettings);
  }

  /**
   * Method getDimensionProperty.  Returns a Dimension Object for a provided key
   * @param key - String, the key to look for.
   * @return Dimension
   */
  public Dimension getDimensionProperty(String key)
  {
    Dimension defaultValue = new Dimension(10, 10);
    return (getDimensionProperty(key, defaultValue));
  }
  /**
   * Method getDimensionProperty.  Returns a Dimension Object for a provided key
   * @param key - String, the key to look for.
   * @param defaultValue - Dimension,The Default value.  
   * @return Dimension
   */
  public Dimension getDimensionProperty(String key, Dimension defaultValue)
  {
    // Get the property and default value
    Dimension value = parseDimension(getProperty(key, null), defaultValue);
    return (value);
  }
  /**
   * Method getFontProperty.  Provides a font return value for a provided key.
   * @param key - String, the key to look for.
   * @return Font, the returned font, will return the default font, if not found, unless 
   * the default is non parsable, then it will return a TimesRoman, 12 point font.
   */
  public Font getFontProperty(String key)
  {
    Font defaultValue = new Font("TimesRoman", 1, 12);
    return (getFontProperty(key, defaultValue));
  }
  /**
   * Method getFontProperty.  Provides a font return value for a provided key.
   * @param key - String, the key to look for.
   * @param defaultValue - Font, The Default value.  
   * @return Font, the returned font, will return the default font, if not found,
   */
  public Font getFontProperty(String key, Font defaultValue)
  {
    // Get the property and default value
    Font value = parseFont(getProperty(key, null), defaultValue);
    return (value);
  }
  /**
   * Method getFontProperty.  Provides a font return value for a provided key.
   * the default return font will be TimesRoman normal 12 point font.
   * @param key - String, the key to look for.
   * @param defaultFont - The Default value.  format should be as follows: "TimesRoman, 1, 12"
   * @return Font, the returned font, will return the default font, if not found, unless 
   * the default is non parsable, then it will return a TimesRoman, 12 point font.
   */
  public Font getFontProperty(String key, String defaultFont)
  {
    Font defaultValue = parseFont(defaultFont, new Font("TimesRoman", 1, 12));
    return (getFontProperty(key, defaultValue));
  }
  /**
   * Method getProperty.  If the File was lasted updated since the last time it was read
   * then it will re-load the settings.  Upon completion it will return
   * either null if the settings do not exits or the actual string.
   *  Function is synchronized to lockout simoutaneous exectution,
   * until it has, finished.  This is due to potential file read
   * @param key - String, the Key to look for.
   * @return String, the value for the provided key
   */
  public synchronized String getProperty(String key)
  {

    // Has the File changed since last read
    if (propertiesFile.lastModified() > parseNum(settings.getProperty(TIMESTAMP), 0))
    {
      // File has changed - reload it
      loadFileSettings();
    }
    // Get the key asked for by the user
    return (settings.getProperty(key));
  }
  /**
   * Method getProperty.  If the File was lasted updated since the last time it was read
   * then it will re-load the settings.  Upon completion it will return
   * either defaultValue if the settings do not exits or the actual string.
   * Function is synchronized to lockout simoutaneous exectution,
   * until it has, finished.  This is due to potential file read
   * @param key - String, the key to look for.
   * @param defaultValue - String, the Default value if key is not found.
   * @return String, the value for the provided key
   */
  public synchronized String getProperty(String key, String defaultValue)
  {
    // Has the File changed since last read
    if (propertiesFile != null && (propertiesFile.lastModified() > parseNum(settings.getProperty(TIMESTAMP), 0)))
    {
      // File has changed - reload it
      loadFileSettings();
    }
    return (settings.getProperty(key, defaultValue));
  }
  /**
   * Method initProperties.  This method sets all default properties deisred by this
   * application.
   */
  private void initProperties()
  {
    // Get the resoultion of the screen.
    Dimension frameDimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    // Set a size string for the default window size
    String size = frameDimension.width / 2 + " " + frameDimension.height / 2;
    // Create a new Properties class for recording defaults.
    defaultSettings = new Properties();

    if (propertiesFile.exists())
    {
      // NO Exception log the Timestamp of the File
      defaultSettings.setProperty(TIMESTAMP, "" + propertiesFile.lastModified());
    }
    // Set all defaults ready to run the application
    defaultSettings.setProperty(APPTITLE, "A Generic Frame");
    defaultSettings.setProperty(FRAMESIZE, size);
    defaultSettings.setProperty(BGCOLOR, "0 0 100");
    defaultSettings.setProperty(FGCOLOR, "50 50 0");
    defaultSettings.setProperty(DEFAULTFONT, "Monospaced,12,1");
    defaultSettings.setProperty(DEFLOCALE, "en_US");
    // Load the Default settings into the standard settings
    settings = new Properties(defaultSettings);
    // Load any defined user preferred settings.
    loadFileSettings();
  }
  /**
   * Method loadFileSettings.  This method checks if the File exists and opens it as an input
   * stream.  If the file can be opened then it then proceeds to load
   * The file into the standard settings.
   */
  private void loadFileSettings()
  {
    InputStream settingFile = null;
    try
    {
      // Does the file exist
      if (!propertiesFile.exists())
      {
        System.err.println("Where is the File..." + propertiesFile.toString());
        // NO!  lets return
        return;
      }
      else
      {
        // Open the File
        settingFile = new FileInputStream(propertiesFile);
      }
      // Load the settings into the file
      settings.load(settingFile);
      // NO Exception log the Timestamp of the File
      settings.setProperty(TIMESTAMP, "" + propertiesFile.lastModified());
    }
    catch (FileNotFoundException e)
    {
      settingFile = ClassLoader.getSystemResourceAsStream(propertiesFile.toString());
    }
    catch (IOException e)
    {
      settingFile = ClassLoader.getSystemResourceAsStream(propertiesFile.toString());
    }
    finally
    {
      try
      {
        if (settingFile != null)
        {
          settingFile.close();
        }
      }
      catch (IOException exc)
      {} // Do Nothing
    }

  }
  /**
   * Method restoreDefaultProperties.  This method makes the original default values, to be the main key
   * with reconstruction of the entire class.
   */
  public void restoreDefaultProperties()
  {
    // declare a new Properties class with the default settings.
    settings = new Properties(defaultSettings);
  }
  /**
   * Method saveProperties.  This method saves the the propertiers file to the File name
   * described in propertiesFile. It opens the File as a PrintStream
   * and calls the properties Store method, the parameter specifies
   * the text as the header to the file.
   * Function is synchronized to lockout simoutaneous exectution
   * until it has, finished.  This is due to potential file write
   * @param headerTitle - Stirng, the title of the header.
   */
  public synchronized void saveProperties(String headerTitle)
  {
    try
    {
      // Open a printstream
      PrintStream fileout = new PrintStream(new FileOutputStream(propertiesFile.getAbsolutePath()));
      // No exception - store it baby.
      settings.store(fileout, headerTitle);
    }
    // Here there boys, Play nice - no pouting allowed.
    catch (FileNotFoundException e)
    {
      System.out.println("File Not Found Exception");
    }
    catch (IOException e)
    {
      System.out.println("IOException ");
    }

  }
  /**
   * Method setProperty.  This method is used to set the new propeties desired by the
   * application, the user needs to enter a Key and defaultValue
   * @param key - String, the key to look for.
   * @param defaultValue - String, default value if key is not found.
   */
  public void setProperty(String key, String defaultValue)
  {
    settings.setProperty(key, defaultValue);
  }
}