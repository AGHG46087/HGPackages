package com.hgutil.data;

import java.awt.Color;
import java.io.Serializable;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   ColoredData.java<BR>
 * Type Name:   ColoredData<BR>
 * Description: Class to specify what are its background and foreground colors associated with the data that it carries.
 */
public class ColoredData implements Serializable
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3832622863521232690L;

  /**
   * Field <code>bgColor</code> : Color
   * 
   * @uml.property name="bgColor" 
   */
  private Color bgColor;

  /**
   * Field <code>fgColor</code> : Color
   * 
   * @uml.property name="fgColor" 
   */
  private Color fgColor;

  /**
   * Field <code>hlColor</code> : Color
   * 
   * @uml.property name="hlColor" 
   */
  private Color hlColor = null;

  /**
   * Field <code>data</code> : Object
   * 
   * @uml.property name="data" 
   */
  private Object data;


  /** Field <code>FGCOLOR</code> : Color */
  public static final transient Color FGCOLOR = new Color(0, 0, 0);
  /** Field <code>BGGREEN</code> : Color */
  public static final transient Color BGGREEN = new Color(0, 100, 0);
  /** Field <code>BGRED</code> : Color */
  public static final transient Color BGRED = new Color(100, 0, 0);
  /** Field <code>FGGREEN</code> : Color */
  public static final transient Color FGGREEN = new Color(0, 255, 0);
  /** Field <code>FGRED</code> : Color */
  public static final transient Color FGRED = new Color(255, 0, 0);
  /** Field <code>HIGHLIGHT_GREEN</code> : Color */
  public static final transient Color HIGHLIGHT_GREEN = new Color(0, 255, 0);
  /** Field <code>HIGHLIGHT_RED</code> : Color */
  public static final transient Color HIGHLIGHT_RED = new Color(255, 0, 0);

  /**
   * ColoredData constructor comment.
   * @param data Fraction
   */
  public ColoredData(Fraction data)
  {
    setData(data);
  }
  /**
   * ColoredData constructor comment.
   * @param color Color
   * @param data Object
   */
  public ColoredData(Color color, Object data)
  {
    setData(color, data);
  }
  /**
   * ColoredData constructor comment.
   * @param data Double
   */
  public ColoredData(Double data)
  {
    setData(data);
  }

  /**
   * Returns the Background Color of the Data Object
   * @return Color
   * 
   * @uml.property name="bgColor"
   */
  public Color getBGColor() {
    return bgColor;
  }

  /**
   * Returns the Data Object
   * @return Object
   * 
   * @uml.property name="data"
   */
  public Object getData() {
    return data;
  }

  /**
   * Returns the Foreground Color of the Data Object
   * @return Color
   * 
   * @uml.property name="fgColor"
   */
  public Color getFGColor() {
    return fgColor;
  }

  /**
   * Gets the Cell HighLight Color
   * @return Color
   * 
   * @uml.property name="hlColor"
   */
  public Color getHLColor() {
    return (hlColor);
  }

  /**
   * Sets the Background Color
   * @param color Color
   * 
   * @uml.property name="bgColor"
   */
  public void setBGColor(Color color) {
    bgColor = color;
  }

  /**
   * Sets the Background, Foreground Color and the Data Object
   * if the Value is Greate than or equal to 0 then Green, else Red
   * @param data double
   */
  public void setData(double data)
  {
    bgColor = (data >= 0.0) ? BGGREEN : BGRED;
    fgColor = (data >= 0.0) ? FGGREEN : FGRED;
    hlColor = (data >= 0.0) ? HIGHLIGHT_GREEN : HIGHLIGHT_RED;
    this.data = new Double(data);
  }
  /**
   * Sets the Background, Foreground Color and the Data Object
   * if the Value is Greate than or equal to 0 then Green, else Red
   * @param data int
   */
  public void setData(int data)
  {
    bgColor = (data >= 0) ? BGGREEN : BGRED;
    fgColor = (data >= 0) ? FGGREEN : FGRED;
    this.data = new Integer(data);
  }
  /**
   * Sets the Background, Foreground Color and the Data Object
   * if the Value is Greate than or equal to 0 then Green, else Red
   * @param data long
   */
  public void setData(long data)
  {
    bgColor = (data >= 0) ? BGGREEN : BGRED;
    fgColor = (data >= 0) ? FGGREEN : FGRED;
    this.data = new Long(data);
  }
  /**
   * Sets the Background, Foreground Color and the Data Object
   * if the Value is Greate than or equal to 0 then Green, else Red
   * @param data Fraction
   */
  public void setData(Fraction data)
  {
    bgColor = (data.doubleValue() >= 0.0) ? BGGREEN : BGRED;
    fgColor = (data.doubleValue() >= 0.0) ? FGGREEN : FGRED;
    hlColor = (data.doubleValue() >= 0.0) ? HIGHLIGHT_GREEN : HIGHLIGHT_RED;
    this.data = data;
  }

  /**
   * Sets the Background, Foreground Color and the Data Object
   * @param bgColor Color
   * @param fgColor Color
   * @param data Object
   * 
   * @uml.property name="data"
   */
  public void setData(Color bgColor, Color fgColor, Object data) {
    this.bgColor = bgColor;
    this.fgColor = fgColor;
    this.data = data;
  }

  /**
   * Sets the Foreground Color and the Data Object
   * @param color Color
   * @param data Object
   * 
   * @uml.property name="fgColor"
   */
  public void setData(Color color, Object data) {
    fgColor = color;
    this.data = data;
  }

  /**
   * Sets the Background, Foreground Color and the Data Object
   * if the Value is Greate than or equal to 0 then Green, else Red
   * @param data Double
   */
  public void setData(Double data)
  {
    bgColor = (data.doubleValue() >= 0.0) ? BGGREEN : BGRED;
    fgColor = (data.doubleValue() >= 0.0) ? FGGREEN : FGRED;
    hlColor = (data.doubleValue() >= 0.0) ? HIGHLIGHT_GREEN : HIGHLIGHT_RED;
    this.data = data;
  }
  /**
   * Overrides the Foreground,Background Color
   * @param FGColor Color
   * @param BGColor Color
   */
  public void setFG_BGColor(Color FGColor, Color BGColor)
  {
    bgColor = BGColor;
    fgColor = FGColor;
  }
  /**
   * Sets the Foreground Color
   * @param color Color
   */
  public void setFGColor(Color color)
  {
    fgColor = color;
  }

  /**
   * Sets the Cell HighLight Color
   * @param color Color
   * 
   * @uml.property name="hlColor"
   */
  public void setHLColor(Color color) {
    hlColor = color;
  }

  /**
   * Returns the String representation of the Data Object
   * @return String
   */
  public String toString()
  {
    String temp = getData().toString();
    return temp;
  }
}