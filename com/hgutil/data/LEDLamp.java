package com.hgutil.data;

import java.awt.image.*;
import java.awt.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   LEDLamp.java<BR>
 * Type Name:   LEDLamp<BR>
 * Description: Generates aGraph with little LED LAMPS
 */
public class LEDLamp
{
  /** Field <code>RED</code> : Color */
  public final static Color RED = Color.red;
  /** Field <code>GREEN</code> : Color */
  public final static Color GREEN = Color.green;
  /** Field <code>BLUE</code> : Color */
  public final static Color BLUE = Color.blue;
  /** Field <code>YELLOW</code> : Color */
  public final static Color YELLOW = Color.yellow;
  /** Field <code>BLACK</code> : Color */
  public final static Color BLACK = Color.black;

  /**
   * Field <code>onColor</code> : Color
   * 
   * @uml.property name="onColor" 
   */
  private Color onColor = GREEN;

  /**
   * Field <code>offColor</code> : Color
   * 
   * @uml.property name="offColor" 
   */
  private Color offColor = BLACK;

  /**
   * Field <code>lampOn</code> : boolean
   * 
   * @uml.property name="lampOn" 
   */
  private boolean lampOn = false;

  /**
   * Field <code>buffer</code> : Image
   * 
   * @uml.property name="buffer" 
   */
  private transient Image buffer = null;

  /** Field <code>bg</code> : Graphics2D */
  private transient Graphics2D bg = null;

  /**
   * Field <code>bufferWidth</code> : int
   * 
   * @uml.property name="bufferWidth" 
   */
  private transient int bufferWidth = 0;

  /**
   * Field <code>bufferHeight</code> : int
   * 
   * @uml.property name="bufferHeight" 
   */
  private transient int bufferHeight = 0;

  /**
   * LEDLamp constructor comment.
   */
  public LEDLamp()
  {
    this(20, 5);
  }
  /**
   * LEDLamp constructor comment.
   */
  public LEDLamp(int width, int height)
  {
    this(LEDLamp.GREEN, LEDLamp.BLACK, true, width, height);
  }
  /**
   * LEDLamp constructor comment.
   */
  public LEDLamp(Color onColor)
  {
    this(onColor, LEDLamp.BLACK, true);
  }
  /**
   * LEDLamp constructor comment.
   */
  public LEDLamp(Color onColor, Color offColor, boolean lampOn)
  {
    this(onColor, offColor, lampOn, 20, 5);
  }
  /**
   * LEDLamp constructor comment.
   */
  public LEDLamp(Color onColor, Color offColor, boolean lampOn, int width, int height)
  {
    super();
    setOnColor(onColor);
    setOffColor(offColor);
    setLampOn(lampOn);
    setBufferWidth(width);
    setBufferHeight(height);
  }

  /**
   * Method to the Buffer Image
   * Creation date: (01/08/2002 1:51:35 PM)
   * @return java.awt.Image
   * 
   * @uml.property name="buffer"
   */
  public Image getBuffer() {
    BufferedImage image = new BufferedImage(bufferWidth, bufferHeight, BufferedImage.TYPE_INT_RGB);

    bg = (Graphics2D) image.getGraphics();

    bg.setBackground(BLACK);
    bg.setColor(lampOn ? onColor : offColor);

    bg.fillRect(0, 0, bufferWidth, bufferHeight);
    bg.setColor(Color.gray);
    bg.drawRect(0, 0, bufferWidth, bufferHeight);

    buffer = (Image) image;
    return buffer;
  }

  /**
   * Method to get the Buffer Height of the Lamp
   * Creation date: (01/08/2002 1:56:04 PM)
   * @return int
   * 
   * @uml.property name="bufferHeight"
   */
  public int getBufferHeight() {
    return bufferHeight;
  }

  /**
   * Method to get the Buffer Width of the Lamp
   * Creation date: (01/08/2002 1:54:52 PM)
   * @return int
   * 
   * @uml.property name="bufferWidth"
   */
  public int getBufferWidth() {
    return bufferWidth;
  }

  /**
   * Method to get the Lamp Off Color
   * Creation date: (01/08/2002 1:41:02 PM)
   * @return int
   * 
   * @uml.property name="offColor"
   */
  public Color getOffColor() {
    return offColor;
  }

  /**
   * Method to get the Lamp On Color
   * Creation date: (01/08/2002 1:40:18 PM)
   * @return java.awt.Color
   * 
   * @uml.property name="onColor"
   */
  public java.awt.Color getOnColor() {
    return onColor;
  }

  /**
   * Method to retrieve if LEDLamp is on
   * Creation date: (01/08/2002 1:42:18 PM)
   * @return boolean
   * 
   * @uml.property name="lampOn"
   */
  public boolean isLampOn() {
    return lampOn;
  }

  /**
   * Method to set the Buffer Height of the Lamp
   * Creation date: (01/08/2002 1:56:04 PM)
   * @param newBufferHeight int
   * 
   * @uml.property name="bufferHeight"
   */
  public void setBufferHeight(int newBufferHeight) {
    bufferHeight = (newBufferHeight >= 1) ? newBufferHeight : 1;
  }

  /**
   * Method to set the Buffer Width of the Lamp
   * Creation date: (01/08/2002 1:54:52 PM)
   * @param newBufferWidth int
   * 
   * @uml.property name="bufferWidth"
   */
  public void setBufferWidth(int newBufferWidth) {
    bufferWidth = (newBufferWidth >= 1) ? newBufferWidth : 1;
  }

  /**
   * Method to set the Lamp to on
   * Creation date: (01/08/2002 1:42:18 PM)
   * @param newLampOn boolean TRUE Lamp is on, FALSE lamp is off
   * 
   * @uml.property name="lampOn"
   */
  public void setLampOn(boolean newLampOn) {
    lampOn = newLampOn;
  }

  /**
   * Method to set the Lamp Off Color
   * Creation date: (01/08/2002 1:41:02 PM)
   * @param newOffColor int
   * 
   * @uml.property name="offColor"
   */
  public void setOffColor(Color newOffColor) {
    offColor = newOffColor;
  }

  /**
   * Method to set the Lamp On Color
   * Creation date: (01/08/2002 1:40:18 PM)
   * @param newOnColor java.awt.Color
   * 
   * @uml.property name="onColor"
   */
  public void setOnColor(java.awt.Color newOnColor) {
    onColor = newOnColor;
  }

}