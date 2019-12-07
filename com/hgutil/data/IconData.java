package com.hgutil.data;

import javax.swing.ImageIcon;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   IconData.java<BR>
 * Type Name:   IconData<BR>
 * Description: Class Object ot maintain an Image and Text together
 */
public class IconData implements java.io.Serializable
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 4050484512641333303L;

  /**
   * 
   * @uml.property name="icon"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private transient ImageIcon icon;

  /**
   * 
   * @uml.property name="data" 
   */
  private Object data;

  /**
   * Constructor 
   * @param icon ImageIcon
   * @param data Object
   */
  public IconData(ImageIcon icon, Object data)
  {
    setData(icon, data);
  }

  /**
   * Method to return the Data Object
   * @return Object
   * 
   * @uml.property name="data"
   */
  public Object getData() {
    return (data);
  }

  /**
   * Method to return the Data Object as a string
   * @return String
   */
  public String getDataSz()
  {
    return data.toString();
  }

  /**
   * Method to return the Icon
   * @return ImageIcon
   * 
   * @uml.property name="icon"
   */
  public ImageIcon getIcon() {
    return (icon);
  }

  /**
   * Method to set the Data Object
   * @param data Object
   * 
   * @uml.property name="data"
   */
  public void setData(Object data) {
    this.data = data;
  }

  /**
   * Method to set the Data Object and Icon
   * @param icon ImageIcon
   * @param data Object
   */
  public void setData(ImageIcon icon, Object data)
  {
    setIcon(icon);
    setData(data);
  }

  /**
   * Method to set the Icon 
   * @param icon ImageIcon
   * 
   * @uml.property name="icon"
   */
  public void setIcon(ImageIcon icon) {
    this.icon = icon;
  }

  /**
   * Method to return the Data Object as a string
   * @return String
   */
  public String toString()
  {
    String temp = getDataSz();
    if (temp == null)
    {
      temp = "";
    }
    return temp;
  }
  
  /**
   * @see Object#equals(Object)
   */
  public boolean equals( Object obj )
  {
    return ( data.equals(obj) );
  }
}