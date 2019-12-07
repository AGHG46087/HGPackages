package com.hgutil;

import java.util.ResourceBundle;
import java.util.MissingResourceException;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil<BR>
 * File Name:   AppResourceBundle.java<BR>
 * Type Name:   AppResourceBundle<BR>
 * Description: Class is used for capturing imformation from a resource Bundle
 */
public class AppResourceBundle extends ParseData
{

  /**
   * Field <code>bundle</code> : ResourceBundle
   * 
   * @uml.property name="bundle" 
   */
  // Resource bundle for internationalized and accessible text
  private ResourceBundle bundle = null;

  /** Field <code>resourceName</code> : String */
  private String resourceName = null;

  /**
   * Constructs a resource bundle object with parameter name
   * @param resourceName String
   */
  public AppResourceBundle(String resourceName)
  {
    this.resourceName = resourceName;
  }
  /**
   * Returns a boolean value based upon key value
   * @param key String
   * @return boolean
   */
  public boolean getBool(String key)
  {
    String value = null;
    try
    {
      value = getResourceBundle().getString(key);
    }
    catch (MissingResourceException e)
    {
      System.out.println("java.util.MissingResourceException: " + "Couldn't find value for: " + key);
    }
    if (value == null)
    {
      value = "Could not find resource: " + key + "  ";
    }
    boolean rc = parseBool(value, false);
    return rc;
  }

  /**
   * Returns the resource bundle associated with this demo. Used
   * to get accessable and internationalized strings.
   * @return ResourceBundle
   * 
   * @uml.property name="bundle"
   */
  protected ResourceBundle getResourceBundle() {
    if (bundle == null) {
      bundle = ResourceBundle.getBundle(resourceName);
    }
    return bundle;
  }

  /**
   * Returns a String value based upon key value
   * @param key String
   * @return String
   */
  public String getString(String key)
  {
    String value = null;
    try
    {
      value = getResourceBundle().getString(key);
    }
    catch (MissingResourceException e)
    {
      System.out.println("java.util.MissingResourceException: " + "Couldn't find value for: " + key);
    }
    if (value == null)
    {
      value = "Could not find resource: " + key + "  ";
    }
    return value;
  }
}